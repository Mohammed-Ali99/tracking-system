package com.spring.trackingsystem.entity;

import com.spring.trackingsystem.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "shipments")
@Setter
@Getter
public class Shipment extends AbstractAuditingEntity{

    @Id
    @SequenceGenerator(name = "shipment_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_seq")
    private Long id;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_address")
    private String senderAddress;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ToString.Exclude
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    List<ShipmentEvent> events;
}
