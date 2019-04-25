package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


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

        Object o = table.getValueAt(row, column);
        if (o == null) return null;

        if (table.getName().equals("uploadTable")) {
            setIcon(null);
            //State state = (State)value;
            if ( value.equals("IDLE")) setEnabled(false);
        }
        setSize(40, 40);
        setBackground(Color.white);
        setBorder(null);
        return this;
    }

}
