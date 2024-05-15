package com.example.Devices.Repository;

import com.example.Devices.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for interacting with the Device entity in the database.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // No additional methods needed, as JpaRepository provides all necessary CRUD operations
}
