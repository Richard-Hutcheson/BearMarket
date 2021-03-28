package csi3471.bearMarket.MainScreenFiles;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener {
    private static JFrame frame;
    private JMenu editAccount = new JMenu("Edit Account");
    private JMenu purchaseHistory;
    private JMenu currentlySelling;
    private JMenu createPosting;
    private JMenu exit;
    MainScreen(){
        //MENUBAR
        JMenuBar menuBar = new JMenuBar();
        JMenu editAccount = new JMenu("Edit Account");
        JMenu purchaseHistory = new JMenu("Purchase History");
        JMenu currentlySelling = new JMenu("Currently Selling");
        JMenu createPosting = new JMenu("Create Posting");
        JMenu exit = new JMenu("Exit");
        //SEPARATORS
        JMenu space1 = new JMenu("    ");
        space1.setEnabled(false);
        JMenu space2 = new JMenu("    ");
        space2.setEnabled(false);
        JMenu space3 = new JMenu("    ");
        space3.setEnabled(false);
        JMenu space4 = new JMenu("    ");
        space4.setEnabled(false);
        exit.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Exit Application?", "Exit", JOptionPane.YES_NO_OPTION);
                //user selects 'yes' option to exit application
                if (dialogResult == JOptionPane.YES_OPTION){ frame.dispose(); }
            }
            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void createAndShowLogin(){
        frame = new JFrame("BearMarket: Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.add(new MainScreen()); //Add content to the window.
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
