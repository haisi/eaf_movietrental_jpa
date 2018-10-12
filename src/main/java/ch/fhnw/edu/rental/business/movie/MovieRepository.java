package ch.fhnw.edu.rental.business.movie;

import java.util.List;

import ch.fhnw.edu.rental.business.util.persistence.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

import static ch.fhnw.edu.rental.business.util.persistence.QueryParameter.with;

@Repository
public class MovieRepository extends AbstractJpaRepository<Movie, Long> {

    public List<Movie> findByTitle(String title) {
        return super.findWithQuery(
            "SELECT m from Movie m where m.title = :title", with("title", title).params());
    }

}
