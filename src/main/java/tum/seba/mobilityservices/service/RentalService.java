package tum.seba.mobilityservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tum.seba.mobilityservices.entity.Rental;
import tum.seba.mobilityservices.repository.RentalRepository;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	public Rental save(Rental newRental) {
		return rentalRepository.save(newRental);
	}

	public Iterable<Rental> findAll() {
		return rentalRepository.findAll();
	}

	public Rental findById(int rentalId) {
		return rentalRepository.findById(rentalId).orElse(null);
	}

	public void deleteById(int rentalId) {
		try {
			rentalRepository.deleteById(rentalId);
		}
		catch (Exception e) {
			System.err.println("Unable to delete Rental with ID: " + rentalId);
		}
	}

	public Iterable<Rental> findCompletedRentals() {
		return rentalRepository.findCompletedRentals();
	}

}
