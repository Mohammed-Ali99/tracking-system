package com.spring.trackingsystem.entity;

import com.spring.trackingsystem.entity.enums.ShipmentFileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipment_files")
@Setter
@Getter
public class ShipmentFile extends AbstractAuditingEntity{

    @Id
    @SequenceGenerator(name = "shipment_file_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_file_seq")
    private Long id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private ShipmentFileType fileType;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
