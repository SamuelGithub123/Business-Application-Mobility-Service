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
import tum.seba.mobilityservices.entity.Invoice;
import tum.seba.mobilityservices.service.InvoiceService;

@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/invoices")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<Iterable<Invoice>>(invoiceService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/invoices/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		return new ResponseEntity<Invoice>(invoiceService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/invoices")
	public ResponseEntity<?> newInvoice(@Valid @RequestBody Invoice newInvoice) {
		return new ResponseEntity<Invoice>(invoiceService.save(newInvoice), HttpStatus.CREATED);
	}

	@PutMapping("/invoices/{id}")
	public ResponseEntity<?> replaceInvoice(@PathVariable int id, @Valid @RequestBody Invoice newInvoice) {
		newInvoice.setId(id);
		invoiceService.save(newInvoice);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<?> deleteInvoice(@PathVariable int id) {
		invoiceService.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
