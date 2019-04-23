package ui;

import model.State;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author yiner
 * @since 2019-04-23
 */
public class FileButtonRenderer extends JButton implements TableCellRenderer {

    private ImageIcon downloadIcon = new ImageIcon(getClass().getResource("/ui/icon/download.png"));

    public FileButtonRenderer() {
        super();
        setSize(40, 40);
        setIcon(downloadIcon);
        setBackground(Color.white);
        setBorder(null);
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        setSize(40, 40);
        setBackground(Color.white);
        setBorder(null);
        //setText((value == null) ? "" : value.toString());
        return this;
    }

}
