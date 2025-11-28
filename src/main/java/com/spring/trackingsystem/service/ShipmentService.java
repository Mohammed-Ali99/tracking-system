package com.spring.trackingsystem.service;

import com.spring.trackingsystem.dto.ShipmentDriverRequest;
import com.spring.trackingsystem.dto.ShipmentDto;
import com.spring.trackingsystem.dto.ShipmentStatusDto;
import com.spring.trackingsystem.entity.Shipment;

public interface ShipmentService {

    ShipmentDto saveShipment(ShipmentDto dto);

    void assignDriver(Long shipmentId, ShipmentDriverRequest shipmentDriverRequest);

    void updateShipmentStatus(Long shipmentId, ShipmentStatusDto dto);

    ShipmentDto trackShipment(String trackingNumber);

    Shipment getShipmentById(Long id);
}
