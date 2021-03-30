//Created by Noah Lambaria

package csi3471.bearMarket.LoginScreenFiles;

import csi3471.bearMarket.Account;
import csi3471.bearMarket.MainScreenFiles.MainScreen;
import csi3471.bearMarket.ProductFiles.ReadProductFile;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

public class LoginMenu extends JPanel implements ActionListener{
    
    //Global variables here
    static JFrame loginScreen;
    JButton loginButton, createButton, exitButton;
    JLabel usernameLabel, passwordLabel;
	JFormattedTextField usernameField;
	JPasswordField passwordField;
	final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 30;

	//Builds the login form
	public LoginMenu() {
		super(new BorderLayout());



		/*
		ImageIcon icon = createImageIcon("src/main/java/csi3471/bearMarket/LoginScreenFiles/bear.gif",
				"a pretty but meaningless splat");
		//URL bear = this.getClass().getResource("csi3471/bearMarket/LoginScreenFiles/bear.gif");
		//Icon beargif = new ImageIcon(bear);
		JLabel img = new JLabel(icon);
		img.setBounds(668, 43, 46, 14); // You can use your own values
		loginScreen.getContentPane().add(img);
		*/

		//Creates the "Bear Market" Title at the top of the login-screen.
		JLabel bearMarketLabel = new JLabel("Bear Market");
		bearMarketLabel.setForeground(Color.YELLOW.darker());
		bearMarketLabel.setHorizontalAlignment(JLabel.CENTER);
		bearMarketLabel.setFont(new Font("Serif", Font.BOLD, 50));
		bearMarketLabel.setPreferredSize(new Dimension(100,100));
		add(bearMarketLabel, BorderLayout.NORTH);

		//Initialize the username:
	    usernameLabel = new JLabel("Username:   ");
	    usernameField = new JFormattedTextField("");
		usernameField.setValue("");
		usernameField.setColumns(15);
	    usernameLabel.setLabelFor(usernameField);
	    //Initialize the password:
	    passwordLabel = new JLabel("Password:   ");
	    passwordField = new JPasswordField("");
	    //passwordField.setValue("");
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
		JPanel bodyPanel = new JPanel(new GridLayout(2,1));
		bodyPanel.add(upPanel);


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
		bodyPanel.add(clePanel,BorderLayout.SOUTH);
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

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
										String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
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
	public static void createAndShowLogin(){



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

