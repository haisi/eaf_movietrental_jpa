package ch.fhnw.edu.rental.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Movie;

import static ch.fhnw.edu.rental.persistence.QueryParameter.with;

@Repository
public class MovieRepository extends AbstractJpaRepository<Movie, Long> {

    public MovieRepository() {
        setClazz(Movie.class);
    }

    public List<Movie> findByTitle(String title) {
        return super.findWithQuery(
            "SELECT m from Movie m where m.title = :title", with("email", title).params());
    }

}
