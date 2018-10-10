package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import ch.fhnw.edu.rental.persistence.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.UserRepository;

import static ch.fhnw.edu.rental.persistence.QueryParameter.*;

@Repository
public class JpaUserRepository extends AbstractJpaRepository<User, Long> implements UserRepository {

    public JpaUserRepository() {
        setClazz(User.class);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(super.findOne(id));
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public User save(User t) {
        return super.update(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(User entity) {
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
    public List<User> findByLastName(String lastName) {
        return super.findWithNamedQuery(
            "SELECT u FROM User u where u.lastName = :lastName", with("lastName", lastName).params());
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return super.findWithNamedQuery(
            "SELECT u FROM User u where u.firstName = :firstName", with("firstName", firstName).params());
    }

    @Override
    public List<User> findByEmail(String email) {
        return super.findWithNamedQuery(
            "SELECT u FROM User u where u.email = :email", with("email", email).params());

    }
}
