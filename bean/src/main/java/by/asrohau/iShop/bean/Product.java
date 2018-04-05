package by.asrohau.iShop.bean;

public class Product {
	private int id;
	private String company;
	private String name;
	private String type;
	private String price;
	private String description;
	private int reserve_id;
	private int order_id;

	public Product() {}

	public Product(int id, int reserve_id) {
		this.id = id;
		this.reserve_id = reserve_id;
	}

	public Product(String company, String name, String type, String price) {
		this.company = company;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Product(String company, String name, String type, String price, String description) {
		this.company = company;
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public Product(int id, String company, String name, String type, String price, String description) {
		this.id = id;
		this.company = company;
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(int reserve_id) {
		this.reserve_id = reserve_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (id != product.id) return false;
		if (reserve_id != product.reserve_id) return false;
		if (order_id != product.order_id) return false;
		if (company != null ? !company.equals(product.company) : product.company != null) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (type != null ? !type.equals(product.type) : product.type != null) return false;
		if (price != null ? !price.equals(product.price) : product.price != null) return false;
		return description != null ? description.equals(product.description) : product.description == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (company != null ? company.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + reserve_id;
		result = 31 * result + order_id;
		return result;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", company='" + company + '\'' +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", price='" + price + '\'' +
				", description='" + description + '\'' +
				", reserve_id=" + reserve_id +
				", order_id=" + order_id +
				'}';
	}
}