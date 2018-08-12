package by.asrohau.iShop.bean;

public class Person {

    private long id;
    private String firstname;
    private String lastname;
    private int age;
    private int phoneNumber;
    private String city;

    public Person(){}

    public Person firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Person lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Person age(int age) {
        this.age = age;
        return this;
    }

    public Person phoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Person city(String city) {
        this.city = city;
        return this;
    }

}

class Main{
    public static void main(String[] args) {

        Person p = new Person().firstname("andrei").lastname("kush").age(20).city("Hague").phoneNumber(1234567);
    }

}