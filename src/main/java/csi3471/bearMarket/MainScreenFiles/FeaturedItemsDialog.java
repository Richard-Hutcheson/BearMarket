package csi3471.bearMarket.MainScreenFiles;

import csi3471.bearMarket.ProductFiles.Product;

import java.util.Random;

public class FeaturedItemsDialog{
    private Product item1 = null, item2 = null, item3 = null;
    FeaturedItemsDialog(){
        Random rand = new Random();
        item1 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
        item2 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
        item3 = ProductTable.productVector.get(rand.nextInt(ProductTable.productVector.size() -1));
    }
    public void createFIDialog(){
        //Products have been assigned
        if ( item1 != null && item2 != null&& item3 != null){
            sout
        }

    }


}
