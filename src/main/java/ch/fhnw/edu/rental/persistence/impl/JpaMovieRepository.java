package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class JpaMovieRepository implements MovieRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Movie> findById(Long id) {
        Movie movie = em.find(Movie.class, id);
        return Optional.ofNullable(movie);
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie save(Movie t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        Movie movie = findById(id).orElseThrow(() -> new IllegalArgumentException("Can't delete Movie with id =) null"));
        this.delete(movie);
    }

    @Override
    public void delete(Movie entity) {
        em.remove(entity);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return em.find(Movie.class, id) != null;
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(m) from Movie m", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        TypedQuery<Movie> query = em.createQuery("SELECT m from Movie m where m.title = :title", Movie.class);
        query.setParameter("title", title);

        return query.getResultList();
    }

}
