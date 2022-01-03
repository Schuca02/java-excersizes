package learn.house.models;

import java.util.Objects;

public class Guest {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;



    private String state;

    public Guest(int id, String firstName, String lastName, String email, String phoneNumber, String state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public Guest(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Guest guest = (Guest) o;
//        return Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && Objects.equals(email, guest.email);
//    }
//    @Override
//    public int hashCode(){
//        return Objects.hash(firstName, lastName, email);
//    }
}