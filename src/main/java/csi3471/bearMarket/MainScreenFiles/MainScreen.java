package csi3471.bearMarket.MainScreenFiles;

import csi3471.bearMarket.AccountInformation;
import csi3471.bearMarket.AccountFiles.Account;
import csi3471.bearMarket.AccountFiles.EditAccount;
import csi3471.bearMarket.CurrentlySelling.CSReadFile;
import csi3471.bearMarket.CurrentlySelling.CurrentlySellingWindow;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.PurchaseHistory.PurchaseHistoryPanel;
import csi3471.bearMarket.UserMarketPosting.CreateMarketPostWindow;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class is the controller for the core actions of the program. It presents all of the market's items in a table
 * and displays featured items. It allows users to access most parts and features of the program
 * @auhor Richard Hutcheson
 */
public class MainScreen extends JPanel implements ActionListener{
    protected static JFrame frame;
    final private JMenu accountMenu, createPostingMenu, exit;
    final private JMenuItem editAccount, createPostingItem, purchaseHistory, currentlySelling, exitItem;
    private JLabel filterLabel;
    private JTextField filterTF;
    final private Color LIGHT_ORANGE = new Color(255, 219, 77);
    public static JTable table;
    public static DefaultTableModel tableModel;
    protected static JScrollPane scrollPane;
    private TableRowSorter sorter;
    private JButton fItem1, fItem2, fItem3;
    
    public static Account currentAccount = null;
    public static AccountInformation ai;
    FeaturedItemsDialog featuredItemsDialog;

    /**
     * MainScreen constructor holds the core code to initialize most MainScreen members
     */
    MainScreen(){
        super(new BorderLayout());
        ProgLogger.LOGGER.info("Starting main screen dialog");

        //MENUBAR
        JMenuBar menuBar = new JMenuBar();
        //exit
        exit = new JMenu("Exit");
        exitItem = new JMenuItem("Exit BearMarket");
        exitItem.addActionListener(this);
        exit.add(exitItem);
        //acount items
        accountMenu = new JMenu("Account");
        editAccount = new JMenuItem("Edit Account");
        editAccount.addActionListener(this);
        purchaseHistory = new JMenuItem("Purchase History");
        purchaseHistory.addActionListener(this);
        currentlySelling = new JMenuItem("Currently Selling");
        currentlySelling.addActionListener(this);
        //user market posting items
        createPostingMenu = new JMenu("Create Posting");
        createPostingItem = new JMenuItem("create a new market post");
        createPostingItem.addActionListener(this);


        //MENU BAR SEPARATORS
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
        ProgLogger.LOGGER.info("Created Menu Bar");


        //CENTER PANEL and TOP-CENTER PANEL
        JPanel topCenterPanel = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(topCenterPanel, BoxLayout.Y_AXIS);
        topCenterPanel.setLayout(boxLayout2);
        topCenterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "FEATURED ITEMS", TitledBorder.CENTER, TitledBorder.TOP));
        JPanel centerPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(boxLayout);

        //PRODUCT TABLE
        JPanel tablePanel = new JPanel(new BorderLayout());

        ProductTable.createTable(); //set up table and scroll pane
        tablePanel.add(scrollPane);
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "PRODUCTS", TitledBorder.CENTER, TitledBorder.TOP));
        ProgLogger.LOGGER.info("Created Product Table");

        //FEATURED ITEMS
        featuredItemsDialog = new FeaturedItemsDialog(); //RANDOMLY CREATES THREE ITEMS
        JPanel featuredItemsPanel = new JPanel();
        //featured item buttons
        //                       item 1
        fItem1 = new JButton(featuredItemsDialog.getItem1Name());
        fItem1.setPreferredSize(new Dimension(150, 50));
        fItem1.addActionListener(this);
        fItem1.setToolTipText(featuredItemsDialog.getItem1Name()); //set tool tip for button
        //                       item 2
        fItem2 = new JButton(featuredItemsDialog.getItem2Name());
        fItem2.setPreferredSize(new Dimension(150, 50));
        fItem2.addActionListener(this);
        fItem2.setToolTipText(featuredItemsDialog.getItem2Name()); //set tool tip for button
        //                       item 3
        fItem3 = new JButton(featuredItemsDialog.getItem3Name());
        fItem3.setPreferredSize(new Dimension(150, 50));
        fItem3.addActionListener(this);
        fItem3.setToolTipText(featuredItemsDialog.getItem3Name()); //set tool tip for button

        featuredItemsPanel.add(fItem1);
        featuredItemsPanel.add(fItem2);
        featuredItemsPanel.add(fItem3);

        topCenterPanel.add(featuredItemsPanel);
        centerPanel.add(topCenterPanel);
        centerPanel.add(tablePanel);
        ProgLogger.LOGGER.info("Created Featured Items Section");

        //PRODUCT FILTER
        JPanel filterPanel = new JPanel();
        filterLabel = new JLabel("SEARCH: ");
        filterTF = new JTextField(20);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        filterTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(filterTF.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(filterTF.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(filterTF.getText());
            }
            public void search(String str) {
                ProgLogger.LOGGER.info("Filtering: \"" + str + "\"");
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str)); //case in-sensitive regex
                }
            }
        });
        filterPanel.add(filterLabel);
        filterPanel.add(filterTF);
        filterPanel.setBorder(new LineBorder(LIGHT_ORANGE, 3, true));
        centerPanel.add(filterPanel);
        ProgLogger.LOGGER.info("Created Filter Section");


        //ADD CENTER PANEL
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
        ProgLogger.LOGGER.info("Main Screen Dialog Completed");
    }

    /**
     * Determines what to user with user input
     * @param e ActionEvent variable that tells MainScreen what the user is attempting to click on
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == currentlySelling){
            ProgLogger.LOGGER.info("Currently Selling Dialog Called");
            CurrentlySellingWindow.createAndShowCurrentlySelling();
        }
        if (e.getSource() == editAccount){
            ProgLogger.LOGGER.info("Edit Account Dialog Called");
            new EditAccount(currentAccount);
        }
        if (e.getSource() == purchaseHistory){
            ProgLogger.LOGGER.info("Purchase History Dialog Called");
            PurchaseHistoryPanel.createAndShowPurchaseHistory();
        }
        if (e.getSource() == createPostingItem){
            ProgLogger.LOGGER.info("Create MarketPost Dialog Called");
            CreateMarketPostWindow.createMarketPost();
        }
        if (e.getSource() == fItem1 ){
            ProgLogger.LOGGER.info("Dialog for Featured Item One Called");
            featuredItemsDialog.createFIDialog(1);
        }
        if (e.getSource() == fItem2){
            ProgLogger.LOGGER.info("Dialog for Featured Item Two Called");
            featuredItemsDialog.createFIDialog(2);
        }
        if (e.getSource() == fItem3){
            ProgLogger.LOGGER.info("Dialog for Featured Item Three Called");
            featuredItemsDialog.createFIDialog(3);
        }

        if (e.getSource() == exitItem){
            ProgLogger.LOGGER.info("Exit Button clicked");
            int dialogResult = JOptionPane.showConfirmDialog(null, "Exit Application?", "Exit", JOptionPane.YES_NO_OPTION);
            //user selects 'yes' option to exit application
            if (dialogResult == JOptionPane.YES_OPTION){
                ProgLogger.LOGGER.info("User Confirmed Exit Action");
                
                ProductTable.saveToFile();
                ai.saveFile();
                
                frame.dispose();
                System.exit(0);
            }
        }
    }

    /**
     * Creates JFrame for main screen and sets it up
     * @param account Account of the user who has logged in for this instance
     */
    public static void createAndShowGUI(Account account){
        ProgLogger.LOGGER.info("Main Screen dialog called and Account obj passed through");
        //the account that's logged in currently
        currentAccount = account;
        //Create Account Information
        frame = new JFrame("BearMarket: Main Screen");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Re-add Exit without saving?
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.add(new MainScreen()); //Add content to the window.
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ai = new AccountInformation(currentAccount);
        ai.readFile();
        ProgLogger.LOGGER.info("Main Screen GUI Displayed");
    }
}
