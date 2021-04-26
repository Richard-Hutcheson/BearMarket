package csi3471.bearMarket.PurchaseHistory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import csi3471.bearMarket.CurrentlySelling.CSProduct;
import csi3471.bearMarket.Logging.ProgLogger;
import csi3471.bearMarket.MainScreenFiles.MainScreen;

/**
 * Table that holds the information and display render for the Purchase History table.
 * @author Josh McKone
 *
 */
public class PTable extends PurchaseHistoryPanel {
    public static Vector<PurchaseProduct> pProductVector;
    
    private static TableCellRenderer tableRenderer;
    
    /**
     * Function that's called by Purchase product window to populate the table for the window.
     */
    public static void createTable() {
        ProgLogger.LOGGER.info("Creating Purchase History table");
        
        pProductVector = new Vector<>();
        
        //Building table model
        tableModel = new DefaultTableModel() {
            final private String[] columnNames = {"Product Names","Category","Price"};
            
            @Override
            public Class<?> getColumnClass(int columnIndex){
                return switch(columnIndex) {
                    case 2 -> Float.class;
                    default -> String.class;
                };
            }
            
            @Override
            public int getRowCount() {
                return pProductVector.size();
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
                return switch(columnIndex) {
                    case 0 -> pProductVector.get(rowIndex).getProductName();
                    case 1 -> pProductVector.get(rowIndex).getCategory();
                    case 2 -> pProductVector.get(rowIndex).getPrice();
                    default -> null;
                };
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        //Building table
        purchaseHistoryTable = new JTable(tableModel);
        purchaseHistoryTable.getTableHeader().setReorderingAllowed(false); //Prevents column reordering
        purchaseHistoryTable.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        purchaseHistoryTable.addMouseListener(new JTableButtonMouseListener(purchaseHistoryTable));
        purchaseHistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        purchaseHistoryTable.setAutoCreateRowSorter(true);
        purchaseHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        ProgLogger.LOGGER.info("Currently Selling table created");
        
        pProductVector = MainScreen.ai.getPurchaseHistoryVector();
        
        //ProgLogger.LOGGER.info("Reading in account currently selling info.");
        //Read in account currently selling information
        //final String file = "users/" + MainScreen.currentAccount.getUsername() + ".csv";
        //PReadFile.readFile(file, pProductVector);
        //ProgLogger.LOGGER.info("Read in file");
    }
}
