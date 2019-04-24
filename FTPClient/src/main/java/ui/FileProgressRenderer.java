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
        setStringPainted(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (value == null || !(value instanceof Integer)) return null;

        if ((Integer)value == -1 ){
            setIndeterminate(true);
        } else {
            setIndeterminate(false);

            this.setValue((Integer) value);
        }
        return this;
    }

    @Override
    public void setUI(ProgressBarUI ui) {
        // TODO Auto-generated method stub
        super.setUI(new ProgressUI(this, new Color(64, 73, 105),
                new Color(152, 181, 205)));//这个颜色就是我们要设置的进度条颜色
    }
}
