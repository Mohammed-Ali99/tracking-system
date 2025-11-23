package com.spring.trackingsystem.dto;

import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShipmentDto {

    private Long id;
    private String trackingNumber;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private Double weight;
    private ShipmentStatus status;
}
