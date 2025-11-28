package com.spring.trackingsystem.controller;

import com.spring.trackingsystem.config.Constant;
import com.spring.trackingsystem.dto.ShipmentFileDto;
import com.spring.trackingsystem.service.ShipmentFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shipment-files")
public class ShipmentFileController {

    private final ShipmentFileService shipmentFileService;

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasRole('" + Constant.DRIVER +"')")
    public ResponseEntity<?> uploadFile(@PathVariable Long id,
                                        @RequestBody ShipmentFileDto shipmentFileDto) {

        shipmentFileService.uploadFile(id, shipmentFileDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/all-files")
    @PreAuthorize("hasRole('" + Constant.DRIVER +"')")
    public ResponseEntity<?> getAllFilesByShipmentId(@PathVariable Long id) {
        List<ShipmentFileDto> shipmentFileDtos = shipmentFileService.getAllFilesByShipmentId(id);

        return ResponseEntity
                .ok(shipmentFileDtos);
    }

}
