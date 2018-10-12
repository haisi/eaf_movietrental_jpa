package ch.fhnw.edu.rental.business.rental;

import java.util.List;

import ch.fhnw.edu.rental.business.util.persistence.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.business.user.User;

import static ch.fhnw.edu.rental.business.util.persistence.QueryParameter.with;

@Repository
public class RentalRepository extends AbstractJpaRepository<Rental, Long> {

    public List<Rental> findByUser(User user) {
        return super.findWithQuery(
            "SELECT r FROM Rental r where r.user = :user", with("user", user).params());

    }
}
