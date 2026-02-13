package com.renault.digital.garage_service.repositories;

import com.renault.digital.garage_service.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

 long countByGarageId(Long garageId);

 List<Vehicle> findByGarageId(Long garageId);

 List<Vehicle> findByBrandAndYearAndFuelType(String brand, Integer year, String fuelType);

 Boolean existsByFuelType(String fuelType);

}
