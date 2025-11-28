package com.spring.trackingsystem.service.mapper;

import com.spring.trackingsystem.dto.ShipmentFileDto;
import com.spring.trackingsystem.entity.ShipmentFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentFileMapper extends EntityMapper<ShipmentFileDto, ShipmentFile>{

    @Override
    ShipmentFileDto toDto(ShipmentFile entity);

    @Override
    ShipmentFile toEntity(ShipmentFileDto dto);
}
