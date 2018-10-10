package ch.fhnw.edu.rental.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    private Long id;

    private String title;
    private LocalDate releaseDate;
    private boolean rented;
    private PriceCategory priceCategory;

    public Movie(String title, LocalDate releaseDate, PriceCategory priceCategory) throws NullPointerException {
        if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
            throw new NullPointerException("not all input parameters are set!");
        }
        this.title = title;
        this.releaseDate = releaseDate;
        this.priceCategory = priceCategory;
        this.rented = false;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
