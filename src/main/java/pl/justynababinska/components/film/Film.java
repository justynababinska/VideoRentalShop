package pl.justynababinska.components.film;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.justynababinska.components.category.Category;
import pl.justynababinska.components.customer.Customer;

@Entity
@Table(name = "film")
public class Film implements Serializable {
	private static final long serialVersionUID = 770292183598339910L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private int quantity;
	private double price;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "film_customers", joinColumns = {
			@JoinColumn(name = "film_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", referencedColumnName = "id") })
	private List<Customer> customers = new ArrayList<>();

	public Film() {
	}

	public Film(String name, String description, int quantity, double price) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
		customer.getFilms().add(this);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, customers, description, id, name, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(category, other.category) && Objects.equals(customers, other.customers)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}

}
