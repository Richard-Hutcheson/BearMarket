package csi3471.bearMarket.PurchaseHistory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;

public class PurchaseHistoryPanel extends JPanel implements ActionListener {
    
    //Global variables
    protected static JFrame purchaseHistoryFrame;
    protected static JTable purchaseHistoryTable;
    private JButton sellingItems, mainMenu;
    protected static TableModel tableModel;
    
    //Populate content
    public PurchaseHistoryPanel(){
        super(new BorderLayout());
        
        JPanel MenuPanel = new JPanel(new GridLayout(2,1));
        
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
        
        MenuPanel.add(buttons);
        
        JLabel currentlySellingLabel = new JLabel("Purchase History");
        currentlySellingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        MenuPanel.add(currentlySellingLabel);
        
        add(MenuPanel, BorderLayout.NORTH);
        
        PTable.createTable();
        purchaseHistoryTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        purchaseHistoryTable.setFillsViewportHeight(true);
        purchaseHistoryTable.setVisible(true);
        purchaseHistoryTable.setEnabled(false);
        purchaseHistoryTable.setRowSelectionAllowed(false);
        
        JScrollPane historyScrollPane = new JScrollPane(purchaseHistoryTable);
        
        add(historyScrollPane, BorderLayout.CENTER);
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
        purchaseHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        PurchaseHistoryPanel historyPanel = new PurchaseHistoryPanel();
        
        purchaseHistoryFrame.setPreferredSize(new Dimension(600,400));
        purchaseHistoryFrame.setContentPane(historyPanel);
        
        purchaseHistoryFrame.pack();
        purchaseHistoryFrame.setVisible(true);
    }
}
