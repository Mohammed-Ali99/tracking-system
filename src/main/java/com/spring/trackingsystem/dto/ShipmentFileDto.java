package com.spring.trackingsystem.dto;

import com.spring.trackingsystem.entity.enums.ShipmentFileType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShipmentFileDto {

    @NotNull
    private String base64Data;

    @NotNull
    private String fileExtension;

    @NotNull
    private ShipmentFileType fileType;
}
