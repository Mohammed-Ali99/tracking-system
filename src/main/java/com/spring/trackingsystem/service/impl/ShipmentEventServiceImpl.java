package com.spring.trackingsystem.service.impl;

import com.spring.trackingsystem.entity.ShipmentEvent;
import com.spring.trackingsystem.repository.ShipmentEventRepository;
import com.spring.trackingsystem.service.ShipmentEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentEventServiceImpl implements ShipmentEventService {

    private final ShipmentEventRepository shipmentEventRepository;

    @Override
    public void saveShipmentEvent(ShipmentEvent event) {
        shipmentEventRepository.save(event);
    }
}
