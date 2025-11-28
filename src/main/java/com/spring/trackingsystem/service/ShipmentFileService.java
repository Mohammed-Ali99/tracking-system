package com.spring.trackingsystem.service;

import com.spring.trackingsystem.dto.ShipmentFileDto;

import java.util.List;

public interface ShipmentFileService {

    void uploadFile(Long shipmentId, ShipmentFileDto shipmentFileDto);

    List<ShipmentFileDto> getAllFilesByShipmentId(Long shipmentId);

}

