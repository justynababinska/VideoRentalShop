package pl.justynababinska.components.category;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoryController {

	private Scanner sc;
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryController(Scanner sc, CategoryRepository categoryRepository) {
		this.sc = sc;
		this.categoryRepository = categoryRepository;
	}

	public void addCategory() {
		System.out.println("Dodawanie kategorii");
		Category category = readCategory();
		try {
		categoryRepository.save(category);
		System.out.println("Dodano kategorię: " + category);
		} catch (DataIntegrityViolationException ex) {
			System.err.println("Nie dodano kategorii, nazwa może być duplikatem");
		}
	}

	public Category readCategory() {
		Category category = new Category();
		System.out.println("Podaj nazwę kategorii:");
		category.setName(sc.nextLine());
		System.out.println("Podaj opis kategorii:");
		category.setDescription(sc.nextLine());
		return category;
	}

	public void removeCategory() {
		System.out.println("Usuwanie kategorii");
		System.out.println("Podaj id kategorii, którą chcesz usunąć");
		long id = sc.nextLong();
		Optional<Category> category = categoryRepository.findById(id);
		category.ifPresentOrElse(categoryRepository::delete,
				() -> System.out.print("Nie istnieje kategoria o wskazanym id"));
	}

}
