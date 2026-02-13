package com.renault.digital.garage_service.repositories;

import com.renault.digital.garage_service.entities.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GarageRepository extends JpaRepository<Garage, Long>,
        JpaSpecificationExecutor<Garage> {

}
