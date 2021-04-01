package csi3471.bearMarket.MainScreenFiles;

import com.sun.tools.javac.Main;
import csi3471.bearMarket.ProductFiles.Product;
import csi3471.bearMarket.ProductFiles.ReadProductFile;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ProductTable extends MainScreen{
    //public static Map<Integer, Product> productMap = new HashMap<>();
    public static Vector<Product> productVector = new Vector<>();
    private static TableCellRenderer tableRenderer;
    public static void createTable(){

        tableModel = new DefaultTableModel(){
            final private String[] columnNames = {"Product Name", "Category", "Quantity", "Rating (x/10)", "Price ($)", "Description", "Purchase", "Reviews"};

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch(columnIndex){
                    case 2 -> Integer.class;
                    case 3, 4 -> Float.class;
                    case 5, 6, 7 -> JButton.class;
                    default -> String.class;
                };
            }
            @Override
            public int getRowCount() {
                return productVector.size();
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
                return switch (columnIndex) {
                    case 0 -> productVector.get(rowIndex).getProductName();
                    case 1 -> productVector.get(rowIndex).getCategory();
                    case 2 -> productVector.get(rowIndex).getQuantity();
                    case 3 -> productVector.get(rowIndex).getRating();
                    case 4 -> productVector.get(rowIndex).getPrice();
                    case 5 -> productVector.get(rowIndex).getDescButton();
                    case 6 -> productVector.get(rowIndex).getPurchase();
                    case 7 -> productVector.get(rowIndex).getReviewsButton();
                    default -> null;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false); //prevent column reordering
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        table.addMouseListener(new JTableButtonMouseListener(table));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoCreateRowSorter(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(85);

        //read in product file
        ReadProductFile.readFile("src/main/java/csi3471/bearMarket/ProductFiles/product_list.tsv", productVector);

        for (Product p : productVector){
            System.out.println(p.getID());
            //tableModel.addRow(p.returnObjects());
        }


//        for (Map.Entry<Integer, Product> m : productMap.entrySet()){
//            tableModel.addRow(m.getValue().returnObjects());
//            //System.out.println(m.getValue().toString());
//        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 400));
    }
}
//class ProductTableModel extends AbstractTableModel {
//
//    private String[] columnNames = {"Product Name", "Category", "Quantity", "Rating", "Price", "Description", "Purchase", "Reviews"};
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return switch(columnIndex){
//            case 2 -> Integer.class;
//            case 3, 4 -> Float.class;
//            default -> String.class;
//        };
//    }
//
//    @Override
//    public int getRowCount() {
//        return ;
//    }
//
//    @Override
//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    @Override
//    public String getColumnName(int column) {
//        return columnNames[column];
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return null;
//    }
//
//    public void addRow(Product product){
//
//    }
//}
