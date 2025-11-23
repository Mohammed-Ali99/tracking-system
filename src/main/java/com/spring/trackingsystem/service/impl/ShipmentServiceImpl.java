package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.dto.ShipmentDriverRequest;
import com.spring.trackingsystem.dto.ShipmentDto;
import com.spring.trackingsystem.dto.ShipmentStatusDto;
import com.spring.trackingsystem.entity.*;
import com.spring.trackingsystem.entity.enums.Role;
import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import com.spring.trackingsystem.repository.ShipmentRepository;
import com.spring.trackingsystem.security.SecurityUtils;
import com.spring.trackingsystem.service.ShipmentEventService;
import com.spring.trackingsystem.service.ShipmentService;
import com.spring.trackingsystem.service.StatusTransitionService;
import com.spring.trackingsystem.service.UserService;
import com.spring.trackingsystem.service.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentEventService shipmentEventService;
    private final UserService userService;
    private final StatusTransitionService statusTransitionService;

    public ShipmentDto saveShipment(ShipmentDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw BusinessException.buildBadRequestException("id should be null");
        }
        if (dto.getWeight() < 0) {
            throw BusinessException.buildBadRequestException("weight should be greater than 0");
        }
        String trackingNumber = generateTrackingNumber();
        Shipment entity = shipmentMapper.toEntity(dto);
        entity.setTrackingNumber(trackingNumber);
        entity.setStatus(ShipmentStatus.CREATED);
        entity.setCustomer(applyCustomer());
        entity = shipmentRepository.save(entity);

        ShipmentEvent shipmentCreated = ShipmentEvent.builder()
                .shipment(entity)
                .status(entity.getStatus())
                .description("Shipment created")
                .build();
        shipmentEventService.saveShipmentEvent(shipmentCreated);

        return shipmentMapper.toDto(entity);
    }

    private User applyCustomer() {
        Optional<String> currentUserName = SecurityUtils.getCurrentUserName();
        return currentUserName.map(userService::findByEmail).orElse(null);
    }

    private String generateTrackingNumber() {
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public void assignDriver(Long shipmentId, ShipmentDriverRequest shipmentDriverRequest) {
        User driver = userService.findById(shipmentDriverRequest.getDriverId());
        if (!Objects.equals(driver.getRole(), Role.DRIVER)) {
            throw BusinessException.buildBadRequestException("the user must be a driver");
        }
        shipmentRepository.updateDriverById(driver, shipmentId);
    }

    @Transactional
    @Override
    public void updateShipmentStatus(Long shipmentId, ShipmentStatusDto dto) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> BusinessException.buildNotFoundException(Shipment.class, shipmentId));

        checkDriver(shipment);
        validateStatus(shipment, dto);

        shipment.setStatus(dto.getStatus());
        shipment = shipmentRepository.save(shipment);

        ShipmentEvent shipmentEvent = ShipmentEvent.builder()
                .shipment(shipment)
                .status(shipment.getStatus())
                .description("Shipment status updated to " + shipment.getStatus().name())
                .build();

        shipmentEventService.saveShipmentEvent(shipmentEvent);
    }

    private void validateStatus(Shipment shipment, ShipmentStatusDto dto) {
        if (Objects.equals(shipment.getStatus(), dto.getStatus())) {
            return;
        }
        statusTransitionService.validateTransition(shipment.getStatus(), dto.getStatus());
    }

    private void checkDriver(Shipment shipment) {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserName();
        if (currentUserLogin.isEmpty()) {
            throw BusinessException.buildBadRequestException("no user logged in, cannot update the status of the shipment.");
        }
        User driver = userService.findByEmail(currentUserLogin.get());

        if (Objects.isNull(shipment.getDriver())) {
            throw BusinessException.buildBadRequestException("the shipment must have a driver assigned");
        }

        if (!Objects.equals(shipment.getDriver(), driver)) {
            throw BusinessException.buildBadRequestException("the user must be the driver of the shipment");
        }
    }

    @Override
    public ShipmentDto trackShipment(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> BusinessException.buildNotFoundException(Shipment.class, trackingNumber));
        return shipmentMapper.toDto(shipment);
    }
}
