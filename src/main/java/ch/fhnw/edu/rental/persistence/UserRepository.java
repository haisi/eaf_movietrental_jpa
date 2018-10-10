package ch.fhnw.edu.rental.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.User;

import static ch.fhnw.edu.rental.persistence.QueryParameter.*;

@Repository
public class UserRepository extends AbstractJpaRepository<User, Long> {

    public User save(User t) {
        return super.save(t);
    }

    public List<User> findByLastName(String lastName) {
        return super.findWithQuery(
            "SELECT u FROM User u where u.lastName = :lastName", with("lastName", lastName).params());
    }

    public List<User> findByFirstName(String firstName) {
        return super.findWithQuery(
            "SELECT u FROM User u where u.firstName = :firstName", with("firstName", firstName).params());
    }

    public List<User> findByEmail(String email) {
        return super.findWithQuery(
            "SELECT u FROM User u where u.email = :email", with("email", email).params());

    }
}
