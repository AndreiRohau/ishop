package by.asrohau.iShop.bean;

public class Reserve {

    private int id;
    private int user_id;
    private int product_id;

    public Reserve() {
    }

    public Reserve(int user_id, int product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Reserve(int id, int user_id, int product_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserve reserve = (Reserve) o;

        if (id != reserve.id) return false;
        if (user_id != reserve.user_id) return false;
        return product_id == reserve.product_id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + product_id;
        return result;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                '}';
    }
}
