//Created by Noah Lambaria
package csi3471.bearMarket.AccountFiles;
import java.util.Objects;

/**
 * This object holds an account for the user.
 * @author Noah Lambaria
 */
public class Account {
    String username, password;
    String firstName, lastName, shippingAddress, state, zip, cardNumber;
    String cvv, cardZip;

    /**
     * Constructor for the account class
     *
     * @param username the username to log into the marketplace
     * @param password the password for credential verification and logging in
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param shippingAddress the shipping address
     * @param state the state the user currently resides
     * @param zip the zip of where the user currently resides
     * @param cardNumber the card number to use for payment
     * @param cvv the 3 whacky digits on the back for security purposes
     * @param cardZip the zip of the card to ensure non-fraudulent charges
     */
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
    /**
     * The second constructor for the account class
     *
     * @param username the username to log into the marketplace
     * @param password the password for credential verification and logging in
     */
    public Account(String username,String password){
        this.username = username;
        this.password = password;
    }

    /**
     * getter for the password
     * @return the current account's password
     */
    public String getPassword() { return password; }
    /**
     * getter for the username
     * @return the current account's username
     */
    public String getUsername() { return username; }
    /**
     * setter for the password
     * @param password the password that will modify the current account's password
     */
    public void setPassword(String password) { this.password = password; }
    /**
     * setter for the username
     * @param username the username that will modify the current account's username
     */
    public void setUsername(String username) { this.username = username; }
    /**
     * getter for the first name
     * @return the current account's first name
     */
    public String getFirstName() { return firstName; }
    /**
     * setter for the first name
     * @param firstName the first name that will modify the current account's first name attribute
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    /**
     * getter for the last name
     * @return the current account's last name
     */
    public String getLastName() { return lastName; }
    /**
     * setter for the last name name
     * @param lastName the last name that will modify the current account's last name attribute
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    /**
     * getter for the shipping address
     * @return the current account's shipping address
     */
    public String getShippingAddress() { return shippingAddress; }
    /**
     * setter for the shipping address
     * @param shippingAddress the shipping address that will modify the current account's shippingAddress attribute
     */
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    /**
     * getter for the user's state
     * @return the current account's state
     */
    public String getState() { return state; }
    /**
     * setter for the state
     * @param state the state that will modify the current account's state attribute
     */
    public void setState(String state) { this.state = state; }
    /**
     * getter for the user's zipcode
     * @return the current account's zipcode
     */
    public String getZip() { return zip; }
    /**
     * setter for the zip
     * @param zip the zip that will modify the current account's zip attribute
     */
    public void setZip(String zip) { this.zip = zip; }
    /**
     * getter for the user's credit card number
     * @return the current account's credit card number
     */
    public String getCardNumber() { return cardNumber; }
    /**
     * setter for the first name
     * @param cardNumber the card number that will modify the current account's cardNumber attribute
     */
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    /**
     * getter for the user's CVV
     * @return the current account's CVV
     */
    public String getCvv() { return cvv; }
    /**
     * setter for the CVV
     * @param cvv the cvv that will modify the current account's cvv attribute
     */
    public void setCvv(String cvv) { this.cvv = cvv; }
    /**
     * getter for the user's credit card zip
     * @return the current account's credit card zip
     */
    public String getCardZip() { return cardZip; }
    /**
     * setter for the card zip
     * @param cardZip the first name that will modify the current account's cardZip attribute
     */
    public void setCardZip(String cardZip) { this.cardZip = cardZip; }

    /**
     * Equals comparator
     * @param o the other object that will be compared with this object to determine whether or not they
     *          are the same
     */
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
