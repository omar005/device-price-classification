package com.example.Devices.Repository;

import com.example.Devices.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // You can add custom query methods here if needed
}

