package tum.seba.mobilityservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import tum.seba.mobilityservices.entity.Customer;
import tum.seba.mobilityservices.repository.CustomerRepository;

import java.text.MessageFormat;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private Argon2PasswordEncoder argon2PasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return customerRepository.findByEmail(username).orElseThrow(() ->
				new UsernameNotFoundException(MessageFormat.format("User with username {0} cannot be found.", username)));
	}

	public Customer save(Customer newCustomer) {
		newCustomer.setPassword(argon2PasswordEncoder.encode(newCustomer.getPassword()));
		try { return customerRepository.save(newCustomer); }
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	public void deleteById(int customerId) {
		try {
			customerRepository.deleteById(customerId);
		}
		catch (Exception e) {
			System.err.println("Unable to delete Customer with ID: " + customerId);
		}
	}

	public Iterable<Customer> findCustomerWithUnpaidInvoices() {
		return customerRepository.findCustomersWithUnpaidInvoices();
	}

}
