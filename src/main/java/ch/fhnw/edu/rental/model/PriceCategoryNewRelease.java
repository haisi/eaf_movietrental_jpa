package ch.fhnw.edu.rental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("NewRelease")
public class PriceCategoryNewRelease extends PriceCategory {

//    private PriceCategoryNewRelease() {/* Hibernate... */}

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        // add bonus for two day new release rental
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "New Release";
    }
}
