package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.CurrentlySelling.CSProduct;
import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditMarketPostWindow extends CreateMarketPostWindow {

    private CSProduct editProduct;

    public static void createEditPostWindow(CSProduct edit) {
        createMarketPostFrame = new JFrame();
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        EditMarketPostWindow mainPanel = new EditMarketPostWindow(edit);

        createMarketPostFrame.setPreferredSize(new Dimension(400, 200));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
        createMarketPostFrame.setLocationRelativeTo(null);
        createMarketPostFrame.setVisible(true);
    }

    public EditMarketPostWindow(CSProduct edit) {
        super();
        editProduct = edit;

        File productFile = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
        Scanner in = null;
        int searchID = editProduct.getID();
        int count = 0;

        try {
            in = new Scanner(productFile);

            Product tempProduct = new Product();

            while (in.hasNextLine()) {
                String parsed[] = in.nextLine().split("\t");

                if (count != 0) {

                    if (Integer.parseInt(parsed[6]) == searchID) {
                        tempProduct = new Product(parsed);
                        break;
                    }
                }
                count++;

            }

            // pre-fill all the text boxes
            tempTextField[0].setText(tempProduct.getProductName());

            for (int i = 0; i < categories.length; i++) {
                if (categories[i].equals(tempProduct.getCategory())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }

            tempTextField[1].setText(tempProduct.getDescription());
            tempTextField[2].setText(Integer.toString(tempProduct.getQuantity()));
            tempTextField[3].setText(String.format("%.2f", tempProduct.getPrice()));

            confirmChanges.addActionListener(this);
            cancelChanges.addActionListener(this);

            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmChanges) {

        }

        if (e.getSource() == cancelChanges) {

        }
    }
}
