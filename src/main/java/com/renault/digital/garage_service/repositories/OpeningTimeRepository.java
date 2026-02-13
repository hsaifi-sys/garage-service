package com.renault.digital.garage_service.repositories;

import com.renault.digital.garage_service.entities.OpeningTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface OpeningTimeRepository extends JpaRepository<OpeningTime, Long> {

    List<OpeningTime> findByGarageId(Long garageId);

    List<OpeningTime> findByGarageIdAndDayOfWeek(Long garageId, DayOfWeek dayOfWeek);
}
