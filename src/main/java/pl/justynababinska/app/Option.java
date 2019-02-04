package pl.justynababinska.app;

public enum Option {
	ADD_FILM(1, "Dodaj film"), 
	ADD_CATEGORY(2, "Dodaj kategorię"), 
	ADD_CUSTOMER(3, "Dodaj klienta"),
	SEARCH_FILM(4, "Szukaj filmu po nazwie"),
	RENT_FILM(5, "Wypożycz film"), 
	REMOVE_FILM(6, "Usuń film"), 
	REMOVE_CATEGORY(7, "Usuń kategorię"),
	REMOVE_CUSTOMER(8, "Usuń klienta"), 
	EXIT(9, "Koniec");

	private int number;
	private String description;

	Option(int number, String description) {
		this.number = number;
		this.description = description;
	}

	@Override
	public String toString() {
		return number + " - " + description;
	}

	static Option changeToOption(int number) {
		if (number < 1 || number > values().length)
			throw new InvalidOptionException();
		return values()[number - 1];
	}
}
