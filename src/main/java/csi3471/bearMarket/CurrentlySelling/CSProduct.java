package csi3471.bearMarket.CurrentlySelling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;
import csi3471.bearMarket.UserMarketPosting.DeletePostWindow;
import csi3471.bearMarket.UserMarketPosting.EditMarketPostWindow;

/**
 * Special Product class for the currently selling items.
 * @author Josh McKone
 *
 */
public class CSProduct implements ActionListener {
    
    private String productName, category;
    private int ID;
    
    private double price;
    private JButton editButton, deleteButton;
    private Product originalProduct;
    
    /**
     * Constructor that builds the item using the other item's information
     * @param ID The ID to look up in the product map to get product information.
     */
    public CSProduct(int ID) {
        Product p = ProductTable.productMap.get(ID);
        originalProduct = p;
        
        productName = p.getProductName();
        category = p.getCategory();
        this.ID = ID;
        price = p.getPrice();
        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            EditMarketPostWindow.createEditPostWindow(this);
        } 
        
        if (e.getSource() == deleteButton) {
            DeletePostWindow.DeletePostWindow(this.getOriginalProduct(), this);
        }
    }
    
    public Object[] returnObjects() {
        return new Object[] {productName, category, price, editButton, deleteButton};
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setDescription(String desc) {
        this.originalProduct.setDescription(desc);
    }

    public String getDescription() { return this.originalProduct.getDescription(); }

    public void setQuantity(int q) {
        this.originalProduct.setQuantity(q);
    }

    public int getQuantity() { return this.originalProduct.getQuantity(); }

    public Product getOriginalProduct() {
        originalProduct.setProductName(this.productName);
        originalProduct.setCategory(this.category);
        originalProduct.setPrice(this.price);
        return this.originalProduct;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ID;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CSProduct other = (CSProduct) obj;
        if (ID != other.ID)
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        return true;
    }
    
    

}
