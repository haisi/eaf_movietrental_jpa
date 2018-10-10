package ch.fhnw.edu.rental.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.PriceCategory;

@Repository
public class PriceCategoryRepository extends AbstractJpaRepository<PriceCategory, Long> {

    public PriceCategoryRepository() {
        setClazz(PriceCategory.class);
    }

}
