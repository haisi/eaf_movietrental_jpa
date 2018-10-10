package ch.fhnw.edu.rental.model;

import javax.persistence.*;

@Entity
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name = "pricecategory_type")
public abstract class PriceCategory {

    @Id
    @Column(name = "PRICECATEGORY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
