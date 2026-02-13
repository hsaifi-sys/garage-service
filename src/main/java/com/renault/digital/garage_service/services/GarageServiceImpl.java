package com.renault.digital.garage_service.services;

import com.renault.digital.garage_service.entities.Garage;
import com.renault.digital.garage_service.models.GarageModel;
import com.renault.digital.garage_service.repositories.GarageRepository;
import com.renault.digital.garage_service.specifications.GarageSpecifcations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageServiceImpl implements GarageService{
   @Autowired
   private GarageRepository garageRepository;

    @Override
    public GarageModel create(GarageModel garageModel) {
        return GarageModel.of(
                garageRepository.save(garageModel.toEntity())
        );
    }

    @Override
    public GarageModel update(Long id, GarageModel garageModel) {

        Garage existing = garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found : " + id));

        existing.setAddress(garageModel.address());
        existing.setName(garageModel.name());
        existing.setTelephone(garageModel.telephone());
        existing.setEmail(garageModel.email());

        Garage saved = garageRepository.save(existing);

        return GarageModel.of(saved);
    }

    @Override
    public void delete(Long id) {
        Optional<Garage> existing = garageRepository.findById(id);
        garageRepository.delete((existing).orElseThrow(() ->
                new RuntimeException("Garage not found with id =" + id)));
    }

    @Override
    public GarageModel getById(Long id) {
        return GarageModel.of(garageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Garage not found with id =" + id)));

    }

    @Override
    public Page<GarageModel> search(String criteria, Pageable pageable) {
        Specification<Garage> spec = GarageSpecifcations.queryInAllFields(criteria);
        Page<Garage> page = garageRepository.findAll(spec, pageable);
        return page.map(GarageModel::of);
    }
}
