package ui;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

/**
 * @author yiner
 * @since 2019-04-24
 */
public class FileProgressCellEditor extends JProgressBar implements TableCellEditor {
    public FileProgressCellEditor() {
        super(JProgressBar.HORIZONTAL, 0, 100);
        setBackground(new Color(152, 181, 205));
        setForeground(new Color(64, 73, 105));
        setSize(new Dimension(100, 9));
        setStringPainted(true);
        setValue(50);
    }

    private Integer progress;
    public Integer getProgress() {
        return progress;
    }
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Object o = table.getValueAt(row, column);
        if (o == null || !(o instanceof Integer)) return null;

        setBackground(new Color(152, 181, 205));
        setForeground(new Color(64, 73, 105));
        setSize(new Dimension(100, 10));
        //setStringPainted(true);
        setValue(50);

        int num = (int)o;
        progress = num;
        updateValue(num);
        return this;
    }

    public void updateValue(int num){
        if (num == -1 ){
            setIndeterminate(true);
        } else {
            setIndeterminate(false);

            this.setValue(num);
        }
    }

    //@Override
    public Object getCellEditorValue() {
        return progress;
    }

    @Override
    public void setUI(ProgressBarUI ui) {
        // TODO Auto-generated method stub
        super.setUI(new ProgressUI(this, new Color(64, 73, 105),
                new Color(152, 181, 205)));//这个颜色就是我们要设置的进度条颜色
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    /**
     * Call when editing a cell while clicking anther cell.
     * @return
     */
    @Override
    public boolean stopCellEditing() {
        //可以注释掉下面的fireEditingStopped();，然后在GenderEditor的构造函数中把
        //addActionListener()的注释去掉（这时请求终止编辑操作从JComboBox获得），
        //fireEditingStopped();//请求终止编辑操作从JTable获得
        return true;
    }

    @Override
    public void cancelCellEditing() {
        // do nothing
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class,l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class,l);
    }

    private void fireEditingStopped(){
        CellEditorListener listener;
        Object[]listeners = listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i++){
            if(listeners[i]== CellEditorListener.class){
                //之所以是i+1，是因为一个为CellEditorListener.class（Class对象），
                //接着的是一个CellEditorListener的实例
                listener= (CellEditorListener)listeners[i+1];
                //让changeEvent去通知编辑器已经结束编辑
                //在editingStopped方法中，JTable调用getCellEditorValue()取回单元格的值，
                //并且把这个值传递给TableValues(TableModel)的setValueAt()
                listener.editingStopped(changeEvent);
            }
        }
    }
}
