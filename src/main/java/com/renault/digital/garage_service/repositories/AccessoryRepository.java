package com.renault.digital.garage_service.repositories;

import com.renault.digital.garage_service.entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    List<Accessory> findByVehicleId(Long vehicleId);

    boolean existsByName( String name);
}
