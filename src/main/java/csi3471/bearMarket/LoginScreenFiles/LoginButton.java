//Created by Noah Lambaria
package csi3471.bearMarket.LoginScreenFiles;
import csi3471.bearMarket.AccountFiles.Account;
import csi3471.bearMarket.Logging.ProgLogger;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * This class acts as a util for the logging in process of BearMarket
 * @author Noah Lambaria
 */
public class LoginButton {

    /**
     * This is the default constructor for the LoginButton, which validates
     * user input and decides if the user can log in based on the information
     * typed in.
     *
     * @param checker boolean to determine whether or not the account is valid
     * @param username the username inputted
     * @param password the password inputted
     *
     */
    public LoginButton(AtomicBoolean checker, String username, char[] password) {
        //this will contain the code needed to check if the user
        //can log in, with the boolean being the condition to be modified
        //by referenced based on if the user can login or not.

        ProgLogger.LOGGER.info("Login button pressed");
        String thePassword = new String(password);

        try {

            //reads the account list information to verify the username has not been taken
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/csi3471/bearMarket/AccountFiles/accountList.csv")));
            String line = null;
            Account a = null;
            Boolean found = false;


            //Skip the first line as it says "username, password", which is the format of the csv file
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                //if it is found
                if (split[0].equals(username)) {
                    found = true;

                    if(split[1].equals(thePassword)){
                        //then it is a valid login

                        //System.out.println("valid login");
                        checker.set(true);
                    }else{
                        //invalid password
                        checker.set(false);
                        //System.out.println("valid username, invalid password");
                    }
                }
            }

            if(!found){
                //System.out.println("Invalid username");
                checker.set(false);
            }

            //if its still false after this point, then that means the username doesn't match
            //anything in the database file, meaning that its invalid, thus returning false.


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ProgLogger.LOGGER.severe("The file was not found when clicking the login button");
        } catch (IOException e) {
            e.printStackTrace();
            ProgLogger.LOGGER.severe("The account file was not read/written in properly");
        }
    }
}
