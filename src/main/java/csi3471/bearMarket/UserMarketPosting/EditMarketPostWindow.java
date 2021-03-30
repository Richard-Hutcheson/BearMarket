package csi3471.bearMarket.UserMarketPosting;

import csi3471.bearMarket.Product;

import javax.swing.*;
import java.awt.*;

public class EditMarketPostWindow extends CreateMarketPostWindow {

    private Product editProduct;

    public static void createEditPostWindow(Product edit) {
        createMarketPostFrame = new JFrame();
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        EditMarketPostWindow mainPanel = new EditMarketPostWindow(edit);

        createMarketPostFrame.setPreferredSize(new Dimension(400, 200));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
        createMarketPostFrame.setVisible(true);
    }

    public EditMarketPostWindow(Product edit) {
        super();
        editProduct = edit;

        // pre-fill all the text boxes
        tempTextField[0].setText(editProduct.getProductName());
        tempTextField[2].setText(editProduct.getDescription());
        tempTextField[3].setText(Integer.toString(editProduct.getQuantity()));
        tempTextField[4].setText(Double.toString(editProduct.getRating()));
        tempTextField[5].setText("$" + String.format(Double.toString(editProduct.getPrice()), ".2f"));

    }

}
