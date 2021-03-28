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
	    JPanel upPanel = new JPanel(new GridLayout(2,2));
		upPanel.add(usernameLabel);
		upPanel.add(usernameField);
		upPanel.add(passwordLabel);
		upPanel.add(passwordField);
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
		//creates a separator between username/password and buttons, and add buttons to panel
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
	}

	//Login and register button handling
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

	    if(e.getSource() == loginButton) {

	    	//Have an if condition that says if the account is valid
			//then BearMarket.createAndShowGUI, else do nothing OR notify
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

class LoginDatabase{
	ArrayList<Account> accounts;
	
	public LoginDatabase() {
        accounts = new ArrayList<>();
    }
	
	public void addAccount(Account account) {
	    accounts.add(account);
	}
}

class Account{
	String username, password;
	String firstName, lastName, shippingAddress, state, zip, cardNumber;
	String cvv, cardZip;

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

	public void createTable(){


		JLabel inform = new JLabel("Please Enter your information below...");
		inform.setHorizontalAlignment(JLabel.CENTER);
		createFrame.add(inform,BorderLayout.NORTH);


		JPanel allInformation = new JPanel();

		//***First/Last Name Section (MAKE A PANEL DEDICATED FOR THAT) ***
		JPanel firstPanel = new JPanel();
		firstNameLabel = new JLabel("First Name:");
		firstNameField = new JFormattedTextField("");
		firstNameField.setValue("");
		firstNameField.setColumns(15);
		firstNameLabel.setLabelFor(firstNameField);
		firstPanel.add(firstNameLabel);
		firstPanel.add(Box.createRigidArea(new Dimension(20,25)));
		firstPanel.add(firstNameField);
		allInformation.add(firstPanel);

		JPanel lastPanel = new JPanel();
		lastNameLabel = new JLabel("Last Name:");
		lastNameField = new JFormattedTextField("");
		lastNameField.setValue("");
		lastNameField.setColumns(15);
		lastNameLabel.setLabelFor(lastNameField);
		lastPanel.add(lastNameLabel);
		lastPanel.add(Box.createRigidArea(new Dimension(20,25)));
		lastPanel.add(lastNameField);
		allInformation.add(lastPanel);

		//***USER INFORMATION SECTION (MAKE A PANEL DEDICATED FOR THAT)***
		//Initialize the username:
		JPanel usernamePanel = new JPanel();
		usernameLabel = new JLabel("Username:");
		usernameField = new JFormattedTextField("");
		usernameField.setValue("");
		usernameField.setColumns(15);
		usernameLabel.setLabelFor(usernameField);
		usernamePanel.add(usernameLabel);
		usernamePanel.add(Box.createRigidArea(new Dimension(20,25)));
		usernamePanel.add(usernameField);
		allInformation.add(usernamePanel);

		//Initialize the password:
		JPanel passwordPanel = new JPanel();
		passwordLabel = new JLabel("Password:");
		passwordField = new JFormattedTextField("");
		passwordField.setValue("");
		passwordField.setColumns(15);
		passwordLabel.setLabelFor(passwordField);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(Box.createRigidArea(new Dimension(20,25)));
		passwordPanel.add(passwordField);
		allInformation.add(passwordPanel);

		//***ADDRESS SECTION (MAKE A PANEL DEDICATED FOR THAT)***
		//Initialize the shipping address:
		JPanel shipPanel = new JPanel();
		shipAddressLabel = new JLabel("Shipping Address:");
		shipAddressField = new JFormattedTextField("");
		shipAddressField.setValue("");
		shipAddressField.setColumns(15);
		shipAddressLabel.setLabelFor(shipAddressField);
		shipPanel.add(shipAddressLabel);
		shipPanel.add(Box.createRigidArea(new Dimension(20,25)));
		shipPanel.add(shipAddressField);
		allInformation.add(shipPanel);

		//Initialize the State:
		JPanel statePanel = new JPanel();
		stateLabel = new JLabel("State:");
		stateField = new JFormattedTextField("");
		stateField.setValue("");
		stateField.setColumns(15);
		stateLabel.setLabelFor(stateField);
		statePanel.add(stateLabel);
		statePanel.add(Box.createRigidArea(new Dimension(20,25)));
		statePanel.add(stateField);
		allInformation.add(statePanel);

		//Initialize the Zip:
		JPanel zipPanel = new JPanel();
		zipLabel = new JLabel("Zipcode:");
		zipField = new JFormattedTextField("");
		zipField.setValue("");
		zipField.setColumns(15);
		zipLabel.setLabelFor(zipField);
		zipPanel.add(zipLabel);
		zipPanel.add(Box.createRigidArea(new Dimension(20,25)));
		zipPanel.add(zipField);
		allInformation.add(zipPanel);

		//***CARD INFORMATION SECTION (MAKE A PANEL DEDICATED FOR THAT)***
		JPanel cardNumberPanel = new JPanel();
		//Initialize the Credit/Debit Card:
		cardNumberLabel = new JLabel("Card Number:");
		cardNumberField = new JFormattedTextField("");
		cardNumberField.setValue("");
		cardNumberField.setColumns(15);
		cardNumberLabel.setLabelFor(cardNumberField);
		cardNumberPanel.add(cardNumberLabel);
		cardNumberPanel.add(Box.createRigidArea(new Dimension(20,25)));
		cardNumberPanel.add(cardNumberField);
		allInformation.add(cardNumberPanel);

		//Initialize the CVV
		JPanel cvvPanel = new JPanel();
		cvvLabel = new JLabel("CVV:");
		cvvField = new JFormattedTextField("");
		cvvField.setValue("");
		cvvField.setColumns(15);
		cvvLabel.setLabelFor(cvvField);
		cvvPanel.add(cvvLabel);
		cvvPanel.add(Box.createRigidArea(new Dimension(20,25)));
		cvvPanel.add(cvvField);
		allInformation.add(cvvPanel);

		//Initialize the Card Zip
		JPanel cardZipPanel = new JPanel();
		cardZipLabel = new JLabel("Card Zipcode:");
		cardZipField = new JFormattedTextField("");
		cardZipField.setValue("");
		cardZipField.setColumns(15);
		cardZipLabel.setLabelFor(cardZipField);
		cardZipPanel.add(cardZipLabel);
		cardZipPanel.add(Box.createRigidArea(new Dimension(20,25)));
		cardZipPanel.add(cardZipField);
		allInformation.add(cardZipPanel);

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