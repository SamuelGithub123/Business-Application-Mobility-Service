package tum.seba.mobilityservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tum.seba.mobilityservices.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT c FROM Customer c, Rental r, Invoice i WHERE c.id = r.customer.id AND r.invoice.id = i.id AND i.isPaid = false")
	public List<Customer> findCustomersWithUnpaidInvoices();

	Optional<Customer> findByEmail(String email);

}
