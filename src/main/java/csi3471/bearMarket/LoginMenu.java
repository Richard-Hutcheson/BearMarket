package csi3471.bearMarket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class LoginMenu extends JPanel implements ActionListener{
    
    //Global variables here
    static JFrame loginScreen;
    JButton testButton;
	
    //Builds the login form
	public LoginMenu() {
	    super(new BorderLayout());

	    testButton = new JButton("Test");
	    testButton.addActionListener(this);

	    JPanel testPanel = new JPanel(new BorderLayout());

	    testPanel.add(testButton, BorderLayout.CENTER);

	    add(testPanel, BorderLayout.CENTER);
	}

	//Login and register button handling
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	    if(e.getSource() == testButton) {
	        BearMarket.createAndShowGUI();
	        loginScreen.dispose();
	    }
	}
	
	//Builds the GUI for the frame
	public static void createAndShowLogin() {
        loginScreen = new JFrame("Bear Market Login");
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LoginMenu loginMenu = new LoginMenu();
        
        loginScreen.setPreferredSize(new Dimension(1000, 800));
        loginScreen.setContentPane(loginMenu);
        
        loginScreen.pack();
        loginScreen.setVisible(true);
    }
}

class LoginDatabase{
	ArrayList<Login> accounts;
	
	public LoginDatabase() {
        accounts = new ArrayList<>();
    }
	
	public void addAccount(Login account) {
	    accounts.add(account);
	}
}

class Login{
	String username;
	String password;
	
	public String getPassword() {
        return password;
    }
	public String getUsername() {
        return username;
    }
	public void setPassword(String password) {
        this.password = password;
    }
	public void setUsername(String username) {
        this.username = username;
    }
}