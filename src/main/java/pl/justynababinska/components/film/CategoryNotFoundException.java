package pl.justynababinska.components.film;

public class CategoryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3879060029677874495L;

	CategoryNotFoundException(String message) {
		super(message);
	}

}
