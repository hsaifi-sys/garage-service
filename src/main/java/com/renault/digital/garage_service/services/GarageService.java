package com.renault.digital.garage_service.services;

import com.renault.digital.garage_service.entities.Garage;
import com.renault.digital.garage_service.models.GarageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GarageService {
    GarageModel create(GarageModel garageModel);
    GarageModel update(Long id, GarageModel garageModel);
    void delete(Long  id);
    GarageModel getById(Long id);

    Page<GarageModel> search(String criteria, Pageable pageable);
}
