import CustomExceptions.WrongOrderException;

import java.util.Objects;

public class Customer {
    public final String INVALID_ORDER = "Invalid order!";

    String firstName;
    String lastName;
    Gender gender;
    String phoneNumber;
    String address;
    int countOrders;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getCountOrders() {
        return countOrders;
    }

    public Customer(String firstName, String lastName, Gender gender, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.countOrders = 0;
    }

    public void makeOrder(Shop shop, String name, int amount){

            Order order = new Order(this, new Product(name), amount);

            boolean isAccepted = shop.getOrder(order);
            try{
                if(!isAccepted) throw new WrongOrderException(INVALID_ORDER);
                this.countOrders++;
            }catch (WrongOrderException e){
                Shop.writeLog(e.getMessage());
            }



    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName: " + firstName +
                ", lastName: " + lastName +
                ", gender: " + gender +
                ", phoneNumber: " + phoneNumber +
                ", address: " + address +
                '}';
    }
    //Customers equal if they have equal first and last name, and gender
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && gender == customer.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender);
    }
}
