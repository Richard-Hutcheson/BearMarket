package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.CurrentlySelling.CSProduct;
import csi3471.bearMarket.CurrentlySelling.CSTable;
import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.MainScreenFiles.MainScreen;
import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class DeletePostWindow extends JPanel implements ActionListener {

    private static JFrame frame;
    private int deleteNdxFromMainTable = 0;
    private File userFile;

    public static void DeletePostWindow(Product p, CSProduct cp) {
        DeletePostWindow mainPanel = new DeletePostWindow(p, cp);
    }

    public DeletePostWindow(Product p, CSProduct cp) {

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
            
            //Delete from table and update table
            int ndx = CSTable.csProductVector.indexOf(cp);
            CSTable.csProductVector.remove(cp);
            MainScreen.ai.setCurrentlySellingVector(CSTable.csProductVector);
            MainScreen.ai.currentlySellingProductVector.remove(ndx);
            CurrentlySellingWindow.tableModel.fireTableDataChanged();

            ArrayList<String> newFile = new ArrayList<>();

            int count = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parsed = line.split("\t");

                if (count != 0 && Integer.parseInt(parsed[6]) != p.getID()) {
                    newFile.add(line);
                }
                else {
                    deleteNdxFromMainTable = count;
                }

                count++;
            }

            FileOutputStream outFile = null;
            try {
                outFile = new FileOutputStream(productFile);
                outFile.close();
                outFile = new FileOutputStream(productFile);
                outFile.write("Product\tCategory\tDescription\tQuantity (Stock)\tRating (x/10)\tPrice\tID\n".getBytes(StandardCharsets.UTF_8));

                for (int i = 0; i < newFile.size(); i++) {
                    outFile.write(newFile.get(i).getBytes(StandardCharsets.UTF_8));
                    if (i != newFile.size() - 1)
                        outFile.write(new byte[]{'\n'});
                }

                newFile.clear();
                outFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int deleteNdx = 0;
            for (int i = 0; i < CSTable.csProductVector.size(); i++) {
                if (CSTable.csProductVector.get(i).getProductName().equals(p.getProductName())) {
                    deleteNdx = i;
                    CSTable.csProductVector.remove(i);
                    break;
                }
            }

            ProductTable.deleteItem(p, deleteNdxFromMainTable - 1);

            in.close();
            in = null;

            try {
                in = new Scanner(userFile);

                while (in.hasNextLine()) {
                    newFile.add(in.nextLine());
                }

                String[] parsed = newFile.get(1).split(",");

                String temp = "";
                for (int i = 0; i < parsed.length; i++) {
                    if (Integer.parseInt(parsed[i]) != p.getID()) {
                        temp += parsed[i] + ",";
                    }
                }

                newFile.set(1, temp);

                FileOutputStream userOutFile = new FileOutputStream(userFile);

                for (int i = 0; i < newFile.size(); i++) {
                    userOutFile.write(newFile.get(i).getBytes(StandardCharsets.UTF_8));
                    userOutFile.write(new byte[]{'\n'});
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }


            in.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
