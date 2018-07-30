package by.asrohau.iShop.bean;

public class Order extends Base {

    private int userId;
    private String productIds;
    private String userAddress;
    private String userPhone;
    private String status;

    public Order() {}

    public Order(int userId, String productIds, String userAddress, String userPhone) {
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }

    public Order(int userId, String productIds, String userAddress, String userPhone, String status) {
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
    }

    public Order(int id, int userId, String productIds, String userAddress, String userPhone, String status) {
        super(id);
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (userId != order.userId) return false;
        if (productIds != null ? !productIds.equals(order.productIds) : order.productIds != null) return false;
        if (userAddress != null ? !userAddress.equals(order.userAddress) : order.userAddress != null) return false;
        if (userPhone != null ? !userPhone.equals(order.userPhone) : order.userPhone != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        result = 31 * result + (productIds != null ? productIds.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                 super.toString() +
                "userId=" + userId +
                ", productIds='" + productIds + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
