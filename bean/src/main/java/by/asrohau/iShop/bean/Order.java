package by.asrohau.iShop.bean;

public class Order {

    private int id;
    private int user_id;
    private String productIDs;
    private String user_address;
    private String user_phone;
    private String status;

    public Order() {
    }

    public Order(int user_id, String productIDs, String user_address, String user_phone) {
        this.user_id = user_id;
        this.productIDs = productIDs;
        this.user_address = user_address;
        this.user_phone = user_phone;
    }

    public Order(int user_id, String productIDs, String user_address, String user_phone, String status) {
        this.user_id = user_id;
        this.productIDs = productIDs;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.status = status;
    }

    public Order(int id, int user_id, String productIDs, String user_address, String user_phone, String status) {
        this.id = id;
        this.user_id = user_id;
        this.productIDs = productIDs;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(String productIDs) {
        this.productIDs = productIDs;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (user_id != order.user_id) return false;
        if (productIDs != null ? !productIDs.equals(order.productIDs) : order.productIDs != null) return false;
        if (user_address != null ? !user_address.equals(order.user_address) : order.user_address != null) return false;
        if (user_phone != null ? !user_phone.equals(order.user_phone) : order.user_phone != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + (productIDs != null ? productIDs.hashCode() : 0);
        result = 31 * result + (user_address != null ? user_address.hashCode() : 0);
        result = 31 * result + (user_phone != null ? user_phone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", productIDs='" + productIDs + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
