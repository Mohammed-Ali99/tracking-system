package com.spring.trackingsystem.dto;

import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShipmentStatusDto {

    @NotNull(message = "Status is required")
    private ShipmentStatus status;
}
