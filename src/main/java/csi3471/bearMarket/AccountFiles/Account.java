package csi3471.bearMarket.AccountFiles;

import java.util.Objects;

public class Account {
    String username, password;
    String firstName, lastName, shippingAddress, state, zip, cardNumber;
    String cvv, cardZip;

    public Account(String username, String password, String firstName, String lastName, String shippingAddress, String state, String zip, String cardNumber, String cvv, String cardZip) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingAddress = shippingAddress;
        this.state = state;
        this.zip = zip;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardZip = cardZip;
    }

    public Account(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() { return password; }
    public String getUsername() { return username; }
    public void setPassword(String password) { this.password = password; }
    public void setUsername(String username) { this.username = username; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getCardZip() { return cardZip; }
    public void setCardZip(String cardZip) { this.cardZip = cardZip; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
