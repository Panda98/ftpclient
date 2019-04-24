package ui;

import javafx.beans.binding.ObjectExpression;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author yiner
 * @since 2019-04-24
 */
public class FileProgressRenderer extends JProgressBar implements TableCellRenderer {
    public FileProgressRenderer() {
        super(JProgressBar.HORIZONTAL, 0, 100);
        setBackground(new Color(152, 181, 205));
        setForeground(new Color(64, 73, 105));
        setSize(new Dimension(100, 10));
        setStringPainted(true);
        setValue(50);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Object o = table.getValueAt(row, column);
        if (o == null || !(o instanceof Integer)) return null;

        setBackground(new Color(152, 181, 205));
        setForeground(new Color(64, 73, 105));
        setSize(new Dimension(100, 10));
        //setStringPainted(true);
        setValue(50);

        int num = (int)o;
        updateValue(num);
        return this;
    }

    public void updateValue(int num){
        if (num == -1 ){
            setIndeterminate(true);
        } else {
            setIndeterminate(false);
            setValue(num);
        }
    }

    @Override
    public void setUI(ProgressBarUI ui) {
        // TODO Auto-generated method stub
        super.setUI(new ProgressUI(this, new Color(64, 73, 105),
                new Color(152, 181, 205)));//这个颜色就是我们要设置的进度条颜色
    }
}
