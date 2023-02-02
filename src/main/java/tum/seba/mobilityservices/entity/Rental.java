package tum.seba.mobilityservices.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Rental {

	public enum Status {BOOKED, COMPLETED, CANCELED}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date startTime;
	private Date endTime;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Vehicle vehicle;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Invoice invoice;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private ServicePoint startLocation;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private ServicePoint endLocation;

	public Rental() {}

	public Rental(Date startTime, Date endTime, Status status) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public ServicePoint getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(ServicePoint startLocation) {
		this.startLocation = startLocation;
	}

	public ServicePoint getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(ServicePoint endLocation) {
		this.endLocation = endLocation;
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + "]";
	}

}
