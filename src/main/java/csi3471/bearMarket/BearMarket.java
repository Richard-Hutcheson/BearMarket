package csi3471.bearMarket;
import javax.swing.*;

//Main form for program
public class BearMarket{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { LoginMenu.createAndShowLogin(); }
		});
	}
}
