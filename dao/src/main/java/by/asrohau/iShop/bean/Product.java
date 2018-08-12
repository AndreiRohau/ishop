package by.asrohau.iShop.bean;

public class Product extends Base{

	private String company;
	private String name;
	private String type;
	private String price;
	private String description;
	private long reserveId;
	private long orderId;

	public Product() {}
	public Product(long id) {
		super(id);
	}

	public Product(long id, long reserveId) {
		super(id);
		this.reserveId = reserveId;
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

	public Product(long id, String company, String name, String type, String price, String description) {
		super(id);
		this.company = company;
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
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

	public long getReserveId() {
		return reserveId;
	}

	public void setReserveId(long reserveId) {
		this.reserveId = reserveId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Product product = (Product) o;

		if (reserveId != product.reserveId) return false;
		if (orderId != product.orderId) return false;
		if (company != null ? !company.equals(product.company) : product.company != null) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (type != null ? !type.equals(product.type) : product.type != null) return false;
		if (price != null ? !price.equals(product.price) : product.price != null) return false;
		return description != null ? description.equals(product.description) : product.description == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (company != null ? company.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (int) (reserveId ^ (reserveId >>> 32));
		result = 31 * result + (int) (orderId ^ (orderId >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Product{" +
				super.toString() +
				"company='" + company + '\'' +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", price='" + price + '\'' +
				", description='" + description + '\'' +
				", reserveId=" + reserveId +
				", orderId=" + orderId +
				'}';
	}
}