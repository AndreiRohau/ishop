package by.asrohau.iShop.entity;

public class Base {
    private long id;

    public Base() {
    }

    public Base(long id) {
        this.id = id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Base base = (Base) o;

        return id == base.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString(){
        return "Base{id = " + id + "}";
    }

}
