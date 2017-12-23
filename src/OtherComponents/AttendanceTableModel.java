package OtherComponents;

import Classes.Student;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A custom table model for the table of persons. 
 * @author www.codejava.net
 *
 */
public class AttendanceTableModel extends AbstractTableModel {
	private final String[] columnNames = {"No.","Student ID", "Fisrt Name","Last Name", "Status"};
	private final List<Student> listPerson = new ArrayList<>();;
        
	
	public AttendanceTableModel(List<Student> listPerson) {
		this.listPerson.addAll(listPerson);
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
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();            
        }

	@Override
	public int getRowCount() {
		return listPerson.size();
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Student person = listPerson.get(rowIndex);
		
		switch (columnIndex) {
		case 1:
				person.setStudentId((int) value);
			break;		
		case 2:
				person.setFname((String) value);
			break;
                case 3:
                                person.setLname((String) value);
                        break;
                case 4:
				person.setRollUpStatus((RollUpStatus) value);
			break;                
		}		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object returnValue = null;
		Student person = listPerson.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
				returnValue = rowIndex + 1;
			break;
		case 1:
				returnValue = person.getStudentId();
			break;
		case 2:
				returnValue = person.getFname();
			break;
		case 3:
				returnValue = person.getLname();
			break;
                case 4:
				returnValue = person.getRollUpStatus();
			break;        
		}
		
		return returnValue;
	}

        @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }	
    private Object valueOrDontHave(Object value){
        
        return value == null ? "" : value;
    }
}
