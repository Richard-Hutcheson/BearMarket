package csi3471.bearMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CurrentlySellingWindow extends JPanel implements ActionListener {

    static JFrame frame;
    JTable mainTable;
    JScrollPane mainPane;
    JButton purchaseHistory, mainMenu;
    TableModel tableModel;
    String[] colNames = {"Product", "Category", "Description", "Quantity", "Rating", "Price"};

    public CurrentlySellingWindow() {
        //super(new GridBagLayout());
        super(new GridLayout(3,1));

        //GridBagConstraints c = new GridBagConstraints();

        //TODO: Link to account data
        // temporary will fix when file reading is sorted out
        Object[][] dataValues = {
                {"testProduct1","testCategory1","testDescription1","testQuantity1","testRating1","testPrice1"},
                {"testProduct2","testCategory2","testDescription2","testQuantity2","testRating2","testPrice2"},
                {"testProduct3","testCategory3","testDescription3","testQuantity3","testRating3","testPrice3"},
                {"testProduct4","testCategory4","testDescription4","testQuantity4","testRating4","testPrice4"}
        };

        // create table and table model
        //tableModel = new DefaultTableModel(dataValues, colNames);
        mainTable = new JTable(dataValues, colNames);

        /*
        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setWidth(50);
        }
        */

        //Button Panel
        //Button to go back to purchase history
        purchaseHistory = new JButton("View Purchase History");
        purchaseHistory.addActionListener(this);
        
        //Button to go back to main menu
        mainMenu = new JButton("Back to Main Menu");
        mainMenu.addActionListener(this);
        
        //Panel to house buttons
        JPanel buttons = new JPanel(new GridLayout(1,2));
        
        buttons.add(purchaseHistory);
        buttons.add(mainMenu);
        
        add(buttons);

        // create the currently selling label
        JLabel currentlySellingLabel = new JLabel("Currently Selling Items");
        currentlySellingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        /*
        c.gridx = 0;
        c.gridy = 0;
        */
        add(currentlySellingLabel);

        // create the currently selling items table
        mainTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        mainTable.setFillsViewportHeight(true);
        mainTable.setVisible(true);
        mainTable.setEnabled(false);
        mainTable.setRowSelectionAllowed(false);
        mainTable.revalidate();
        mainTable.repaint();

        // initialize scroll pane
        mainPane = new JScrollPane(mainTable);
        //mainPane.setPreferredSize(new Dimension(600, 300));
        /*
        c.gridx = 0;
        c.gridy = 1;
        */
        //mainPane.add(mainTable);

        add(mainPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == purchaseHistory) {
            PurchaseHistoryPanel.createAndShowPurchaseHistory();
            frame.dispose();
        }
        
        if(e.getSource() == mainMenu) {
            //TODO: Add Implementation to go back to Main Menu
            frame.dispose();
        }
    }
    
    public static void createAndShowCurrentlySelling() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        CurrentlySellingWindow sellingPanel = new CurrentlySellingWindow();
        
        frame.setPreferredSize(new Dimension(600,400));
        frame.setContentPane(sellingPanel);
        
        frame.pack();
        frame.setVisible(true);
    }
}
