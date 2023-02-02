package tum.seba.mobilityservices;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import tum.seba.mobilityservices.entity.Bicycle;
import tum.seba.mobilityservices.entity.Car;
import tum.seba.mobilityservices.entity.Customer;
import tum.seba.mobilityservices.entity.Employee;
import tum.seba.mobilityservices.entity.Invoice;
import tum.seba.mobilityservices.entity.Rental;
import tum.seba.mobilityservices.entity.Rental.Status;
import tum.seba.mobilityservices.entity.ServicePoint;
import tum.seba.mobilityservices.entity.User;
import tum.seba.mobilityservices.entity.Vehicle;
import tum.seba.mobilityservices.service.CustomerService;
import tum.seba.mobilityservices.service.EmployeeService;
import tum.seba.mobilityservices.service.InvoiceService;
import tum.seba.mobilityservices.service.RentalService;
import tum.seba.mobilityservices.service.ServicePointService;
import tum.seba.mobilityservices.service.VehicleService;

@SpringBootApplication
public class SebaMobilityServicesApplication {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ServicePointService servicePointService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private VehicleService vehicleService;

	public static void main(String[] args) {
		SpringApplication.run(SebaMobilityServicesApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void execCodeAfterStartup() {
	
		// instantiate test data
		
		Rental testRental = new Rental(new Date(), new Date(), Status.COMPLETED);
		Invoice testInvoice = new Invoice(120.50, false);
		ServicePoint testServicePoint = new ServicePoint("TUM Main Campus", "Arcisstr.", 21, "Munich");
		User testUser = new User("Max", "Test", "max.test@tum.de", "pw123", "Arcisstr.", 21, "Munich");
		Employee testEmployee = new Employee("Max", "Test", "max.test@tum.de", "pw123", "Arcisstr.", 21, "Munich", "0123456789", new Date());
		Customer testCustomer = new Customer("Max", "Test", "max.test@tum.de", "pw123", "Arcisstr.", 21, "Munich", "maxtest", new Date());
		Vehicle testVehicle = new Vehicle("TUM", "Vehicle", new Date(), true);
		Car testCar = new Car("TUM", "Car", new Date(), true, 5, 215, "gasoline", 10000);
		Bicycle testBicycle = new Bicycle("TUM", "eBike", new Date(), true, 10, true);
		
		// print out all instances to the console
		
		System.out.println(testRental.toString());
		System.out.println(testInvoice.toString());
		System.out.println(testServicePoint.toString());
		System.out.println(testUser.toString());
		System.out.println(testEmployee.toString());
		System.out.println(testCustomer.toString());
		System.out.println(testVehicle.toString());
		System.out.println(testCar.toString());
		System.out.println(testBicycle.toString());
		
		// set associations
		
		testRental.setCustomer(testCustomer);
		testRental.setVehicle(testCar);
		testRental.setInvoice(testInvoice);
		testRental.setStartLocation(testServicePoint);
		testRental.setEndLocation(testServicePoint);
		testCustomer.setRentals(List.of(testRental));
		testCar.setCurrentLocation(testServicePoint);
		testCar.setRentals(List.of(testRental));
		testServicePoint.setVehicles(List.of(testCar, testBicycle));
		testServicePoint.setRentalsStart(List.of(testRental));
		testServicePoint.setRentalsEnd(List.of(testRental));
		testServicePoint.setEmployees(List.of(testEmployee));
		testEmployee.setServicePoints(List.of(testServicePoint));
		
		// persist entities to database
		
		rentalService.save(testRental);
		invoiceService.save(testInvoice);
		servicePointService.save(testServicePoint);
		employeeService.save(testEmployee);
		customerService.save(testCustomer);
		vehicleService.save(testVehicle);
		vehicleService.save(testCar);
		vehicleService.save(testBicycle);
		
		// read each instance from the database
		
		System.out.println(rentalService.findById(testRental.getId()).toString());
		System.out.println(invoiceService.findById(testInvoice.getId()).toString());
		System.out.println(servicePointService.findById(testServicePoint.getId()).toString());
		System.out.println(employeeService.findById(testEmployee.getId()).toString());
		System.out.println(customerService.findById(testCustomer.getId()).toString());
		System.out.println(vehicleService.findById(testVehicle.getId()).toString());
		System.out.println(vehicleService.findById(testCar.getId()).toString());
		System.out.println(vehicleService.findById(testBicycle.getId()).toString());
		
		// test queries
		
		System.out.println(rentalService.findCompletedRentals());
		System.out.println(customerService.findCustomerWithUnpaidInvoices());
		System.out.println(vehicleService.findVehiclesByServicePoint(testServicePoint));
		
	}

}
