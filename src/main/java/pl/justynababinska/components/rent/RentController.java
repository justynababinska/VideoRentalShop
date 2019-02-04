package pl.justynababinska.components.rent;

import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.justynababinska.components.customer.Customer;
import pl.justynababinska.components.customer.CustomerRepository;
import pl.justynababinska.components.film.Film;
import pl.justynababinska.components.film.FilmRepository;

@Service
public class RentController {

	Scanner sc;
	CustomerRepository customerRepository;
	FilmRepository filmRepository;

	@Autowired
	public RentController(Scanner sc, CustomerRepository customerRepository, FilmRepository filmRepository) {
		this.sc = sc;
		this.customerRepository = customerRepository;
		this.filmRepository = filmRepository;
	}
	
	@Transactional
    public void rentFilmToCustomer() {
        try {
            rent();
        } catch(RentException e) {
            System.err.println(e.getMessage());
        }
    }
	
	private void rent() {
		System.out.println("Podaj pesel klienta:");
		String customerPesel = sc.nextLine();
		System.out.println("Podaj id filmu:");
		long filmId = sc.nextLong();
		Optional<Customer> customer = customerRepository.findByPesel(customerPesel);
		Optional<Film> film = filmRepository.findById(filmId);
		if (customer.isPresent())
			film.ifPresentOrElse(dev -> {
				if (dev.getQuantity() > dev.getCustomers().size())
					dev.addCustomer(customer.get());
				else
					throw new RentException("Brak wolnych urządzeń o wskazanym id");
			}, () -> {
				throw new RentException("Brak filmu o wskazanym id");
			});
		else
			throw new RentException("Brak klienta o wskazanym id");
	}

}
