package model;

import javax.swing.table.DefaultTableModel;

/**
 * @author yiner
 * @since 2019-04-22
 */
public class FilesDataModel extends DefaultTableModel {
    private static final String[] columns={"文件名","类型","进度","按钮"};//所有的列字段

    public FilesDataModel(Object[][] data){
        super(data, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // TODO Auto-generated method stub
        //重写isCellEditable方法，设置是否可以对表格进行编辑，也可以设置某行或者列，可以编辑或者不可以编辑
        return super.isCellEditable(row, column);
    }

    @Override
    public void setValueAt(Object arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        super.setValueAt(arg0, arg1, arg2);
    }
}
