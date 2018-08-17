package by.asrohau.iShop.entity;

import java.sql.Date;

public class Order extends Base {

    private long userId;
    private String productIds;
    private String userAddress;
    private String userPhone;
    private String status;
    private Date dateCreated;

    public Order() {}

    public Order(long id) {
        super(id);
    }

    public Order(long userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public Order(long userId, String productIds, String userAddress, String userPhone) {
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }

    public Order(long userId, String productIds, String userAddress, String userPhone, String status) {
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
    }

    public Order(long id, long userId, String productIds, String userAddress, String userPhone, String status) {
        super(id);
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
    }

    public Order(long userId, String productIds, String userAddress, String userPhone, String status, Date dateCreated) {
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Order(long id, long userId, String productIds, String userAddress, String userPhone, String status, Date dateCreated) {
        super(id);
        this.userId = userId;
        this.productIds = productIds;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        return dateCreated != null ? dateCreated.equals(order.dateCreated) : order.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (productIds != null ? productIds.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + super.getId() +
                "userId=" + userId +
                ", productIds='" + productIds + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
