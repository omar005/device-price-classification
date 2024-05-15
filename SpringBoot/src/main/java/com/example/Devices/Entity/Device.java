package com.example.Devices.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing a device in the system.
 */
@Entity
@Data
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer battery_power;
    private Boolean blue;
    private Double clock_speed;
    private Boolean dual_sim;
    private Integer fc;
    private Integer combined_g;
    private Integer int_memory;
    private Double m_dep;
    private Double mobile_wt;
    private Integer n_cores;
    private Integer pc;
    private Double pixel_density;
    private Integer ram;
    private Double screen_size;
    private Double talk_time;
    private Boolean touch_screen;
    private Boolean wifi;
    private Integer predicted_price;
}
