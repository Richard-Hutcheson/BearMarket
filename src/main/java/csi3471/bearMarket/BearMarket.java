package csi3471.bearMarket;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.LoginScreenFiles.LoginMenu;

import javax.swing.*;
import java.io.IOException;

/**This class is initiates the startup of the program
 * @author Richard Hutcheson, Josh McKone, Austin Blanchard, Noah Lambaria
 */
//Main form for program
public class BearMarket{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * initiates Logger setup and starts the login screen to the program
			 */
			public void run() {
				try{
					ProgLogger.setup();
				}catch(IOException e){
					e.printStackTrace();
					System.err.println("Utility Logger class Unable to be setup");
				}
				LoginMenu.createAndShowLogin(); }
		});
	}
}
