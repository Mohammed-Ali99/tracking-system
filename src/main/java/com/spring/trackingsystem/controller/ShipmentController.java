package com.spring.trackingsystem.controller;

import com.spring.trackingsystem.config.Constant;
import com.spring.trackingsystem.dto.ShipmentDriverRequest;
import com.spring.trackingsystem.dto.ShipmentDto;
import com.spring.trackingsystem.dto.ShipmentStatusDto;
import com.spring.trackingsystem.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<?> saveShipment(@RequestBody ShipmentDto shipmentDto) {
        ShipmentDto dto = shipmentService.saveShipment(shipmentDto);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping("/{id}/assign-driver")
    @PreAuthorize("hasRole('" + Constant.ADMIN + "')")
    public ResponseEntity<?> assignDriver(@PathVariable Long id,
                                          @RequestBody ShipmentDriverRequest shipmentDriverRequest) {
        shipmentService.assignDriver(id, shipmentDriverRequest);
        return ResponseEntity
                .noContent().build();
    }

    @PostMapping("/{id}/update-status")
    @PreAuthorize("hasRole('" + Constant.DRIVER + "')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestBody ShipmentStatusDto shipmentStatusDto) {
        shipmentService.updateShipmentStatus(id, shipmentStatusDto);

        return ResponseEntity
                .noContent().build();
    }

    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<?> trackShipment(@PathVariable String trackingNumber) {
        ShipmentDto dto = shipmentService.trackShipment(trackingNumber);

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
