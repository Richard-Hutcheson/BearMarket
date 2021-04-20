package csi3471.bearMarket.AccountFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class EditAccount implements ActionListener {

    //Initialize variables within CreateAccount Class
    static JFrame createFrame;
    JLabel firstNameLabel, lastNameLabel, shipAddressLabel;
    JLabel stateLabel, zipLabel, cardNumberLabel, cvvLabel, cardZipLabel;
    JLabel usernameLabel, passwordLabel;
    JButton saveButton, backButton;
    GridBagConstraints gbc;
    JFormattedTextField firstNameField, lastNameField, shipAddressField;
    JFormattedTextField stateField, zipField, cardNumberField, cvvField, cardZipField;
    JFormattedTextField usernameField, passwordField;
    JPanel allInformation;
    JLabel informationEmptyError2,informationEmptyError3,informationEmptyError4,informationEmptyError5,informationEmptyError6;
    JLabel informationEmptyError7,informationEmptyError8,informationEmptyError9,informationEmptyError10,informationEmptyError1;

    static Account currentAccount = null;

    public EditAccount(Account account){

        //initialize the current account
        currentAccount = account;


        //get the information associated with the account
        String fileName = currentAccount.username + ".csv";
        File directory = new File("users");
        File actualFile = new File(directory, fileName);

        try {
            BufferedReader userFileReader = new BufferedReader(new FileReader(actualFile));

            //Read the first line (which contains the account information of the user)
            String line = userFileReader.readLine();
            String[] split = line.split(",");


            //username,password,firstname,lastname,address,state,zip,card number, cvv, card zip

            //Output for testing purposes to ensure account info has been gathered successfully.
            //System.out.println("Username: " + split[0]);
            //System.out.println("Password: " + split[1]);
            //System.out.println("FirstName: " + split[2]);
            //System.out.println("LastName: " + split[3]);
            //System.out.println("Address: " + split[4]);
            //System.out.println("State: " + split[5]);
            //System.out.println("Zip: " + split[6]);
            //System.out.println("CardNumber: " + split[7]);
            //System.out.println("cvv: " + split[8]);
            //System.out.println("card zip: " + split[9]);

            currentAccount.setUsername(split[0]);
            currentAccount.setPassword(split[1]);
            currentAccount.setFirstName(split[2]);
            currentAccount.setLastName(split[3]);
            currentAccount.setShippingAddress(split[4]);
            currentAccount.setState(split[5]);
            currentAccount.setZip(split[6]);
            currentAccount.setCardNumber(split[7]);
            currentAccount.setCvv(split[8]);
            currentAccount.setCardZip(split[9]);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }






        //**************************************************************************

        //Create a new frame that will ask the user to input information
        createFrame = new JFrame();
        createFrame.setPreferredSize(new Dimension(700,600));
        createFrame.setVisible(true);
        //Calls function which adds the button
        createTable();
        createFrame.pack();
        createFrame.setLocationRelativeTo(null); //centers screen, must be after .pack
    }

    //Creates the table
    public void createTable(){
        //Label to inform the user to enter the information
        JLabel inform = new JLabel("Update your personal information");
        inform.setHorizontalAlignment(JLabel.CENTER);
        createFrame.add(inform,BorderLayout.NORTH);

        //create panel that will have each parameter the user can enter.
        allInformation = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
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

        //Initialize the password:
        passwordLabel = new JLabel("Password:");
        passwordField = new JFormattedTextField("");
        passwordField.setValue("");
        passwordField.setColumns(15);
        passwordLabel.setLabelFor(passwordField);
        gbc.gridx = 0;
        gbc.gridy = 2;
        allInformation.add(passwordLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        allInformation.add(passwordField,gbc);

        //Initialize the shipping address:
        shipAddressLabel = new JLabel("Shipping Address:");
        shipAddressField = new JFormattedTextField("");
        shipAddressField.setValue("");
        shipAddressField.setColumns(15);
        shipAddressLabel.setLabelFor(shipAddressField);
        gbc.gridx = 0;
        gbc.gridy = 3;
        allInformation.add(shipAddressLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        allInformation.add(shipAddressField,gbc);

        //Initialize the State:
        stateLabel = new JLabel("State:");
        stateField = new JFormattedTextField("");
        stateField.setValue("");
        stateField.setColumns(15);
        stateLabel.setLabelFor(stateField);
        gbc.gridx = 0;
        gbc.gridy = 4;
        allInformation.add(stateLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        allInformation.add(stateField,gbc);


        //Initialize the Zip:
        zipLabel = new JLabel("Zipcode:");
        zipField = new JFormattedTextField("");
        zipField.setValue("");
        zipField.setColumns(15);
        zipLabel.setLabelFor(zipField);
        gbc.gridx = 0;
        gbc.gridy = 5;
        allInformation.add(zipLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        allInformation.add(zipField,gbc);


        //Initialize the Credit/Debit Card:
        cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JFormattedTextField("");
        cardNumberField.setValue("");
        cardNumberField.setColumns(15);
        cardNumberLabel.setLabelFor(cardNumberField);
        gbc.gridx = 0;
        gbc.gridy = 6;
        allInformation.add(cardNumberLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        allInformation.add(cardNumberField,gbc);


        //Initialize the CVV
        cvvLabel = new JLabel("CVV:");
        cvvField = new JFormattedTextField("");
        cvvField.setValue("");
        cvvField.setColumns(15);
        cvvLabel.setLabelFor(cvvField);
        gbc.gridx = 0;
        gbc.gridy = 7;
        allInformation.add(cvvLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        allInformation.add(cvvField,gbc);

        //Initialize the Card Zip
        cardZipLabel = new JLabel("Card Zipcode:");
        cardZipField = new JFormattedTextField("");
        cardZipField.setValue("");
        cardZipField.setColumns(15);
        cardZipLabel.setLabelFor(cardZipField);
        gbc.gridx = 0;
        gbc.gridy = 8;
        allInformation.add(cardZipLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
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
        saveButton = new JButton("Edit");
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

        if(e.getSource() == backButton) {
            createFrame.dispose();
        }else if(e.getSource() == saveButton){
            //Save information to an account, then update the
            //account information to the database.


            Boolean emptyString = false;

            Component[] components = allInformation.getComponents();
            for (Component component : components) {
                if (informationEmptyError1 == component) {
                    allInformation.remove(informationEmptyError1);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError2 == component){
                    allInformation.remove(informationEmptyError2);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError3 == component){
                    allInformation.remove(informationEmptyError3);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError4 == component){
                    allInformation.remove(informationEmptyError4);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError5 == component){
                    allInformation.remove(informationEmptyError5);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError6 == component){
                    allInformation.remove(informationEmptyError6);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError7 == component){
                    allInformation.remove(informationEmptyError7);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError8 == component){
                    allInformation.remove(informationEmptyError8);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError9 == component){
                    allInformation.remove(informationEmptyError9);
                    allInformation.revalidate();
                    allInformation.repaint();
                }else if(informationEmptyError10 == component){
                    allInformation.remove(informationEmptyError10);
                    allInformation.revalidate();
                    allInformation.repaint();
                }
            }

            informationEmptyError2 = new JLabel("Missing Password, please fill out all information");
            informationEmptyError3 = new JLabel("Missing First Name, please fill out all information");
            informationEmptyError4 = new JLabel("Missing Last Name, please fill out all information");
            informationEmptyError5 = new JLabel("Missing Address, please fill out all information");
            informationEmptyError6 = new JLabel("Missing State, please fill out all information");
            informationEmptyError7 = new JLabel("Missing Zip, please fill out all information");
            informationEmptyError8 = new JLabel("Missing Card Number, please fill out all information");
            informationEmptyError9 = new JLabel("Missing CVV, please fill out all information");
            informationEmptyError10 = new JLabel("Missing Card Zip, please fill out all information");
            if(passwordField.getText().equals("")){
                //WILL NEED TO UPDATE THE ORIGINAL ACCOUNTLIST.CSV WITH THE NEW PASSWORD AS WELL
                //READING AND WRITING TO THE ACCOUNTLIST CSV.
                printEmptyError(2,informationEmptyError2);
                emptyString = true;
            }else{ removeEmptyError(2,informationEmptyError2); }
            if(firstNameField.getText().equals("")){
                printEmptyError(0,informationEmptyError3);
                emptyString = true;
            }else{ removeEmptyError(0,informationEmptyError3); }
            if(lastNameField.getText().equals("")){
                printEmptyError(1,informationEmptyError4);
                emptyString = true;
            }else{ removeEmptyError(1,informationEmptyError4); }
            if(shipAddressField.getText().equals("")){
                printEmptyError(3,informationEmptyError5);
                emptyString = true;
            }else{ removeEmptyError(3,informationEmptyError5); }
            if(stateField.getText().equals("")){
                printEmptyError(4,informationEmptyError6);
                emptyString = true;
            }else{ removeEmptyError(4,informationEmptyError6); }
            if(zipField.getText().equals("")){
                printEmptyError(5,informationEmptyError7);
                emptyString = true;
            }else{ removeEmptyError(5,informationEmptyError7); }
            if(cardNumberField.getText().equals("")){
                printEmptyError(6,informationEmptyError8);
                emptyString = true;
            }else{ removeEmptyError(6,informationEmptyError8); }
            if(cvvField.getText().equals("")){
                printEmptyError(7,informationEmptyError9);
                emptyString = true;
            }else{ removeEmptyError(7,informationEmptyError9); }
            if(cardZipField.getText().equals("")){
                printEmptyError(8,informationEmptyError10);
                emptyString = true;
            }else{ removeEmptyError(8,informationEmptyError10); }


            //If all information has been filled in, then update the folder
            if(!emptyString) {




                //first argument in parameter is just the current username logged in
                Account updatedAccount = new Account(currentAccount.username, (String) passwordField.getText(),
                        (String) firstNameField.getText(), (String) lastNameField.getText(), (String) shipAddressField.getText(),
                        (String) stateField.getText(), (String) zipField.getText(), (String) cardNumberField.getText(),
                        (String) cvvField.getText(), (String) cardZipField.getText());





                System.out.println("User logged in: " + currentAccount.getUsername());
                System.out.println("Password of user: " + currentAccount.getPassword());

            }






        }
    }
    //Fills in the account info associated with the current account
    public void fillInAccountInfo(){

    }
    //Used to print error that empty text filter
    public void printEmptyError(int y, JLabel theLabel){
        createFrame.remove(allInformation);

        theLabel.setOpaque(true);
        theLabel.setVisible(true);
        theLabel.setForeground(Color.RED);
        theLabel.setHorizontalAlignment(JLabel.CENTER);
        theLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        gbc.gridx = 2;
        gbc.gridy = y;
        allInformation.add(theLabel,gbc);
        allInformation.revalidate();
        allInformation.repaint();
        createFrame.add(allInformation);

        //Reupdate the frame
        createFrame.revalidate();
        createFrame.repaint();
    }

    public void removeEmptyError(int y, JLabel theLabel){
        createFrame.remove(allInformation);
        allInformation.remove(theLabel);

        allInformation.revalidate();
        allInformation.repaint();
        createFrame.add(allInformation);

        //Reupdate the frame
        createFrame.revalidate();
        createFrame.repaint();
    }
}