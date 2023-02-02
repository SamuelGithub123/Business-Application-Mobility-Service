package tum.seba.mobilityservices.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer extends User {

	@NotBlank
	@Column(unique = true)
	private String username;
	private Date dateOfBirth;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private List<Rental> rentals;

	public Customer() {}

	public Customer(String firstName, String lastName, String email, String password, String streetName,
			int houseNumber, String city, String username, Date dateOfBirth) {
		super(firstName, lastName, email, password, streetName, houseNumber, city);
		this.username = username;
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
