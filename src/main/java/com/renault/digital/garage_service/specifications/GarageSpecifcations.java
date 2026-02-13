package com.renault.digital.garage_service.specifications;

import com.renault.digital.garage_service.entities.Garage;
import org.springframework.data.jpa.domain.Specification;

public class GarageSpecifcations {
    public static Specification<Garage> queryInAllFields(String q){
      return ((root, query, cb) -> {
          if(q == null || q.isBlank())
              return cb.conjunction();

          String like = "%" + q.toLowerCase() + "%";
          return cb.or(
                          cb.like(cb.lower(root.get("name")), like),
                          cb.like(cb.lower(root.get("address")), like),
                          cb.like(cb.lower(root.get("email")), like),
                          cb.like(cb.lower(root.get("telephone")), like)
          );

      });

    }
}
