package by.asrohau.iShop.bean;

public class Reserve extends Base {

    private int rUserId;
    private int rProductId;

    public Reserve() {
    }

    public Reserve(int rUserId, int rProductId) {
        this.rUserId = rUserId;
        this.rProductId = rProductId;
    }

    public Reserve(int id, int rUserId, int rProductId) {
        super(id);
        this.rUserId = rUserId;
        this.rProductId = rProductId;
    }

    public int getrUserId() {
        return rUserId;
    }

    public void setrUserId(int rUserId) {
        this.rUserId = rUserId;
    }

    public int getrProductId() {
        return rProductId;
    }

    public void setrProductId(int rProductId) {
        this.rProductId = rProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Reserve reserve = (Reserve) o;

        if (rUserId != reserve.rUserId) return false;
        return rProductId == reserve.rProductId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + rUserId;
        result = 31 * result + rProductId;
        return result;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                super.toString() +
                "rUserId=" + rUserId +
                ", rProductId=" + rProductId +
                '}';
    }
}
