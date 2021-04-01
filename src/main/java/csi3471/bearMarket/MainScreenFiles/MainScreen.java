package csi3471.bearMarket.MainScreenFiles;


import csi3471.bearMarket.AccountFiles.EditAccount;
import csi3471.bearMarket.CurrentlySellingWindow;
import csi3471.bearMarket.Product;
import csi3471.bearMarket.PurchaseHistoryPanel;
import csi3471.bearMarket.UserMarketPosting.CreateMarketPostWindow;
import csi3471.bearMarket.UserMarketPosting.DeletePostWindow;
import csi3471.bearMarket.UserMarketPosting.EditMarketPostWindow;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener, MenuListener{
    protected static JFrame frame;
    private JMenu accountMenu, createPostingMenu, exit;
    private JMenuItem editAccount, createPostingItem, purchaseHistory, currentlySelling, createPosting;

    protected static JTable table;
    protected static DefaultTableModel tableModel;
    protected static JScrollPane scrollPane;


    MainScreen(){
        //super();

        //MENUBAR
        JMenuBar menuBar = new JMenuBar();
        exit = new JMenu("Exit");
        exit.addMenuListener(this);
        accountMenu = new JMenu("Account");
        createPostingMenu = new JMenu("Create Posting");
        editAccount = new JMenuItem("Edit Account");
        editAccount.addActionListener(this);
        purchaseHistory = new JMenuItem("Purchase History");
        purchaseHistory.addActionListener(this);
        currentlySelling = new JMenuItem("Currently Selling");
        currentlySelling.addActionListener(this);
        createPosting = new JMenuItem("Create Posting");
        createPosting.addActionListener(this);
        createPostingItem = new JMenuItem("create a new market post");
        createPosting.addActionListener(this);
        createPostingItem.addActionListener(this);


        //SEPARATORS
        JMenu space1 = new JMenu("    ");
        space1.setEnabled(false);
        JMenu space2 = new JMenu("    ");
        space2.setEnabled(false);

        accountMenu.add(editAccount);
        accountMenu.add(purchaseHistory);
        accountMenu.add(currentlySelling);
        menuBar.add(accountMenu);
        menuBar.add(space1);
        createPostingMenu.add(createPostingItem);
        menuBar.add(createPostingMenu);
        menuBar.add(space2);
        menuBar.add(exit);
        frame.setJMenuBar(menuBar);

        JPanel tablePanel = new JPanel(new BorderLayout());

        //PRODUCT TABLE
        ProductTable.createTable(); //set up table and scroll pane

        tablePanel.add(scrollPane);
        //tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));

        add(tablePanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == currentlySelling){
            CurrentlySellingWindow.createAndShowCurrentlySelling();
        }
        if (e.getSource() == editAccount){
            new EditAccount();
        }
        if (e.getSource() == purchaseHistory){
            PurchaseHistoryPanel.createAndShowPurchaseHistory();
        }
        if (e.getSource() == createPostingItem){
            CreateMarketPostWindow.createMarketPost();
        }


    }
    public static void createAndShowGUI(){
        frame = new JFrame("BearMarket: Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.add(new MainScreen()); //Add content to the window.
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == exit){
            int dialogResult = JOptionPane.showConfirmDialog(null, "Exit Application?", "Exit", JOptionPane.YES_NO_OPTION);
            //user selects 'yes' option to exit application
            if (dialogResult == JOptionPane.YES_OPTION){ frame.dispose();}
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
