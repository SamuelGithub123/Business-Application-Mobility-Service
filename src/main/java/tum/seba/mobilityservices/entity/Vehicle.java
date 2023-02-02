package tum.seba.mobilityservices.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String manufacturer;
	@NotBlank
	private String modelType;
	private Date lastService;
	private boolean isAvailable;

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.PERSIST)
	private List<Rental> rentals;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private ServicePoint currentLocation;
	
	public Vehicle() {}

	public Vehicle(String manufacturer, String modelType, Date lastService, boolean isAvailable) {
		this.manufacturer = manufacturer;
		this.modelType = modelType;
		this.lastService = lastService;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Date getLastService() {
		return lastService;
	}

	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public ServicePoint getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(ServicePoint currentLocation) {
		this.currentLocation = currentLocation;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", manufacturer=" + manufacturer + ", modelType=" + modelType + ", lastService="
				+ lastService + ", isAvailable=" + isAvailable + "]";
	}

}
