//Created by Noah Lambaria

package csi3471.bearMarket;

import csi3471.bearMarket.MainScreenFiles.MainScreen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginMenu extends JPanel implements ActionListener{
    
    //Global variables here
    static JFrame loginScreen;
    JButton loginButton, createButton, exitButton;
    JLabel usernameLabel, passwordLabel;
	JFormattedTextField usernameField, passwordField;
	final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 30;

	//Builds the login form
	public LoginMenu() {
		super(new BorderLayout());
		//Creates the "Bear Market" Title at the top of the login-screen.
		JLabel bearMarketLabel = new JLabel("Bear Market");
		bearMarketLabel.setForeground(Color.YELLOW.darker());
		bearMarketLabel.setHorizontalAlignment(JLabel.CENTER);
		bearMarketLabel.setFont(new Font("Serif", Font.BOLD, 50));
		bearMarketLabel.setPreferredSize(new Dimension(100,100));
		add(bearMarketLabel, BorderLayout.NORTH);

		//Initialize the username:
	    usernameLabel = new JLabel("Username:");
	    usernameField = new JFormattedTextField("");
		usernameField.setValue("");
		usernameField.setColumns(15);
	    usernameLabel.setLabelFor(usernameField);
	    //Initialize the password:
	    passwordLabel = new JLabel("Password:");
	    passwordField = new JFormattedTextField("");
	    passwordField.setValue("");
	    passwordField.setColumns(15);
	    passwordLabel.setLabelFor(passwordField);

		//create a grid panel for username and pass word fields and labels
	    JPanel upPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		upPanel.add(usernameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		upPanel.add(usernameField,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		upPanel.add(passwordLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		upPanel.add(passwordField,gbc);
		//
		JPanel bodyPanel = new JPanel();
		bodyPanel.add(upPanel, BorderLayout.CENTER);
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 20, 200));
		JPanel clePanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(clePanel, BoxLayout.Y_AXIS);
		clePanel.setLayout(boxLayout);
		//Creates the login, create account, and exit buttons
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		createButton = new JButton("Create Account");
		createButton.addActionListener(this);
		createButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		exitButton.setMaximumSize(new Dimension (BUTTON_WIDTH, BUTTON_HEIGHT));
		//centers buttons over themselves
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		//creates a separator between username/password and buttons and add buttons to panel
		clePanel.add(Box.createRigidArea(new Dimension(20, BUTTON_HEIGHT / 2)));
		clePanel.add(loginButton);
		clePanel.add(Box.createRigidArea(new Dimension(20, BUTTON_HEIGHT / 3)));
		clePanel.add(createButton);
		clePanel.add(Box.createRigidArea(new Dimension(20, BUTTON_HEIGHT / 3)));
		clePanel.add(exitButton);
		//add panel to
		bodyPanel.add(clePanel);
	    //Lastly add both parts to the program.
	    add(bodyPanel);
	    //add(createAndExit, BorderLayout.SOUTH);
	    setBackground(Color.GREEN.darker().darker());


	    //Creates credit label, providing users with developers names
		JLabel south = new JLabel("Made by: Noah Lambaria, Austin Blanchard, Joshua McKone, Richard Hutcheson");
		south.setBackground(Color.GREEN.darker().darker());
		south.setPreferredSize(new Dimension(100,50));
		south.setHorizontalAlignment(JLabel.CENTER);
		south.setForeground(Color.YELLOW.darker());
		south.setFont(new Font("Serif", Font.BOLD, 15));
		add(south, BorderLayout.SOUTH);
	}

	//Login and register button handling
	public void actionPerformed(ActionEvent e) {

	    if(e.getSource() == loginButton) {

	    	//Have an if condition that says if the account is valid
			//then MainScreen.createAndShowGUI, else do nothing OR notify
			//user that the login is invalid
	        MainScreen.createAndShowGUI();
	        loginScreen.dispose();
	    }else if(e.getSource() == exitButton){
			loginScreen.dispose();
		}else if(e.getSource() == createButton){
	    	CreateAccount create = new CreateAccount();

		}
	}

	//Builds the GUI for the frame
	public static void createAndShowLogin() {
        loginScreen = new JFrame("Bear Market Login");
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LoginMenu loginMenu = new LoginMenu();
        
        loginScreen.setPreferredSize(new Dimension(800, 650));
        loginScreen.setContentPane(loginMenu);
        
        loginScreen.pack();
        loginScreen.setLocationRelativeTo(null); //centers screen, must be after .pack
        loginScreen.setVisible(true);
    }
}

//Possibly will be removed, because only need to access 1 account at a time.
class LoginDatabase{
	ArrayList<Account> accounts;
	public LoginDatabase() { accounts = new ArrayList<>(); }
	public void addAccount(Account account) { accounts.add(account); }
	public void readAccounts(){
		//Implement reading capabilities from a csv file to load the accounts
	}
}
class CreateAccount implements ActionListener {
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

		if(e.getSource() == backButton) {
			createFrame.dispose();
		}else if(e.getSource() == saveButton){
			//Save information to an account, then add the
			//account to the database.
			Account a = new Account();




		}
	}
}