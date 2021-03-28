package csi3471.bearMarket.MainScreenFiles;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ProductTable extends MainScreen{

    public static void createTable(){
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false); //prevent column reordering

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
    }
}

class ProductTableModel extends AbstractTableModel {

    private String[] columnNames = {"Product Name", "Category", "Description", "Quantity", "Rating", "Price", "Purchase", "Reviews"};

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch(columnIndex){
            case 3 -> Integer.class;
            case 4, 5 -> Float.class;
            default -> String.class;
        };
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}