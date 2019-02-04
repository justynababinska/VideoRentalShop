package pl.justynababinska.app;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.justynababinska.components.category.CategoryController;
import pl.justynababinska.components.customer.CustomerController;
import pl.justynababinska.components.film.FilmController;
import pl.justynababinska.components.rent.RentController;

@Controller
public class ApplicationController {

	Scanner sc;
	FilmController filmController;
	CategoryController categoryController;
	CustomerController customerController;
	RentController rentController;

	@Autowired
	public ApplicationController(Scanner sc, FilmController filmController, CategoryController categoryController,
			CustomerController customerController, RentController rentController) {
		this.sc = sc;
		this.filmController = filmController;
		this.categoryController = categoryController;
		this.customerController = customerController;
		this.rentController = rentController;
	}

	public void mainLoop() {
		Option option;
		do {
			printOptions();
			option = readOption();
			executeOption(option);
		} while (option != Option.EXIT);
	}

	public void printOptions() {
		System.out.println("\nWybierz jedną z opcji:");
		Arrays.stream(Option.values()).forEach(System.out::println);
	}

	public Option readOption() {
		boolean correctOption = false;
		Option option = null;
		while (!correctOption) {
			System.out.println("\nPodaj numer opcji:");
			int optionId = sc.nextInt();
			sc.nextLine();
			try {
				option = Option.changeToOption(optionId);
				correctOption = true;
			} catch (InvalidOptionException e) {
				System.err.println(e.getMessage());
			}
		}
		return option;
	}

	public void executeOption(Option option) {
		switch (option) {
		case ADD_FILM:
			filmController.addFilm();
			break;
		case ADD_CATEGORY:
			categoryController.addCategory();
			break;
		case ADD_CUSTOMER:
			customerController.addCustomer();
			break;
		case SEARCH_FILM:
			filmController.searchFilm();
			break;
		case RENT_FILM:
			rentController.rentFilmToCustomer();
			break;
		case REMOVE_FILM:
			filmController.removeFilm();
			break;
		case REMOVE_CATEGORY:
			categoryController.removeCategory();
			break;
		case REMOVE_CUSTOMER:
			customerController.removeCustomer();
			break;
		case EXIT:
			closeApplication();
		}
	}

	private void closeApplication() {
        sc.close();
        System.out.println("Do zobaczenia!");
    }
}
