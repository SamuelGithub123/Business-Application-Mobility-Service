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
import tum.seba.mobilityservices.entity.ServicePoint;
import tum.seba.mobilityservices.service.ServicePointService;

@RestController
@RequestMapping("/api")
public class ServicePointController {

	@Autowired
	private ServicePointService servicePointService;

	@GetMapping("/servicepoints")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<Iterable<ServicePoint>>(servicePointService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/servicepoints/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		return new ResponseEntity<ServicePoint>(servicePointService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/servicepoints")
	public ResponseEntity<?> newServicePoint(@Valid @RequestBody ServicePoint newServicePoint) {
		return new ResponseEntity<ServicePoint>(servicePointService.save(newServicePoint), HttpStatus.CREATED);
	}

	@PutMapping("/servicepoints/{id}")
	public ResponseEntity<?> replaceServicePoint(@PathVariable int id, @Valid @RequestBody ServicePoint newServicePoint) {
		newServicePoint.setId(id);
		servicePointService.save(newServicePoint);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/servicepoints/{id}")
	public ResponseEntity<?> deleteServicePoint(@PathVariable int id) {
		servicePointService.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
