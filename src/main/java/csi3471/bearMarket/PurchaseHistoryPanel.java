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
    
    //Populate content
    public PurchaseHistoryPanel(){
        super(new GridLayout(2,0));
        
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
        
        //TODO: Finish Table, add test data
        JTable historyTable = new JTable();
        JScrollPane historyScrollPane = new JScrollPane(historyTable);
        
        add(historyScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sellingItems) {
            //TODO: Add transition to currently selling items list
        }
        
        if(e.getSource() == mainMenu) {
            //TODO: Add transition back to main menu
        }
    }
    
    //Generate frame and GUI
    public static void createAndShowPurchaseHistory() {
        purchaseHistoryFrame = new JFrame();
        purchaseHistoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PurchaseHistoryPanel historyPanel = new PurchaseHistoryPanel();
        
        purchaseHistoryFrame.setPreferredSize(new Dimension(1000,800));
        purchaseHistoryFrame.setContentPane(historyPanel);
        
        purchaseHistoryFrame.pack();
        purchaseHistoryFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                createAndShowPurchaseHistory();
            }
        });
    }
}
