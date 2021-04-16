package csi3471.bearMarket.MainScreenFiles;
import csi3471.bearMarket.AccountFiles.EditAccount;
import csi3471.bearMarket.CurrentlySellingWindow;
import csi3471.bearMarket.Product;
import csi3471.bearMarket.PurchaseHistoryPanel;
import csi3471.bearMarket.UserMarketPosting.CreateMarketPostWindow;
import csi3471.bearMarket.UserMarketPosting.DeletePostWindow;
import csi3471.bearMarket.UserMarketPosting.EditMarketPostWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener, MenuListener{
    protected static JFrame frame;
    final private JMenu accountMenu, createPostingMenu, exit;
    final private JMenuItem editAccount, createPostingItem, purchaseHistory, currentlySelling, createPosting;
    final private JButton filterButton = new JButton("Filter");
    final private JTextField filterTF = new JTextField();
    final private Color LIGHT_ORANGE = new Color(255, 219, 77);

    protected static JTable table;
    protected static DefaultTableModel tableModel;
    protected static JScrollPane scrollPane;


    MainScreen(){
        super(new BorderLayout());

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
        menuBar.setBackground(LIGHT_ORANGE);
        frame.setJMenuBar(menuBar);

        //FEATURED ITEMS
        JPanel topCenterPanel = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(topCenterPanel, BoxLayout.Y_AXIS);
        topCenterPanel.setLayout(boxLayout2);
        topCenterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "FEATURED ITEMS-WORK IN PROGRESS", TitledBorder.CENTER, TitledBorder.TOP));
        JPanel featuredItemsPanel = new JPanel();
        JButton fItem1 = new JButton("ITEM1");
        JButton fItem2 = new JButton("ITEM2");
        JButton fItem3 = new JButton("ITEM3");

        featuredItemsPanel.add(fItem1);
        featuredItemsPanel.add(fItem2);
        featuredItemsPanel.add(fItem3);
        topCenterPanel.add(featuredItemsPanel);
        JPanel centerPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(boxLayout);
        centerPanel.add(topCenterPanel);

        //PRODUCT TABLE
        JPanel tablePanel = new JPanel(new BorderLayout());
        ProductTable.createTable(); //set up table and scroll pane
        tablePanel.add(scrollPane);
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "PRODUCTS", TitledBorder.CENTER, TitledBorder.TOP));
        //tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
        centerPanel.add(tablePanel);

        add(centerPanel, BorderLayout.CENTER);

        //WEST AND EAST BORDER COLORS
        JLabel westLabel = new JLabel("");
        westLabel.setPreferredSize(new Dimension(150, 720));
        JLabel eastLabel = new JLabel("");
        eastLabel.setPreferredSize(new Dimension(150, 720));
        this.setBackground(Color.GREEN.darker().darker());
        add(westLabel, BorderLayout.WEST);
        add(eastLabel, BorderLayout.EAST);
        Border border = new LineBorder(LIGHT_ORANGE, 3, true);
        setBorder(border);
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
