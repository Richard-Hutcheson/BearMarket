package csi3471.bearMarket.JUnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicTests {

    @BeforeEach
    public void init(){

    }
    @Test
    public void mockTest(){

    }
    @Test
    public void LoginScreenTesting(){
        /*
        b. Check that the information provided links to an account
        c. Validate an invalid account login
        d. Validate an authorized login
         */
    }

    @Test
    public void CreateAccountTesting(){
        /*
        b. Validate that each JTextField is unable to be empty for an account to be created
        c. Validate that the username hasn’t already been taken
        d. Assert true that the account has been created if it meets the specific criteria
        e. Assert that the “accountList” file can be opened
        f. Update the “accountList” file
         */
    }

    @Test
    public void EditAccountTesting(){
        /*
        b. Validate that every JTextField is unable to be empty in order for the information
        to be updated
        c. Assert that the specific account’s file can be opened. (“username”.csv)
        d. Verify that the specific accounts file has been updated with the new information
         */
    }

}
