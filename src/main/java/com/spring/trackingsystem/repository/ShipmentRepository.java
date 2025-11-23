package com.spring.trackingsystem.repository;

import com.spring.trackingsystem.entity.Shipment;
import com.spring.trackingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Transactional
    @Modifying
    @Query("update Shipment s set s.driver = ?1 where s.id = ?2")
    int updateDriverById(User driver, Long id);

    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
