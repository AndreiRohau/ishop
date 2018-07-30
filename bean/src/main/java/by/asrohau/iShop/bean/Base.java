package by.asrohau.iShop.bean;

public class Base {
    private int id;

    public Base() {
    }

    public Base(int id) {
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Base other = (Base) o;

        if(this.id != other.id) return false;

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = this.id;

        result = prime * result + this.id;

        return result;
    }

    @Override
    public String toString(){
        return "Base{id = " + id + "}";
    }

}
