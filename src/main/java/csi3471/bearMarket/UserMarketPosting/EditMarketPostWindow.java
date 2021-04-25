package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.CurrentlySelling.CSProduct;
import csi3471.bearMarket.CurrentlySelling.CSTable;
import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;
import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class EditMarketPostWindow extends CreateMarketPostWindow implements ActionListener {

    private CSProduct editProduct;
    private Product tempProduct;
    private int editNdx = 0;

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
        tempProduct = new Product();

        File productFile = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
        Scanner in = null;
        int searchID = editProduct.getID();
        int count = 0;

        try {
            in = new Scanner(productFile);

            while (in.hasNextLine()) {
                String parsed[] = in.nextLine().split("\t");

                if (count != 0) {

                    if (Integer.parseInt(parsed[6]) == searchID) {
                        editNdx = count;
                        tempProduct = new Product(parsed);
                        break;
                    }
                }
                count++;

            }

            // pre-fill all the text boxes
            tempTextField[0].setText(editProduct.getProductName());

            for (int i = 0; i < categories.length; i++) {
                if (categories[i].equals(tempProduct.getCategory())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }

            tempTextField[1].setText(editProduct.getDescription());
            tempTextField[2].setText(Integer.toString(editProduct.getQuantity()));
            tempTextField[3].setText(String.format("%.2f", editProduct.getPrice()));

            remove(confirmChanges);
            confirmChanges = new JButton("Confirm");
            c.gridx = 0;
            c.gridy = 6;
            confirmChanges.setPreferredSize(new Dimension(150, 20));
            add(confirmChanges, c);
            confirmChanges.addActionListener(this);

            remove(cancelChanges);
            cancelChanges = new JButton("Cancel");
            c.gridx = 1;
            c.gridy = 6;
            cancelChanges.setPreferredSize(new Dimension(150, 20));
            add(cancelChanges, c);
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

            editProduct.setProductName(tempTextField[0].getText());
            editProduct.setCategory((String)comboBox.getSelectedItem());
            editProduct.setDescription(tempTextField[1].getText());
            editProduct.setQuantity(Integer.parseInt(tempTextField[2].getText()));
            editProduct.setPrice(Double.parseDouble(tempTextField[3].getText()));

            CurrentlySellingWindow.tableModel.fireTableDataChanged();

            ProductTable.editItem(editProduct.getOriginalProduct(), editNdx - 1);

            try {
                File productFile = new File("./src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv");
                Scanner in = new Scanner(productFile);

                ArrayList<String> newFile = new ArrayList<>();

                int count = 0;
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] parsed = line.split("\t");

                    if (count != 0) {
                        if (Integer.parseInt(parsed[6]) == editProduct.getID()) {
                            Product tempProduct = editProduct.getOriginalProduct();
                            Object[] replace = new Object[]{tempProduct.getProductName(), tempProduct.getCategory(),
                                            tempProduct.getDescription(), tempProduct.getQuantity(), tempProduct.getRating(),
                                            "$" + tempProduct.getPrice(), tempProduct.getID()};
                            String temp = "";
                            for (int i = 0; i < replace.length; i++) {
                                temp += (replace[i].toString() + "\t");
                            }

                            newFile.add(temp);
                        }
                        else {
                            newFile.add(line);
                        }
                    }

                    count++;
                }

                FileOutputStream outFile = new FileOutputStream(productFile);
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
                in.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


            createMarketPostFrame.dispose();
        }

        if (e.getSource() == cancelChanges) {

            createMarketPostFrame.dispose();
        }
    }
}
