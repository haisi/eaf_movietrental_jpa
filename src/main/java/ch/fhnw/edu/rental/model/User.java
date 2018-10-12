package ch.fhnw.edu.rental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_FIRSTNAME")
    private String firstName;

    @Column(name = "USER_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Rental> rentals;

    private User() { /* Hibernate... */ }

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.rentals = new ArrayList<>();
    }

    /**
     * bidirectional association must always have both the parent-side and the child-side in sync.
     */
    public void addRental(Rental rental) {
        rentals.add(rental);
        rental.setUser(this);
    }

    public void removeRental(Rental rental) {
        rentals.remove(rental);
        rental.setUser(null);
    }

    @PreRemove
    private void preRemove() {
        for (Rental rental : rentals) {
            rental.setUser(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public int getCharge() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
        }
        return result;
    }

}
