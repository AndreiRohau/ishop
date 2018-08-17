package by.asrohau.iShop.entity;

public class Reserve extends Base {

    private long RUserId;
    private long RProductId;

    public Reserve() {
    }

    public Reserve(long id) {
        super(id);
    }

    public Reserve(long RUserId, long RProductId) {
        this.RUserId = RUserId;
        this.RProductId = RProductId;
    }

    public Reserve(long id, long RUserId, long RProductId) {
        super(id);
        this.RUserId = RUserId;
        this.RProductId = RProductId;
    }

    public long getRUserId() {
        return RUserId;
    }

    public void setRUserId(long RUserId) {
        this.RUserId = RUserId;
    }

    public long getRProductId() {
        return RProductId;
    }

    public void setRProductId(long RProductId) {
        this.RProductId = RProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Reserve reserve = (Reserve) o;

        if (RUserId != reserve.RUserId) return false;
        return RProductId == reserve.RProductId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (RUserId ^ (RUserId >>> 32));
        result = 31 * result + (int) (RProductId ^ (RProductId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                super.toString() +
                "RUserId=" + RUserId +
                ", RProductId=" + RProductId +
                '}';
    }
}
