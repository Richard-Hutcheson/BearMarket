package csi3471.bearMarket.ProductFiles;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Product implements ActionListener {

    private String productName, category, description;
    private int quantity, ID;
    private double rating, price;
    private JButton descButton, purchaseButton, reviewsButton;

    public Product() {
        productName = "";
        category = "";
        description = "";
        quantity = 0;
        ID = 0;
        rating = 0.0;
        price = 0.0;
    }

    /*
     * String array should contain the order of the line in the CSV.
     */
    public Product(String[] a) {
        productName = a[0];
        category = a[1];
        description = a[2];
        quantity = Integer.parseInt(a[3]);
        rating = Double.parseDouble(a[4]);
        String tempPrice= a[5].substring(1, a[5].length() - 1); //remove dollar sign
        tempPrice = tempPrice.replace(".", ""); //remove '.'
        tempPrice = tempPrice.replace(",", ""); //remove ','
        price = Double.parseDouble(tempPrice);
        ID = Integer.parseInt(a[6]);
        descButton = new JButton("Description");
        descButton.addActionListener(this);
        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(this);
        reviewsButton = new JButton("Reviews");
        reviewsButton.addActionListener(this);
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public JButton getPurchase(){return purchaseButton;}
    public JButton getDescButton(){return descButton;}
    public JButton getReviewsButton(){return reviewsButton;}


    public Object[] returnObjects(){
        return new Object[] {productName, category, quantity, rating, price, descButton, purchaseButton, reviewsButton};
    }


    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", ID=" + ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && ID == product.ID && Double.compare(product.rating, rating) == 0 && Double.compare(product.price, price) == 0 && productName.equals(product.productName) && category.equals(product.category) && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, category, quantity, rating, price, descButton, purchaseButton, reviewsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == descButton){
            final int WIDTH = 400, HEIGHT = 200;
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(new EtchedBorder(), "Product Description"));

            JDialog dialog = new JDialog(new JFrame(), "");
            JTextArea textArea = new JTextArea(30, 24);
            textArea.setEditable(false);

            textArea.append(description + "\n");
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            //textArea.setText(description);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

            panel.add(scrollPane);
            dialog.add(panel);
            dialog.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            dialog.pack();// try to arrange window so that it fits preferred size
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
        if (e.getSource() == purchaseButton){
            System.out.println("purchase item");
        }
        if (e.getSource() == reviewsButton){
            System.out.println("review item");
        }
    }
}
