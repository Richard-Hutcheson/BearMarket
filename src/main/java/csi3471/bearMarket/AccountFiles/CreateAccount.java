//Created by: Noah Lambaria

package csi3471.bearMarket.AccountFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class CreateAccount implements ActionListener {
    //Initialize variables within CreateAccount Class
    static JFrame createFrame;
    JLabel firstNameLabel, lastNameLabel, shipAddressLabel;
    JLabel stateLabel, zipLabel, cardNumberLabel, cvvLabel, cardZipLabel;
    JLabel usernameLabel, passwordLabel;
    JButton saveButton, backButton;

    JFormattedTextField firstNameField, lastNameField, shipAddressField;
    JFormattedTextField stateField, zipField, cardNumberField, cvvField, cardZipField;
    JFormattedTextField usernameField, passwordField;

    public CreateAccount(){
        //Create a new frame that will ask the user to input information
        createFrame = new JFrame();
        createFrame.setPreferredSize(new Dimension(400,500));
        createFrame.setVisible(true);
        //Calls function which adds the button
        createTable();
        createFrame.pack();
        createFrame.setLocationRelativeTo(null); //centers screen, must be after .pack
    }

    //Creates the table
    public void createTable(){
        //Label to inform the user to enter the information
        JLabel inform = new JLabel("Please Enter your information below...");
        inform.setHorizontalAlignment(JLabel.CENTER);
        createFrame.add(inform,BorderLayout.NORTH);

        //create panel that will have each parameter the user can enter.
        JPanel allInformation = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //***First/Last Name Initialization
        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JFormattedTextField("");
        firstNameField.setValue("");
        firstNameField.setColumns(15);
        firstNameLabel.setLabelFor(firstNameField);
        gbc.gridx = 0;
        gbc.gridy = 0;
        allInformation.add(firstNameLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        allInformation.add(firstNameField,gbc);
        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JFormattedTextField("");
        lastNameField.setValue("");
        lastNameField.setColumns(15);
        lastNameLabel.setLabelFor(lastNameField);
        gbc.gridx = 0;
        gbc.gridy = 1;
        allInformation.add(lastNameLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        allInformation.add(lastNameField,gbc);

        //Initialize the username:
        usernameLabel = new JLabel("Username:");
        usernameField = new JFormattedTextField("");
        usernameField.setValue("");
        usernameField.setColumns(15);
        usernameLabel.setLabelFor(usernameField);
        gbc.gridx = 0;
        gbc.gridy = 2;
        allInformation.add(usernameLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        allInformation.add(usernameField,gbc);

        //Initialize the password:
        passwordLabel = new JLabel("Password:");
        passwordField = new JFormattedTextField("");
        passwordField.setValue("");
        passwordField.setColumns(15);
        passwordLabel.setLabelFor(passwordField);
        gbc.gridx = 0;
        gbc.gridy = 3;
        allInformation.add(passwordLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        allInformation.add(passwordField,gbc);

        //Initialize the shipping address:
        shipAddressLabel = new JLabel("Shipping Address:");
        shipAddressField = new JFormattedTextField("");
        shipAddressField.setValue("");
        shipAddressField.setColumns(15);
        shipAddressLabel.setLabelFor(shipAddressField);
        gbc.gridx = 0;
        gbc.gridy = 4;
        allInformation.add(shipAddressLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        allInformation.add(shipAddressField,gbc);

        //Initialize the State:
        stateLabel = new JLabel("State:");
        stateField = new JFormattedTextField("");
        stateField.setValue("");
        stateField.setColumns(15);
        stateLabel.setLabelFor(stateField);
        gbc.gridx = 0;
        gbc.gridy = 5;
        allInformation.add(stateLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        allInformation.add(stateField,gbc);


        //Initialize the Zip:
        zipLabel = new JLabel("Zipcode:");
        zipField = new JFormattedTextField("");
        zipField.setValue("");
        zipField.setColumns(15);
        zipLabel.setLabelFor(zipField);
        gbc.gridx = 0;
        gbc.gridy = 6;
        allInformation.add(zipLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        allInformation.add(zipField,gbc);


        //Initialize the Credit/Debit Card:
        cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JFormattedTextField("");
        cardNumberField.setValue("");
        cardNumberField.setColumns(15);
        cardNumberLabel.setLabelFor(cardNumberField);
        gbc.gridx = 0;
        gbc.gridy = 7;
        allInformation.add(cardNumberLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        allInformation.add(cardNumberField,gbc);


        //Initialize the CVV
        cvvLabel = new JLabel("CVV:");
        cvvField = new JFormattedTextField("");
        cvvField.setValue("");
        cvvField.setColumns(15);
        cvvLabel.setLabelFor(cvvField);
        gbc.gridx = 0;
        gbc.gridy = 8;
        allInformation.add(cvvLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        allInformation.add(cvvField,gbc);

        //Initialize the Card Zip
        cardZipLabel = new JLabel("Card Zipcode:");
        cardZipField = new JFormattedTextField("");
        cardZipField.setValue("");
        cardZipField.setColumns(15);
        cardZipLabel.setLabelFor(cardZipField);
        gbc.gridx = 0;
        gbc.gridy = 9;
        allInformation.add(cardZipLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        allInformation.add(cardZipField,gbc);

        //Finally, add all of the information above to the frame.
        createFrame.add(allInformation);

        //Create the back & save button and add it to the frame.
        JPanel bottomSection = new JPanel();

        //create backButton
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100,25));
        backButton.addActionListener(this);
        //create saveButton
        saveButton = new JButton("Create");
        saveButton.setPreferredSize(new Dimension(100,25));
        saveButton.addActionListener(this);

        //add both buttons to "bottom section" panel
        bottomSection.add(backButton);
        //creates a "filler" white space between both buttons, which is pretty cool :)
        bottomSection.add(Box.createRigidArea(new Dimension(130,0)));
        bottomSection.add(saveButton);

        createFrame.add(bottomSection,BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == backButton) { createFrame.dispose(); }
        else if(e.getSource() == saveButton){

            JLabel informationEmptyError = new JLabel("Missing information, please fill out all information");
            JLabel userNameTakenError = new JLabel("Username taken, try another username");
            userNameTakenError.setVisible(false);
            informationEmptyError.setVisible(false);


            //Save information to an account, then add the
            //account to the database.

            try {

                //reads the account list information to verify the username has not been taken
                BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/csi3471/bearMarket/AccountFiles/accountList.csv")));
                String line = null;
                Account a = null;
                Boolean userNameTaken = false;

                //Skip the first line as it says "username, password", which is the format of the csv file
                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(",");
                    if (split[0].equals((String) usernameField.getText())) {
                        userNameTaken = true;
                    }
                }

                if (userNameTaken) {
                    userNameTakenError.setVisible(true);
                    userNameTakenError.setPreferredSize(new Dimension(200,200));
                    createFrame.add(userNameTakenError, BorderLayout.SOUTH);
                    System.out.println("USERNAME TAKEN!");
                }else{
                    System.out.println("USERNAME NOT TAKEN");
                    //Initialize a new account with the data
                    Account createdAccount = new Account((String)usernameField.getText(),(String)passwordField.getText(),
                            (String)firstNameField.getText(),(String)lastNameField.getText(),(String)shipAddressField.getText(),
                            (String)stateField.getText(),(String)zipField.getText(),(String)cardNumberField.getText(),
                            (String)cvvField.getText(),(String)cardZipField.getText());


                    //Format: username,password,firstName,lastName,address,state,zip,cardNumber,cvv,cardZip
                    //Create file and directory where the account info will be written to
                    String fileName = createdAccount.username+".csv";
                    File directory = new File("users");
                    File actualFile = new File(directory, fileName);

                    //Write information to accounts own file
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(actualFile)));
                    out.write(createdAccount.getUsername().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getPassword().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getFirstName().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getLastName().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getShippingAddress().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getState().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getZip().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getCardNumber().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getCvv().getBytes(StandardCharsets.UTF_8));
                    out.write(",".getBytes(StandardCharsets.UTF_8));
                    out.write(createdAccount.getCardZip().getBytes(StandardCharsets.UTF_8));

                    try {
                        //This writes to the accountList.csv file, updating the "master" username database
                        String newLine = "\n" + createdAccount.getUsername() + "," + createdAccount.getPassword();
                        Files.write(Paths.get("src/main/java/csi3471/bearMarket/AccountFiles/accountList.csv"), newLine.getBytes(), StandardOpenOption.APPEND);
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    //Close the files and dispose of the frame.
                    out.close();
                    reader.close();
                    createFrame.dispose();
                }

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
