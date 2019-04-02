package pl.justynababinska.components.customer;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerController {

	private Scanner sc;
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerController(Scanner sc, CustomerRepository customerRepository) {
		this.sc = sc;
		this.customerRepository = customerRepository;
	}

	public void addCustomer() {
		System.out.println("Dodawanie klienta");
		Customer customer = readCustomer();
		try {
			customerRepository.save(customer);
			System.out.println("Dodano klienta: " + customer);
		} catch (DataIntegrityViolationException ex) {
			System.err.println("Nie dodano klienta, pesel może być duplikatem");
		}
	}

	public Customer readCustomer() {
		Customer customer = new Customer();
		System.out.println("Podaj imię:");
		customer.setFirstName(sc.nextLine());
		System.out.println("Podaj nazwisko:");
		customer.setLastName(sc.nextLine());
		System.out.println("Podaj pesel:");
		customer.setPesel(sc.nextLine());
		System.out.println("Podaj nr dowodu:");
		customer.setIdentityCard(sc.nextLine());
		return customer;
	}

	public void removeCustomer() {
		System.out.println("Usuwanie klienta");
		System.out.println("Podaj id klienta, którego chcesz usunąć");
		long id = sc.nextLong();
		Optional<Customer> customer = customerRepository.findById(id);
		customer.ifPresentOrElse(customerRepository::delete,
				() -> System.out.print("Nie istnieje klient o wskazanym id"));
	}
}
