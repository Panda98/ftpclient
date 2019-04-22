package model;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author yiner
 * @since 2019-04-22
 */
public class File {
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

//    private static final String[] columns={"文件名","类型","进度","按钮"};//所有的列字段

//    public File(Object[][] data){
//        super(data, columns);
//    }
//
//    @Override
//    public boolean isCellEditable(int row, int column) {
//        // TODO Auto-generated method stub
//        //重写isCellEditable方法，设置是否可以对表格进行编辑，也可以设置某行或者列，可以编辑或者不可以编辑
//        return super.isCellEditable(row, column);
//    }
//
//    @Override
//    public void setValueAt(Object arg0, int arg1, int arg2) {
//        // TODO Auto-generated method stub
//        super.setValueAt(arg0, arg1, arg2);
//    }

}
