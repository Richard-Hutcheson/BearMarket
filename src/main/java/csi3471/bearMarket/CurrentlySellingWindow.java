package csi3471.bearMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CurrentlySellingWindow implements ActionListener {

    JFrame frame;
    JTable mainTable;
    JScrollPane mainPane;
    TableModel tableModel;
    String[] colNames = {"Product", "Category", "Description", "Quantity", "Rating", "Price"};

    public CurrentlySellingWindow() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // temporary will fix when file reading is sorted out
        Object[][] dataValues = new Object[2][8];

        // create table and table model
        tableModel = new DefaultTableModel(dataValues, colNames);
        mainTable = new JTable(tableModel);

        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setWidth(50);
        }


        // create the currently selling label
        JLabel currentlySellingLabel = new JLabel("Currently Selling Items");
        currentlySellingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(currentlySellingLabel, c);

        // create the currently selling items table
        mainTable.setPreferredSize(new Dimension(600, 300));
        mainTable.setVisible(true);
        c.gridx = 0;
        c.gridy = 1;
        mainTable.revalidate();
        mainTable.repaint();

        // initialize scroll pane
        mainPane = new JScrollPane();
        mainPane.setPreferredSize(new Dimension(600, 300));
        c.gridx = 0;
        c.gridy = 1;
        mainPane.add(mainTable, c);

        frame.add(mainPane, c);

        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
