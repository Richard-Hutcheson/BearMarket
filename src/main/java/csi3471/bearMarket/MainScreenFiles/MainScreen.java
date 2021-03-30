package csi3471.bearMarket.MainScreenFiles;

import csi3471.bearMarket.CurrentlySellingWindow;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener, MenuListener {
    private static JFrame frame;
    private JMenu editAccount;
    private JMenu purchaseHistory;
    private JMenu currentlySelling;
    private JMenu createPosting;
    private JMenu exit;
    protected static JTable table;
    protected static TableModel tableModel;
    protected static JScrollPane scrollPane;

    MainScreen(){
        super(new FlowLayout());

        //MENUBAR
        JMenuBar menuBar = new JMenuBar();
        editAccount = new JMenu("Edit Account");
        purchaseHistory = new JMenu("Purchase History");
        currentlySelling = new JMenu("Currently Selling");
        currentlySelling.addMenuListener(this);
        createPosting = new JMenu("Create Posting");
        exit = new JMenu("Exit");
        exit.addMenuListener(this);
        //SEPARATORS
        JMenu space1 = new JMenu("    ");
        space1.setEnabled(false);
        JMenu space2 = new JMenu("    ");
        space2.setEnabled(false);
        JMenu space3 = new JMenu("    ");
        space3.setEnabled(false);
        JMenu space4 = new JMenu("    ");
        space4.setEnabled(false);

        menuBar.add(editAccount);
        menuBar.add(space1);
        menuBar.add(purchaseHistory);
        menuBar.add(space2);
        menuBar.add(currentlySelling);
        menuBar.add(space3);
        menuBar.add(createPosting);
        menuBar.add(space4);
        menuBar.add(exit);
        frame.setJMenuBar(menuBar);
        tableModel = new ProductTableModel();
        ProductTable.createTable(); //set up table and scroll pane

        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
            if (dialogResult == JOptionPane.YES_OPTION){ frame.dispose(); }
        }
        if (e.getSource() == currentlySelling){
            new CurrentlySellingWindow();
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
