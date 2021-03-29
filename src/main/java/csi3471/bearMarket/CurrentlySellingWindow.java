package csi3471.bearMarket;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CurrentlySellingWindow {

    JFrame frame;
    JTable mainTable;
    TableModel tableModel;
    String[] colNames = {"Product", "Category", "Description", "Quantity", "Rating", "Price"};

    public CurrentlySellingWindow() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // temporary will fix when file reading is sorted out
        Object[][] dataValues = new Object[2][8];

        tableModel = new DefaultTableModel(dataValues, colNames);
        mainTable = new JTable(tableModel);

        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            mainTable.getColumnModel().getColumn(i).setWidth(100);
        }

        mainTable.setPreferredSize(new Dimension(600, 400));
        mainTable.setVisible(true);
        mainTable.revalidate();
        mainTable.repaint();

        frame.add(mainTable);
        frame.pack();
    }

}
