package model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author yiner
 * @since 2019-04-22
 */
public class File implements TableModel {
    private String name;
    private String type;

    public File() {

    }

    public File (String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldType = this.type;
        this.type = type;
        changeSupport.firePropertyChange("type", oldType, type);
    }

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public int getRowCount() {
        return 0;
    }

    public int getColumnCount() {
        return 0;
    }

    public String getColumnName(int columnIndex) {
        return null;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addTableModelListener(TableModelListener l) {

    }

    public void removeTableModelListener(TableModelListener l) {

    }
}
