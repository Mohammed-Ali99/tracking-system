package com.spring.trackingsystem.entity;

import com.spring.trackingsystem.entity.enums.ShipmentFileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shipment_files")
@Setter
@Getter
public class ShipmentFile extends AbstractAuditingEntity{

    @Id
    @SequenceGenerator(name = "shipment_file_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_file_seq")
    private Long id;

    @Column(name = "base_data")
    @Lob
    private String base64Data;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private ShipmentFileType fileType;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
