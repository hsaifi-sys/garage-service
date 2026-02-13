package com.renault.digital.garage_service.models;

import com.renault.digital.garage_service.entities.Garage;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record GarageModel(
        Long id,
        String name,
        String address,
        String telephone,
        String email,
        List<VehicleModel> vehicles,
        Map<DayOfWeek, List<OpeningTimeModel>> openingTimes
) {

    /*---------------------------
     * ENTITY -> MODEL
     *--------------------------*/
    public static GarageModel of(Garage entity) {
        if (entity == null) return null;

        Map<DayOfWeek, List<OpeningTimeModel>> times = entity.getOpeningTimes()
                .stream()
                .map(OpeningTimeModel::of)
                .collect(Collectors.groupingBy(OpeningTimeModel::dayOfWeek));

        return new GarageModel(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTelephone(),
                entity.getEmail(),
                VehicleModel.of(entity.getVehicles()),
                times
        );
    }

    public static List<GarageModel> of(List<Garage> entities) {
        return entities == null ? List.of()
                : entities.stream().map(GarageModel::of).toList();
    }

    /*---------------------------
     * MODEL -> ENTITY
     *--------------------------*/
    public Garage toEntity() {
        Garage garage = new Garage();
        garage.setId(this.id);
        garage.setName(this.name);
        garage.setAddress(this.address);
        garage.setTelephone(this.telephone);
        garage.setEmail(this.email);

        if (this.vehicles != null) {
            garage.setVehicles(this.vehicles.stream()
                    .map(v -> v.toEntity(garage))
                    .collect(Collectors.toList()));
        }

        if (this.openingTimes != null) {
            garage.setOpeningTimes(this.openingTimes.entrySet().stream()
                    .flatMap(e -> e.getValue().stream().map(o->o.toEntity(garage))).toList());
        }

        return garage;
    }

    public static List<Garage> from(List<GarageModel> models) {
        return models == null ? List.of() : models.stream().map(GarageModel::toEntity).toList();
    }
}
