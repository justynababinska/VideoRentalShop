package pl.justynababinska.components.film;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.justynababinska.components.category.Category;
import pl.justynababinska.components.category.CategoryRepository;

@Service
public class FilmController {

	private Scanner sc;
	private FilmRepository filmRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public FilmController(Scanner sc, FilmRepository filmRepository, CategoryRepository categoryRepository) {
		this.sc = sc;
		this.filmRepository = filmRepository;
		this.categoryRepository = categoryRepository;
	}

    public void searchFilm() {
        System.out.println("Podaj fragment nazwy:");
        String name = sc.nextLine();
        List<Film> film = filmRepository.findAllByNameContainingIgnoreCase(name);
        if(film.isEmpty())
            System.out.println("Brak filmu o wskazanej nazwie");
        else {
            System.out.println("Znalezione filmy:");
            film.forEach(System.out::println);
        }
    } 
    
	@Transactional
	public void addFilm() {
		System.out.println("\nDodawanie filmu");
		try {
		Film film = readFilm();
		filmRepository.save(film);
		System.out.println("Dodano film: " + film);
		} catch (CategoryNotFoundException ex) {
			System.err.print("Nie dodano filmu." + ex.getMessage());
		}
	}

	public Film readFilm() {
		Film film = new Film();
		System.out.println("Podaj nazwę filmu:");
		film.setName(sc.nextLine());
		System.out.println("Podaj opis filmu:");
		film.setDescription(sc.nextLine());
		System.out.println("Podaj ilość sztuk filmu:");
		film.setQuantity(sc.nextInt());
		System.out.println("Podaj cenę filmu:");
		film.setPrice(sc.nextDouble());
		sc.nextLine();
		System.out.println("Podaj kategorię filmu(nazwa)");
		String categoryName = sc.nextLine();
		Optional<Category> category = categoryRepository.findByNameIgnoreCase(categoryName);
		category.ifPresentOrElse(film::setCategory, () -> {
			throw new CategoryNotFoundException("Kategoria o podanym id nie istnieje.");
		});
		return film;
	}

	public void removeFilm() {
		System.out.println("Usuwanie filmu");
		System.out.println("Podaj id filmu, który chcesz usunąć:");
		long id = sc.nextLong();
		Optional<Film> film = filmRepository.findById(id);
		film.ifPresentOrElse(filmRepository::delete, () -> System.out.print("Nie istnieje film o wskazanym id."));
	}
}
