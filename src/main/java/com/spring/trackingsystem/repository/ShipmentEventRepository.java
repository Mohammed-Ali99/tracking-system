package com.spring.trackingsystem.repository;

import com.spring.trackingsystem.entity.ShipmentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentEventRepository extends JpaRepository<ShipmentEvent, Long> {
}
