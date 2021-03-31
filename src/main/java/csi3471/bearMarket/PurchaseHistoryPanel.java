package csi3471.bearMarket;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class PurchaseHistoryPanel extends JPanel implements ActionListener {
    
    //Global variables
    static JFrame purchaseHistoryFrame;
    JTable purchaseHistoryTable;
    JButton sellingItems, mainMenu;
    String[] colNames = {"Product", "Category", "Description", "Quantity", "Rating", "Price"};
    
    //Populate content
    public PurchaseHistoryPanel(){
        super(new GridLayout(2,1));
        
        //TODO: Link to account data
        Object[][] dataValues = {
                {"testProduct1","testCategory1","testDescription1","testQuantity1","testRating1","testPrice1"},
                {"testProduct2","testCategory2","testDescription2","testQuantity2","testRating2","testPrice2"},
                {"testProduct3","testCategory3","testDescription3","testQuantity3","testRating3","testPrice3"},
                {"testProduct4","testCategory4","testDescription4","testQuantity4","testRating4","testPrice4"}
        };
        
        //TODO: Possibly make buttons into menu buttons
        //Button to take user to items currently selling
        sellingItems = new JButton("View Selling Items");
        sellingItems.addActionListener(this);
        
        //Button to take user back to main menu
        mainMenu = new JButton("Back to Main Menu");
        mainMenu.addActionListener(this);
        
        //Panel to house buttons
        JPanel buttons = new JPanel(new GridLayout(1,2));
        buttons.add(sellingItems);
        buttons.add(mainMenu);
        
        add(buttons);
        
        JTable historyTable = new JTable(dataValues, colNames);
        historyTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        historyTable.setFillsViewportHeight(true);
        historyTable.setVisible(true);
        historyTable.setEnabled(false);
        historyTable.setRowSelectionAllowed(false);
        
        JScrollPane historyScrollPane = new JScrollPane(historyTable);
        
        add(historyScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sellingItems) {
            CurrentlySellingWindow.createAndShowCurrentlySelling();
            purchaseHistoryFrame.dispose();
        }
        
        if(e.getSource() == mainMenu) {
            //TODO: Add transition back to main menu
            purchaseHistoryFrame.dispose();
        }
    }
    
    //Generate frame and GUI
    public static void createAndShowPurchaseHistory() {
        purchaseHistoryFrame = new JFrame();
        purchaseHistoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PurchaseHistoryPanel historyPanel = new PurchaseHistoryPanel();
        
        purchaseHistoryFrame.setPreferredSize(new Dimension(600,400));
        purchaseHistoryFrame.setContentPane(historyPanel);
        
        purchaseHistoryFrame.pack();
        purchaseHistoryFrame.setVisible(true);
    }
    
    //TODO: Remove test main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                createAndShowPurchaseHistory();
            }
        });
    }
}
