package csi3471.bearMarket.MainScreenFiles;

import csi3471.bearMarket.ProductFiles.Product;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FeaturedItemsDialog{
    private Product item1 = null, item2 = null, item3 = null;
    FeaturedItemsDialog(){
        Random rand = new Random();
        item1 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
        item2 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
        item3 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
        System.out.println(item1.getProductName());
        System.out.println(item2.getProductName());
        System.out.println(item3.getProductName());

    }
    public void createFIDialog(int itemVal){
        final int WIDTH = 400, HEIGHT = 400;

        //Products have been assigned
        Product prod = new Product();
        if (itemVal == 1){
            prod = item1;
        }else if (itemVal == 2){
            prod = item2;
        }else if (itemVal == 3){
            prod = item3;
        }else{
            System.out.println("ERROR: product not properly assigned to featured item");
        }
        JDialog dialog = new JDialog(new JFrame(), "featured item " + itemVal);
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(), "FEATURED ITEM: " + prod.getProductName()));

//TEXT AREA
        JTextArea textArea = new JTextArea(30, 24);
        textArea.setEditable(false);
        textArea.setLineWrap(true); //enable line wrapping by the word
        textArea.setWrapStyleWord(true);
        textArea.append("Product Name: " + prod.getProductName() + "\n");
        textArea.append("Category: " + prod.getCategory() + "\n");
        textArea.append("Quantity: " + prod.getQuantity() + "\n");
        textArea.append("Rating: " + prod.getRating() + "\n");
        textArea.append("Price: " + prod.getPrice() + "\n\n");
        textArea.append("Description: " + prod.getDescription() + "\n\n");

        //SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ); //always show scroll bar
        scrollPane.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT - 100));
        //BACK BUTTON
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        //REVIEW BUTTON

        //PURCHASE BUTTON

        panel.add(scrollPane);
        panel.add(backButton);


        dialog.add(panel);
        dialog.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        dialog.pack();// try to arrange window so that it fits preferred size
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
