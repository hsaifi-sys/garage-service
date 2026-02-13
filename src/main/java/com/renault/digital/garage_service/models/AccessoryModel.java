package com.renault.digital.garage_service.models;

import com.renault.digital.garage_service.entities.Accessory;
import com.renault.digital.garage_service.entities.Vehicle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public record AccessoryModel(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String type
) implements Serializable {


    /* ENTITY -> MODEL */
    public static AccessoryModel of(Accessory entity) {
        if (entity == null) return null;
        return new AccessoryModel(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getType()
        );
    }

    public static List<AccessoryModel> of(List<Accessory> entities) {
        return entities == null ? List.of()
                : entities.stream().map(AccessoryModel::of).toList();
    }

    /* MODEL -> ENTITY (reliée au vehicle parent) */
    public Accessory toEntity(Vehicle vehicle) {
        Accessory a = new Accessory();
        a.setId(this.id);
        a.setName(this.name);
        a.setPrice(this.price);
        a.setDescription(this.description);
        a.setType(this.type);
        a.setVehicle(vehicle);
        return a;
    }
}