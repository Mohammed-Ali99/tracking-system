package com.spring.trackingsystem.entity;

import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "shipment_events")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentEvent extends AbstractAuditingEntity{

    @Id
    @SequenceGenerator(name = "shipment_event_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_event_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Column(name = "description")
    private String description;
}
