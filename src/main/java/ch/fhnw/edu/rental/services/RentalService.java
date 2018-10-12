package ch.fhnw.edu.rental.services;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.persistence.RentalRepository;

@Service
@Transactional
public class RentalService {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private RentalRepository rentalRepo;

    public List<Rental> getAllRentals() {
        List<Rental> rentals = rentalRepo.findAll();
        log.debug("getAllRentals() done");
        return rentals;
    }

    public Rental getRentalById(Long id) {
        return rentalRepo.findById(id).orElse(null);
    }

    public void deleteRental(Rental rental) {
        if (rental == null) {
            throw new RuntimeException("'rental' parameter is not set!");
        }

        rentalRepo.delete(rental);

        if (log.isDebugEnabled()) {
            log.debug("rental[" + rental.getId() + "] deleted");
        }
    }
}
