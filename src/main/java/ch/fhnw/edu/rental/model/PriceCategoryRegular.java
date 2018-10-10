package ch.fhnw.edu.rental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Regular")
public class PriceCategoryRegular extends PriceCategory {

//    private PriceCategoryRegular() {/* Hibernate... */}

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }

    @Override
    public String toString() {
        return "Regular";
    }
}
