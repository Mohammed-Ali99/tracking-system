package com.spring.trackingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShipmentDriverRequest {

    @NotNull(message = "Driver id is required")
    private Long driverId;
}
