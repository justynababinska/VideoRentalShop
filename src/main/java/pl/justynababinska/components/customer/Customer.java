package pl.justynababinska.components.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import pl.justynababinska.components.film.Film;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = -6079222943273025097L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(unique = true, length = 11)
	private String pesel;
	@Column(name = " identity_card", length = 10)
	private String  identityCard;
	@ManyToMany(mappedBy = "customers")
	List<Film> films = new ArrayList<>();

	Customer() {
	}

	public Customer(String firstName, String lastName, String pesel, String identityCard) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
		this.identityCard = identityCard;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel
				+ ", identityCard=" + identityCard + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(films, firstName, id, identityCard, lastName, pesel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(films, other.films) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(identityCard, other.identityCard)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(pesel, other.pesel);
	}



}
