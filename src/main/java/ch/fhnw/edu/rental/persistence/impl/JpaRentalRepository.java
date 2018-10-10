package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import ch.fhnw.edu.rental.persistence.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.RentalRepository;

import static ch.fhnw.edu.rental.persistence.QueryParameter.with;

@Repository
public class JpaRentalRepository extends AbstractJpaRepository<Rental, Long> implements RentalRepository {

    public JpaRentalRepository() {
        setClazz(Rental.class);
    }

    @Override
    public Optional<Rental> findById(Long id) {
        return Optional.ofNullable(super.findOne(id));
    }

    @Override
    public List<Rental> findAll() {
        return super.findAll();
    }

    @Override
    public Rental save(Rental t) {
        return super.update(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Rental entity) {
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

    @Override
    public List<Rental> findByUser(User user) {
        return super.findWithQuery(
            "SELECT r FROM Rental r where r.user = :user", with("user", user).params());

    }
}
