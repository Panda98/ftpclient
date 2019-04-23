package ui;

import model.State;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * @author yiner
 * @since 2019-04-23
 */
public class FileButtonCellEditor extends JButton implements TableCellEditor{

    //EventListenerList:保存EventListener 列表的类。
    private EventListenerList listenerList = new EventListenerList();
    //ChangeEvent用于通知感兴趣的参与者事件源中的状态已发生更改。
    private ChangeEvent changeEvent = new ChangeEvent(this);

    private ImageIcon downloadIcon = new ImageIcon(getClass().getResource("/ui/icon/download.png"));
    private ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/ui/icon/pause.png"));
    private ImageIcon playIcon = new ImageIcon(getClass().getResource("/ui/icon/play.png"));
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public FileButtonCellEditor() {
        super();
        this.setSize(40, 40);
        this.setIcon(downloadIcon);
        this.setBackground(Color.white);
        this.setBorder(null);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonMouseClicked(e);
            }
        });
        this.state = State.IDLE;
    }

    private void buttonMouseClicked(MouseEvent e) {
        switch (getState()){
            case IDLE:
                this.setIcon(pauseIcon);
                setState(State.WORKING);
                break;
            case WORKING:
                this.setIcon(playIcon);
                setState(State.STOPPING);
                break;
            case STOPPING:
                this.setIcon(pauseIcon);
                setState(State.WORKING);
                break;
            default:
                this.setIcon(downloadIcon);
                break;
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        setSize(40, 40);
        setBackground(Color.white);
        setBorder(null);
        return this;
    }

    //@Override
    public Object getCellEditorValue() {
        return state;
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
