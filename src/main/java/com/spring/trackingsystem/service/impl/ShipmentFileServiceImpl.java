package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.dto.ShipmentFileDto;
import com.spring.trackingsystem.entity.Shipment;
import com.spring.trackingsystem.entity.ShipmentFile;
import com.spring.trackingsystem.entity.User;
import com.spring.trackingsystem.repository.ShipmentFileRepository;
import com.spring.trackingsystem.security.SecurityUtils;
import com.spring.trackingsystem.service.ShipmentFileService;
import com.spring.trackingsystem.service.ShipmentService;
import com.spring.trackingsystem.service.UserService;
import com.spring.trackingsystem.service.mapper.ShipmentFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ShipmentFileServiceImpl implements ShipmentFileService {

    private final ShipmentFileRepository shipmentFileRepository;
    private final ShipmentFileMapper shipmentFileMapper;
    private final ShipmentService shipmentService;
    private final UserService userService;

    @Override
    public void uploadFile(Long shipmentId, ShipmentFileDto shipmentFileDto) {
        Shipment shipment = shipmentService.getShipmentById(shipmentId);

        User driver = getCurrentUserLogin();
        if (!Objects.equals(shipment.getDriver(), driver)) {
            throw BusinessException.buildBadRequestException("the user must be the driver of the shipment");
        }

        ShipmentFile entity = shipmentFileMapper.toEntity(shipmentFileDto);
        entity.setShipment(shipment);
        shipmentFileRepository.save(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ShipmentFileDto> getAllFilesByShipmentId(Long shipmentId) {

        Shipment shipment = shipmentService.getShipmentById(shipmentId);
        User driver = getCurrentUserLogin();
        if (!Objects.equals(shipment.getDriver(), driver)) {
            throw BusinessException.buildBadRequestException("the user must be the driver of the shipment");
        }

        return shipmentFileRepository.findByShipment_Id(shipmentId).stream()
                .map(shipmentFileMapper::toDto).toList();

    }

    private User getCurrentUserLogin() {
        Optional<String> currentUserName = SecurityUtils.getCurrentUserName();
        return currentUserName.map(userService::findByEmail).orElse(null);

    }

}
