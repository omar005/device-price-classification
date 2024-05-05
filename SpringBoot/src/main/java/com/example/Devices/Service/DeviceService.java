package com.example.Devices.Service;

import com.example.Devices.Repository.DeviceRepository;
import com.example.Devices.Entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, RestTemplate restTemplate) {
        this.deviceRepository = deviceRepository;
        this.restTemplate = restTemplate;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long deviceId) {
        String apiUrl = "http://localhost:5000/device/" + deviceId;
        ResponseEntity<Device> response = restTemplate.getForEntity(apiUrl, Device.class);
        return response.getBody();
    }

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }
}

