package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.controller.exception.BusinessException;
import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import com.spring.trackingsystem.service.StatusTransitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatusTransitionServiceImpl implements StatusTransitionService {

    public static final Map<ShipmentStatus, List<ShipmentStatus>> ALLOWED_TRANSITIONS  = Map.of(
            ShipmentStatus.CREATED, List.of(ShipmentStatus.PICKED, ShipmentStatus.CANCELLED),
            ShipmentStatus.PICKED,  List.of(ShipmentStatus.IN_TRANSIT),
            ShipmentStatus.IN_TRANSIT, List.of(ShipmentStatus.DELIVERED),
            ShipmentStatus.DELIVERED, List.of(),
            ShipmentStatus.CANCELLED, List.of()
    );

    public void validateTransition(ShipmentStatus current, ShipmentStatus next) {
        List<ShipmentStatus> nextShipmentStatuses = ALLOWED_TRANSITIONS.get(current);
        if (!nextShipmentStatuses.contains(next)) {
            throw BusinessException.buildBadRequestException("Invalid status transition");
        }
    }
}
