package csi3471.bearMarket;

import java.util.Objects;

public class Product {

    private String productName, category, description;
    private int quantity, ID;
    private double rating, price;

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
        price = Double.parseDouble(a[5]);
        ID = Integer.parseInt(a[6]);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && ID == product.ID && Double.compare(product.rating, rating) == 0 && Double.compare(product.price, price) == 0 && productName.equals(product.productName) && category.equals(product.category) && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, category, description, quantity, ID, rating, price);
    }
}
