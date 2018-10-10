package ch.fhnw.edu.rental.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

import static ch.fhnw.edu.rental.persistence.QueryParameter.with;

@Repository
public class RentalRepository extends AbstractJpaRepository<Rental, Long> {

    public List<Rental> findByUser(User user) {
        return super.findWithQuery(
            "SELECT r FROM Rental r where r.user = :user", with("user", user).params());

    }
}
