package csi3471.bearMarket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

//Main form for program
public class BearMarket extends JPanel implements ActionListener{

	//Global variables here
	JButton testButton;
	
	//Bear Market Panel
	public BearMarket() {
		super(new BorderLayout());
		
		testButton = new JButton("Test");
		testButton.addActionListener(this);
		
		JPanel testPanel = new JPanel(new BorderLayout());
		
		testPanel.add(testButton, BorderLayout.CENTER);
		
		add(testPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO: Set login screen to be startup frame
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Bear Market");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BearMarket test = new BearMarket();
		//Create Menu Bar
		frame.setContentPane(test);
		frame.setPreferredSize(new Dimension(1000, 1000));
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { createAndShowGUI(); }
		});
	}
}