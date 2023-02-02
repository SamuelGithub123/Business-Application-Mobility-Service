package tum.seba.mobilityservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tum.seba.mobilityservices.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	@Query("SELECT r FROM Rental r WHERE r.status = 'COMPLETED'")
	public List<Rental> findCompletedRentals();

}
