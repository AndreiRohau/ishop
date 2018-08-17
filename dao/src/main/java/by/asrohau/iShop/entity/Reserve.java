package by.asrohau.iShop.entity;

public class Reserve extends Base {

    private long rUserId;
    private long rProductId;

    public Reserve() {
    }

    public Reserve(long id) {
        super(id);
    }

    public Reserve(long rUserId, long rProductId) {
        this.rUserId = rUserId;
        this.rProductId = rProductId;
    }

    public Reserve(long id, long rUserId, long rProductId) {
        super(id);
        this.rUserId = rUserId;
        this.rProductId = rProductId;
    }

    public long getrUserId() {
        return rUserId;
    }

    public void setrUserId(long rUserId) {
        this.rUserId = rUserId;
    }

    public long getrProductId() {
        return rProductId;
    }

    public void setrProductId(long rProductId) {
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
        result = 31 * result + (int) (rUserId ^ (rUserId >>> 32));
        result = 31 * result + (int) (rProductId ^ (rProductId >>> 32));
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
