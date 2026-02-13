package com.renault.digital.garage_service.models;

import com.renault.digital.garage_service.entities.Garage;
import com.renault.digital.garage_service.entities.OpeningTime;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record OpeningTimeModel(
        Long id,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) implements Serializable {

    /* ENTITY -> MODEL */
    public static OpeningTimeModel of(OpeningTime entity) {
        if (entity == null) return null;
        return new OpeningTimeModel(
                entity.getId(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public static List<OpeningTimeModel> of(List<OpeningTime> entities) {
        return entities == null ? List.of()
                : entities.stream().map(OpeningTimeModel::of).toList();
    }

    /* MODEL -> ENTITY (reliée au garage parent) */
    public OpeningTime toEntity(Garage garage) {
        OpeningTime ot = new OpeningTime();
        ot.setId(this.id);
        ot.setDayOfWeek(this.dayOfWeek);
        ot.setStartTime(this.startTime);
        ot.setEndTime(this.endTime);
        ot.setGarage(garage);
        return ot;
    }
}