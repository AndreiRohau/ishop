package by.asrohau.iShop.entity;

public class Reserve extends Base {

    private long userId;
    private long productId;

    public Reserve() {
    }

    public Reserve(long id) {
        super(id);
    }

    public Reserve(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Reserve(long id, long userId, long productId) {
        super(id);
        this.userId = userId;
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Reserve reserve = (Reserve) o;

        if (userId != reserve.userId) return false;
        return productId == reserve.productId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                super.toString() +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
