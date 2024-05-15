package com.example.Devices.Controller;

import com.example.Devices.DTO.PredictedPriceResponse;
import com.example.Devices.Entity.Device;
import com.example.Devices.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for handling device-related operations.
 */
@RestController
public class DeviceController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeviceRepository deviceRepository;

    private static final String FLASK_API_URL = "http://localhost:5000/predict";

    /**
     * Endpoint to predict device price by sending specifications to Flask API.
     * @param requestBody JSON string representing device specifications.
     * @param response HttpServletResponse to send the prediction response.
     */
    @PostMapping("/api/predict")
    public void predictDevicePrice(@RequestBody String requestBody, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> flaskResponse = restTemplate.postForEntity(FLASK_API_URL, requestEntity, String.class);
        String flaskResponseBody = flaskResponse.getBody();

        try {
            response.getWriter().write(flaskResponseBody);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Endpoint to predict device price by device ID.
     * @param deviceId ID of the device to predict price for.
     * @return ResponseEntity containing the predicted price response.
     */
    @PostMapping("/api/predict/{deviceId}")
    public ResponseEntity<PredictedPriceResponse> predictPriceByDeviceId(@PathVariable Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if (optionalDevice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Device device = optionalDevice.get();
        Map<String, Object> requestBody = prepareRequestBody(device);
        ResponseEntity<PredictedPriceResponse> response = restTemplate.postForEntity(
                FLASK_API_URL, requestBody, PredictedPriceResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            PredictedPriceResponse predictedPriceResponse = response.getBody();

            if (predictedPriceResponse != null) {
                device.setPredicted_price(predictedPriceResponse.getPredicted_price());
                deviceRepository.save(device);

                return ResponseEntity.ok().body(predictedPriceResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    /**
     * Utility method to prepare request body for Flask API.
     * @param device Device object containing specifications.
     * @return Map representing the request body.
     */
    private Map<String, Object> prepareRequestBody(Device device) {
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("battery_power", device.getBattery_power());
        requestBody.put("blue", device.getBlue());
        requestBody.put("clock_speed", device.getClock_speed());
        requestBody.put("dual_sim", device.getDual_sim());
        requestBody.put("fc", device.getFc());
        requestBody.put("int_memory", device.getInt_memory());
        requestBody.put("m_dep", device.getM_dep());
        requestBody.put("mobile_wt", device.getMobile_wt());
        requestBody.put("n_cores", device.getN_cores());
        requestBody.put("pc", device.getPc());
        requestBody.put("ram", device.getRam());
        requestBody.put("talk_time", device.getTalk_time());
        requestBody.put("touch_screen", device.getTouch_screen());
        requestBody.put("wifi", device.getWifi());
        requestBody.put("combined_g", device.getCombined_g());
        requestBody.put("screen_size", device.getScreen_size());
        requestBody.put("pixel_density", device.getPixel_density());
        return requestBody;
    }


    /**
     * Endpoint to retrieve device by ID.
     * @param id ID of the device to retrieve.
     * @return ResponseEntity containing the device if found, otherwise 404.
     */
    @GetMapping("/api/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.map(device -> ResponseEntity.ok().body(device))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to add a new device.
     * @param device Device object representing the new device.
     * @return ResponseEntity containing the saved device or internal server error.
     */
    @PostMapping("/api/devices")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        try {
            Device savedDevice = deviceRepository.save(device);
            return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to retrieve all devices.
     * @return ResponseEntity containing the list of devices if found, otherwise 404.
     */
    @GetMapping("/api/devices")
    public ResponseEntity<List<Device>> retrieveAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(devices);
    }

}
