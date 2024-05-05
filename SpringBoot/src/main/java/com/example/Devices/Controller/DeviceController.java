package com.example.Devices.Controller;
import com.example.Devices.Service.DeviceService;
import com.example.Devices.DTO.PredictedPriceResponse;
import com.example.Devices.Entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeviceController {

    @Autowired
    private RestTemplate restTemplate; // Autowire RestTemplate for HTTP requests

    @Autowired
    private DeviceService deviceService; // Autowire DeviceService for database operations

    private static final String FLASK_API_URL = "http://localhost:5000/predict";

    @PostMapping("/predict/{deviceId}")
    public ResponseEntity<?> predictDevicePrice(@PathVariable Long deviceId) {
        // Fetch device details from your database using deviceId
        Device device = deviceService.getDeviceById(deviceId);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }

        // Prepare request body for Flask API
        Map<String, Object> requestBody = prepareRequestBody(device);

        // Make a POST request to Flask API
        ResponseEntity<PredictedPriceResponse> response = restTemplate.postForEntity(
                FLASK_API_URL, requestBody, PredictedPriceResponse.class);

        // Handle response from Flask API
        if (response.getStatusCode().is2xxSuccessful()) {
            PredictedPriceResponse predictedPriceResponse = response.getBody();
            // Update device entity with predicted price
            device.setPredictedPrice(predictedPriceResponse.getPredictedPrice());
            deviceService.saveDevice(device); // Assuming you have a method to save/update device
            return ResponseEntity.ok().body(predictedPriceResponse);
        } else {
            // Handle error response from Flask API
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }

    // Utility method to prepare request body for Flask API
    private Map<String, Object> prepareRequestBody(Device device) {
        Map<String, Object> requestBody = new HashMap<>();
        // Populate requestBody with device attributes required by Flask API
        // Example: requestBody.put("batteryPower", device.getBatteryPower());
        // Add other attributes as needed
        return requestBody;
    }
}
