package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import ch.fhnw.edu.rental.persistence.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;

@Repository
public class JpaPriceCategoryRepository extends AbstractJpaRepository<PriceCategory, Long> implements PriceCategoryRepository {

    public JpaPriceCategoryRepository() {
        setClazz(PriceCategory.class);
    }

    @Override
    public Optional<PriceCategory> findById(Long id) {
        return Optional.ofNullable(super.findOne(id));
    }

    @Override
    public List<PriceCategory> findAll() {
        return super.findAll();
    }

    @Override
    public PriceCategory save(PriceCategory t) {
        return super.update(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PriceCategory entity) {
        super.delete(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public long count() {
        return super.count();
    }


}
