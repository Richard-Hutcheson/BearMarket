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


/**
 * EditMarketPostWindow
 *
 * - This class is responsible for editing a market posting that is currently
 * selling to the user
 *
 * - It will prompt the user to change the attributes of their product
 * and will update the currently selling menu and the main table accordingly
 *
 * @author Austin Blanchard
 */
public class EditMarketPostWindow extends CreateMarketPostWindow implements ActionListener {

    private CSProduct editProduct;
    private Product tempProduct;
    private int editNdx = 0;

    public static void createEditPostWindow(CSProduct edit) {
        createMarketPostFrame = new JFrame("Edit Market Posting");
        createMarketPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        EditMarketPostWindow mainPanel = new EditMarketPostWindow(edit);

        createMarketPostFrame.setPreferredSize(new Dimension(400, 200));
        createMarketPostFrame.setContentPane(mainPanel);

        createMarketPostFrame.pack();
        createMarketPostFrame.setLocationRelativeTo(null);
        createMarketPostFrame.setVisible(true);
    }

    /**
     * EditMarketPostWindow
     *
     * - This constructor will display the window for the user to edit their
     * market posting.
     *
     * - It will automatically prefill the text using the data from the
     * parameter.
     *
     * @param edit this is the product that will be edited by the user
     * @author Austin Blanchard
     */
    public EditMarketPostWindow(CSProduct edit) {
        super();

        ProgLogger.LOGGER.info("Edit Market Post Window has been created");

        editProduct = edit;
        tempProduct = new Product();

        int searchID = editProduct.getID();

        for (int i = 0; i < ProductTable.productVector.size(); i++) {
            if (ProductTable.productVector.get(i).getID() == searchID) {
                editNdx = i;
                tempProduct = ProductTable.productVector.get(i);
                break;
            }
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

        // readd the confirm and remove buttons to this instance of the class
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


    }

    /**
     * - This function will update the main market table and the currently
     * selling table with the edited information on the user market post.
     *
     * @author Austin Blanchard
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmChanges) {

            editProduct.setProductName(tempTextField[0].getText());
            editProduct.setCategory((String)comboBox.getSelectedItem());
            editProduct.setDescription(tempTextField[1].getText());
            editProduct.setQuantity(Integer.parseInt(tempTextField[2].getText()));
            editProduct.setPrice(Double.parseDouble(tempTextField[3].getText()));

            // add the edited item to the main product table
            ProductTable.editItem(editProduct.getOriginalProduct(), editNdx);

            // change the edited item in the currently selling menu
            int currentlySellingEditNdx = 0;
            for (int i = 0; i < MainScreen.ai.currentlySellingVector.size(); i++) {
                if (MainScreen.ai.currentlySellingVector.get(i).getID() == editProduct.getID()) {
                    currentlySellingEditNdx = i;
                    break;
                }
            }
            MainScreen.ai.currentlySellingVector.set(currentlySellingEditNdx, new CSProduct(editProduct.getID()));


            currentlySellingEditNdx = 0;
            for (int i = 0; i < MainScreen.ai.currentlySellingProductVector.size(); i++) {
                if (MainScreen.ai.currentlySellingProductVector.get(i).getID() == editProduct.getID()) {
                    currentlySellingEditNdx = i;
                    break;
                }
            }
            MainScreen.ai.currentlySellingProductVector.set(currentlySellingEditNdx, editProduct.getOriginalProduct());

            ProgLogger.LOGGER.info("User Market Post has been edited");
            createMarketPostFrame.dispose();
        }

        if (e.getSource() == cancelChanges) {
            ProgLogger.LOGGER.info("User Market Post editing changes were canceled");
            createMarketPostFrame.dispose();
        }
    }
}
