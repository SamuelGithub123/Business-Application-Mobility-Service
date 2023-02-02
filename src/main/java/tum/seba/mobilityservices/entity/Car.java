package tum.seba.mobilityservices.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Car extends Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Positive
	private int numberOfSeats;
	@Positive
	private int horsePower;
	@NotBlank
	private String fuelType;
	@PositiveOrZero
	private int currentMileage;

	public Car() {}

	public Car(String manufacturer, String modelType, Date lastService, boolean isAvailable, int numberOfSeats,
			int horsePower, String fuelType, int currentMileage) {
		super(manufacturer, modelType, lastService, isAvailable);
		this.numberOfSeats = numberOfSeats;
		this.horsePower = horsePower;
		this.fuelType = fuelType;
		this.currentMileage = currentMileage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getCurrentMileage() {
		return currentMileage;
	}

	public void setCurrentMileage(int currentMileage) {
		this.currentMileage = currentMileage;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", numberOfSeats=" + numberOfSeats + ", horsePower=" + horsePower + ", fuelType="
				+ fuelType + ", currentMileage=" + currentMileage + "]";
	}

}
