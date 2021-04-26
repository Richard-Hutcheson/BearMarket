package csi3471.bearMarket.CurrentlySelling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.PurchaseHistory.PurchaseHistoryPanel;

/**
 * Window class for the currently selling information for the current user
 * @author Josh McKone
 *
 */
public class CurrentlySellingWindow extends JPanel implements ActionListener {

    protected static JFrame frame;
    protected static JTable table;
    protected static JScrollPane mainPane;
    private JButton purchaseHistory, mainMenu;
    public static DefaultTableModel tableModel;
    String[] colNames = {"Product", "Category", "Description", "Quantity", "Rating", "Price"};
    
    /**
     * Constructor that builds and populates the currently selling window
     */
    public CurrentlySellingWindow() {
        //super(new GridBagLayout());
        super(new BorderLayout());
        ProgLogger.LOGGER.info("Building Currently Selling Window");
        
        JPanel MenuPanel = new JPanel(new GridLayout(2,1));

        // create table and table model
        CSTable.createTable();

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
        
        MenuPanel.add(buttons);

        // create the currently selling label
        JLabel currentlySellingLabel = new JLabel("Currently Selling Items");
        currentlySellingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        MenuPanel.add(currentlySellingLabel);
        
        add(MenuPanel, BorderLayout.NORTH);

        // create the currently selling items table
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        table.setFillsViewportHeight(true);
        table.setVisible(true);
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);
        table.revalidate();
        table.repaint();

        // initialize scroll pane
        mainPane = new JScrollPane(table);
        add(mainPane, BorderLayout.CENTER);
    }

    /**
     * Function that detects when the user click on a menu option
     * @param e The event that the user actually clicks on.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == purchaseHistory) {
            ProgLogger.LOGGER.info("Switching to Purchase History Menu.");
            PurchaseHistoryPanel.createAndShowPurchaseHistory();
            frame.dispose();
        }
        
        if(e.getSource() == mainMenu) {
            ProgLogger.LOGGER.info("Closing Purchase History window.");
            frame.dispose();
        }
    }
    
    /**
     * Function that other functions call to display the currently selling window.
     */
    public static void createAndShowCurrentlySelling() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        CurrentlySellingWindow sellingPanel = new CurrentlySellingWindow();
        
        frame.setPreferredSize(new Dimension(600,400));
        frame.setContentPane(sellingPanel);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
