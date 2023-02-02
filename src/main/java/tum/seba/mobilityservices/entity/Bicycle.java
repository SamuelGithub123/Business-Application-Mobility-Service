package tum.seba.mobilityservices.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

@Entity
public class Bicycle extends Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Positive
	private int numberOfGears;
	private boolean isElectrical;
	
	public Bicycle() {}
	
	public Bicycle(String manufacturer, String modelType, Date lastService, boolean isAvailable, int numberOfGears,
			boolean isElectrical) {
		super(manufacturer, modelType, lastService, isAvailable);
		this.numberOfGears = numberOfGears;
		this.isElectrical = isElectrical;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfGears() {
		return numberOfGears;
	}

	public void setNumberOfGears(int numberOfGears) {
		this.numberOfGears = numberOfGears;
	}

	public boolean isElectrical() {
		return isElectrical;
	}

	public void setElectrical(boolean isElectrical) {
		this.isElectrical = isElectrical;
	}

	@Override
	public String toString() {
		return "Bicycle [id=" + id + ", numberOfGears=" + numberOfGears + ", isElectrical=" + isElectrical + "]";
	}

}
