package csi3471.bearMarket.PurchaseHistory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import csi3471.bearMarket.MainScreenFiles.ProductTable;
import csi3471.bearMarket.ProductFiles.Product;

/**
 * Special Product class used for the purchase history table.
 * @author Josh McKone
 *
 */
public class PurchaseProduct {
    private String productName, category;
    private int ID;
    
    private double price;
    
    /**
     * Constructor that finds the information from the map
     * @param ID ID to look up in the product map to get pertinent information
     */
    public PurchaseProduct(int ID) {
        Product p = ProductTable.productMap.get(ID);
        
        productName = p.getProductName();
        category = p.getCategory();
        this.ID = ID;
        price = p.getPrice();
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
        PurchaseProduct other = (PurchaseProduct) obj;
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
