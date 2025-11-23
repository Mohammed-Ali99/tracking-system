package com.spring.trackingsystem.service;

import com.spring.trackingsystem.dto.ShipmentDriverRequest;
import com.spring.trackingsystem.dto.ShipmentDto;
import com.spring.trackingsystem.dto.ShipmentStatusDto;

public interface ShipmentService {

    ShipmentDto saveShipment(ShipmentDto dto);

    void assignDriver(Long shipmentId, ShipmentDriverRequest shipmentDriverRequest);

    void updateShipmentStatus(Long shipmentId, ShipmentStatusDto dto);

    ShipmentDto trackShipment(String trackingNumber);
}
