package OtherComponents;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * A custom editor for cells in the Country column.
 * @author www.codejava.net
 *
 */
public class RollUpStatusCellEditor extends AbstractCellEditor 
		implements TableCellEditor, ActionListener {

	private RollUpStatus rollUpStatus;
	private List<RollUpStatus> listCountry;
	
	public RollUpStatusCellEditor(List<RollUpStatus> listCountry) {
		this.listCountry = listCountry;
	}
	
	@Override
	public Object getCellEditorValue() {
		return this.rollUpStatus;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof RollUpStatus) {
			this.rollUpStatus = (RollUpStatus) value;
		}
		
		JComboBox<RollUpStatus> comboCountry = new JComboBox<RollUpStatus>();
		
		for (RollUpStatus aCountry : listCountry) {
			comboCountry.addItem(aCountry);
		}
		
		comboCountry.setSelectedItem(rollUpStatus);
		comboCountry.addActionListener(this);
		
 		
		return comboCountry;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox<RollUpStatus> comboCountry = (JComboBox<RollUpStatus>) event.getSource();
		this.rollUpStatus = (RollUpStatus) comboCountry.getSelectedItem();
	}

}
