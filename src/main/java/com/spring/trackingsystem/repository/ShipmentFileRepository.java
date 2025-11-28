package com.spring.trackingsystem.repository;

import com.spring.trackingsystem.entity.ShipmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentFileRepository extends JpaRepository<ShipmentFile, Long> {
    List<ShipmentFile> findByShipment_Id(Long id);
}
