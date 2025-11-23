package com.spring.trackingsystem.service;

import com.spring.trackingsystem.entity.enums.ShipmentStatus;

public interface StatusTransitionService {
    void validateTransition(ShipmentStatus current, ShipmentStatus next);
}
