package csi3471.bearMarket.AccountFiles;

import javax.swing.*;

import csi3471.bearMarket.MainScreenFiles.MainScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

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
    JPasswordField passwordField;
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

            userFileReader.close();
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
        firstNameField.setValue(currentAccount.getFirstName());
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
        lastNameField.setValue(currentAccount.getLastName());
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
        passwordField = new JPasswordField(currentAccount.getPassword());
        //passwordField.setValue(currentAccount.getPassword());
        passwordField.setColumns(15);
        //temporarily turn this off
        //passwordField.setEditable(false);
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
        shipAddressField.setValue(currentAccount.getShippingAddress());
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
        stateField.setValue(currentAccount.getState());
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
        zipField.setValue(currentAccount.getZip());
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
        cardNumberField.setValue(currentAccount.getCardNumber());
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
        cvvField.setValue(currentAccount.getCvv());
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
        cardZipField.setValue(currentAccount.getCardZip());
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
        saveButton = new JButton("Save");
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


            //If all information has been filled in, then update the file with the changes
            if(!emptyString) {

                //first argument in parameter is just the current username logged in
                Account updatedAccount = new Account(currentAccount.username, (String) passwordField.getText(),
                        (String) firstNameField.getText(), (String) lastNameField.getText(), (String) shipAddressField.getText(),
                        (String) stateField.getText(), (String) zipField.getText(), (String) cardNumberField.getText(),
                        (String) cvvField.getText(), (String) cardZipField.getText());
                
                MainScreen.ai.setAccount(updatedAccount);

                //Output testing to ensure the updatedAccount is working
                //System.out.println("****************");
                //System.out.println("Username: " + updatedAccount.getUsername());
                //System.out.println("Password: " + updatedAccount.getPassword());
                //System.out.println("FirstName: " + updatedAccount.getFirstName());
                //System.out.println("LastName: " + updatedAccount.getLastName());
                //System.out.println("Address: " + updatedAccount.getShippingAddress());
                //System.out.println("State: " + updatedAccount.getState());
                //System.out.println("Zip: " + updatedAccount.getZip());
                //System.out.println("CardNumber: " + updatedAccount.getCardNumber());
                //System.out.println("cvv: " + updatedAccount.getCvv());
                //System.out.println("card zip: " + updatedAccount.getCardZip());

                //System.out.println("User logged in: " + currentAccount.getUsername());
                //System.out.println("Password of user: " + currentAccount.getPassword());


                //Format: username,password,firstName,lastName,address,state,zip,cardNumber,cvv,cardZip
                //Create file and directory where the account info will be written to
                String fileName = updatedAccount.username+".csv";
                File directory = new File("users");
                File actualFile = new File(directory, fileName);


                List<String> lines = null;
                try {
                    lines = Files.readAllLines(actualFile.toPath());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                //Format: username,password,firstName,lastName,address,state,zip,cardNumber,cvv,cardZip
                String s1 = updatedAccount.getUsername();
                String s2 = updatedAccount.getPassword();
                String s3 = updatedAccount.getFirstName();
                String s4 = updatedAccount.getLastName();
                String s5 = updatedAccount.getShippingAddress();
                String s6 = updatedAccount.getState();
                String s7 = updatedAccount.getZip();
                String s8 = updatedAccount.getCardNumber();
                String s9 = updatedAccount.getCvv();
                String s10 = updatedAccount.getCardZip();
                String comma = ",";


                //System.out.println(s1);
                String total = s1+comma+s2+comma+s3+comma+s4+comma+s5+comma+s6+comma+s7+comma+s8+comma+s9+comma+s10;
                //System.out.println("line: " + total);
                lines.set(0, total);
                try {
                    Files.write(actualFile.toPath(), lines);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                String otherLine = currentAccount.getUsername() + comma + currentAccount.getPassword();
                File accountListFile = new File("src/main/java/csi3471/bearMarket/AccountFiles/accountList.csv");
                List<String> accountListInfo = null;
                try {
                    accountListInfo = Files.readAllLines(accountListFile.toPath());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                int counter = 0;
                int officialLine = 0;
                for(String s : accountListInfo){
                    if(s.equals(otherLine)){
                        officialLine = counter;
                    }
                    counter++;
                }
                String theNewLine = updatedAccount.getUsername() + comma + updatedAccount.getPassword();
                //System.out.println("LINE IN ACCOUNT INFO: " + officialLine);
                //System.out.println(otherLine);
                accountListInfo.set(officialLine,theNewLine);
                try {
                    Files.write(accountListFile.toPath(), accountListInfo);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //lastly, close the frame and show a dialog that changes are saved
                JOptionPane.showMessageDialog(null,"Changes Saved!");
                createFrame.dispose();
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