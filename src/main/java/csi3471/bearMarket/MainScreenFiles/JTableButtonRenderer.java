package csi3471.bearMarket.MainScreenFiles;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**Detects mouse actions in regards to the JTable, used for the JButtons in the table
 * @author Richard Hutcheson
 */
class JTableButtonMouseListener extends MouseAdapter{
    private final JTable table;
    public JTableButtonMouseListener(JTable table) {
        this.table = table;
    }
    public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row    = e.getY()/table.getRowHeight();

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
            Object value = table.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton)value).doClick();
            }
        }
    }
}
/**Renders each cell in the table, implemented for the JButtons in the JTable
 * @author Richard Hutcheson
 */
class JTableButtonRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;
    public JTableButtonRenderer(TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
