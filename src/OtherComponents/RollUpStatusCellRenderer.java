package OtherComponents;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A custom renderer for cells in the Country column.
 * @author www.codejava.net
 *
 */
public class RollUpStatusCellRenderer extends DefaultTableCellRenderer {
	
         @Override
 	public Component getTableCellRendererComponent(JTable table, Object value, 
 			boolean isSelected, boolean hasFocus, int row, int column) {
 		if (value instanceof RollUpStatus) {
 			RollUpStatus rollUpStatus = (RollUpStatus) value;
 			setText(rollUpStatus.getName());
 		}
 		 
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(Color.WHITE);
            }
 		return this;
 	}
 	
}
