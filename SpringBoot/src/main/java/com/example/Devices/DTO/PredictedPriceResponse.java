package com.example.Devices.DTO;

import lombok.Data;

@Data
public class PredictedPriceResponse {

    private Integer predicted_price; // Match the key in the JSON response

    // Lombok's @Data annotation automatically generates getters and setters for all fields
    // You don't need to write explicit getter and setter methods
}
