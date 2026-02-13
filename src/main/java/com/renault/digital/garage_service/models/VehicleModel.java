package com.renault.digital.garage_service.models;

import com.renault.digital.garage_service.entities.Garage;
import com.renault.digital.garage_service.entities.Vehicle;
import com.renault.digital.garage_service.enums.FuelType;

import java.util.List;

public record VehicleModel(
        Long id,
        FuelType fuelType,
        String brand,
        Integer year
) {

    /* ENTITY -> MODEL */
    public static VehicleModel of(Vehicle entity) {
        if (entity == null) return null;
        return new VehicleModel(
                entity.getId(),
                entity.getFuelType(),
                entity.getBrand(),
                entity.getYear()
        );
    }

    public static List<VehicleModel> of(List<Vehicle> entities) {
        return entities == null ? List.of()
                : entities.stream().map(VehicleModel::of).toList();
    }

    /* MODEL -> ENTITY (en reliant au garage parent) */
    public Vehicle toEntity(Garage garage) {
        Vehicle v = new Vehicle();
        v.setId(this.id);
        v.setFuelType(this.fuelType);
        v.setBrand(this.brand);
        v.setYear(this.year);
        return v;
    }
}