package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.CurrentlySelling.CSProduct;
import csi3471.bearMarket.CurrentlySelling.CSTable;
import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.MainScreenFiles.MainScreen;
import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.io.*;

/**
 * DeletePostWindow
 *
 * - This class is responsible for the prompting of deleting a user
 * market posting from the currently selling window
 *
 * @author Austin Blanchard
 */
public class DeletePostWindow extends JPanel {

    private static JFrame frame;
    private int deleteNdxFromMainTable = 0;
    private File userFile;

    public static void DeletePostWindow(Product p, CSProduct cp) {
        DeletePostWindow mainPanel = new DeletePostWindow(p, cp);
    }

    public DeletePostWindow(Product p, CSProduct cp) {

        // save the option result
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Market Post", JOptionPane.YES_NO_OPTION);

        ProgLogger.LOGGER.info("Delete Market Post Prompt has been called");

        if (dialogResult == JOptionPane.YES_OPTION) {
            
            //Delete from table and update table
            int ndx = CSTable.csProductVector.indexOf(cp);
            Product pT = MainScreen.ai.currentlySellingProductVector.get(ndx);
            CSTable.csProductVector.remove(cp);
            MainScreen.ai.setCurrentlySellingVector(CSTable.csProductVector);
            MainScreen.ai.currentlySellingProductVector.remove(ndx);
            CurrentlySellingWindow.tableModel.fireTableDataChanged();
            ProductTable.productVector.remove(pT);
            MainScreen.tableModel.fireTableDataChanged();
            ProgLogger.LOGGER.info("User market posting was successfully deleted");
        }
        else if (dialogResult == JOptionPane.NO_OPTION) {
            ProgLogger.LOGGER.info("User market posting was not deleted");
        }
    }

}
