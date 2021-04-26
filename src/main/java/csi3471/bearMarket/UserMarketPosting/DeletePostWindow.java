package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.CurrentlySelling.CSTable;
import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.MainScreenFiles.MainScreen;
import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class DeletePostWindow extends JPanel {

    private static JFrame frame;
    private int deleteNdxFromMainTable = 0;
    private File userFile;

    public static void DeletePostWindow(Product p) {
        DeletePostWindow mainPanel = new DeletePostWindow(p);
    }

    public DeletePostWindow(Product p) {

        userFile = new File("./users/" + MainScreen.currentAccount.getUsername() + ".csv");

        // save the option result
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete Market Post", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            File productFile = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
            Scanner in = null;
            try {
                in = new Scanner(productFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < ProductTable.productVector.size(); i++) {
                if (p.getID() == ProductTable.productVector.get(i).getID()) {
                    ProductTable.deleteItem(p, i);
                    break;
                }
            }


            in.close();
        }
    }

}
