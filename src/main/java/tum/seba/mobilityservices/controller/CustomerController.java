package tum.seba.mobilityservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tum.seba.mobilityservices.entity.Customer;
import tum.seba.mobilityservices.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<Iterable<Customer>>(customerService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		return new ResponseEntity<Customer>(customerService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/customers")
	public ResponseEntity<?> newCustomer(@Valid @RequestBody Customer newCustomer) {
		return new ResponseEntity<Customer>(customerService.save(newCustomer), HttpStatus.CREATED);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<?> replaceCustomer(@PathVariable int id, @Valid @RequestBody Customer newCustomer) {
		newCustomer.setId(id);
		customerService.save(newCustomer);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
		customerService.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/customers/notpaid")
	public ResponseEntity<?> unpaidInvoices() {
		return new ResponseEntity<Iterable<Customer>>(customerService.findCustomerWithUnpaidInvoices(), HttpStatus.OK);
	}

}
