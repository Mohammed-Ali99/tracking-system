package com.spring.trackingsystem.service.mapper;

import com.spring.trackingsystem.dto.ShipmentDto;
import com.spring.trackingsystem.entity.Shipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentMapper extends EntityMapper<ShipmentDto, Shipment>{
    @Override
    ShipmentDto toDto(Shipment entity);

    @Override
    Shipment toEntity(ShipmentDto dto);
}
