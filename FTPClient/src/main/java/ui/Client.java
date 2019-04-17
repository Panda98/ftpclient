package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author yiner
 * @since 2019-04-17
 */
public class Client {

    public Client() {
        initComponents();
        initComponents();
        initComponents();
        initComponents();
        initComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yiner
        mainPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        Download = new JPanel();
        infoBar1 = new JPanel();
        fileSize = new JLabel();
        fileNumber = new JLabel();
        status = new JLabel();
        fileList1 = new JList();
        filePath1 = new JPanel();
        Upload = new JPanel();
        fileList2 = new JList();
        filePath2 = new JPanel();
        fileChooser = new JPanel();
        fileSelect = new JPanel();
        addFilesOrDropTextPane = new JTextPane();
        infoBar2 = new JPanel();
        fileSize2 = new JLabel();
        fileNumber2 = new JLabel();
        status2 = new JLabel();

        //======== mainPanel ========
        {
            mainPanel.setBackground(Color.white);
            mainPanel.setInheritsPopupMenu(false);
            mainPanel.setMinimumSize(new Dimension(400, 300));
            mainPanel.setPreferredSize(new Dimension(500, 400));
            mainPanel.setVisible(true);

            // JFormDesigner evaluation mark
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), mainPanel.getBorder())); mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            mainPanel.setLayout(new BorderLayout());

            //======== tabbedPane1 ========
            {
                tabbedPane1.setBackground(Color.white);
                tabbedPane1.setFocusable(true);
                tabbedPane1.setToolTipText("");

                //======== Download ========
                {
                    Download.setBackground(Color.white);
                    Download.setEnabled(true);
                    Download.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));

                    //======== infoBar1 ========
                    {
                        infoBar1.setBackground(new Color(234, 245, 255));
                        infoBar1.setForeground(new Color(234, 245, 255));
                        infoBar1.setOpaque(true);
                        infoBar1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //---- fileSize ----
                        fileSize.setForeground(new Color(152, 181, 205));
                        fileSize.setOpaque(false);
                        fileSize.setText("5MB");
                        fileSize.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar1.add(fileSize, new GridConstraints(0, 2, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- fileNumber ----
                        fileNumber.setForeground(new Color(152, 181, 205));
                        fileNumber.setOpaque(false);
                        fileNumber.setText("8 files");
                        fileNumber.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar1.add(fileNumber, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status ----
                        status.setForeground(new Color(152, 181, 205));
                        status.setOpaque(false);
                        status.setText("downloading");
                        status.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar1.add(status, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));
                    }
                    Download.add(infoBar1, new GridConstraints(4, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    Download.add(fileList1, new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        null, new Dimension(150, 50), null));

                    //======== filePath1 ========
                    {
                        filePath1.setBackground(Color.white);
                        filePath1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
                    }
                    Download.add(filePath1, new GridConstraints(0, 0, 3, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                }
                tabbedPane1.addTab("Download", Download);

                //======== Upload ========
                {
                    Upload.setBackground(Color.white);
                    Upload.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));

                    //---- fileList2 ----
                    fileList2.setModel(new AbstractListModel() {
                        String[] values = {

                        };
                        public int getSize() { return values.length; }
                        public Object getElementAt(int i) { return values[i]; }
                    });
                    Upload.add(fileList2, new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        null, new Dimension(482, 50), null));

                    //======== filePath2 ========
                    {
                        filePath2.setBackground(Color.white);
                        filePath2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
                    }
                    Upload.add(filePath2, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(24, 44), null));

                    //======== fileChooser ========
                    {
                        fileChooser.setBackground(Color.white);
                        fileChooser.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //======== fileSelect ========
                        {
                            fileSelect.setBackground(Color.white);
                            fileSelect.setBorder(new TitledBorder(new LineBorder(new Color(152, 181, 205)), ""));
                            fileSelect.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

                            //---- addFilesOrDropTextPane ----
                            addFilesOrDropTextPane.setBackground(Color.white);
                            addFilesOrDropTextPane.setEditable(false);
                            addFilesOrDropTextPane.setFont(addFilesOrDropTextPane.getFont().deriveFont(20f));
                            addFilesOrDropTextPane.setForeground(new Color(152, 181, 205));
                            addFilesOrDropTextPane.setInheritsPopupMenu(false);
                            addFilesOrDropTextPane.setMargin(new Insets(0, 0, 0, 0));
                            addFilesOrDropTextPane.setText("Add files or drop files to upload");
                            fileSelect.add(addFilesOrDropTextPane, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                                null, new Dimension(150, 60), null));
                        }
                        fileChooser.add(fileSelect, new GridConstraints(1, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, new Dimension(482, 50), null));
                    }
                    Upload.add(fileChooser, new GridConstraints(1, 0, 2, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //======== infoBar2 ========
                    {
                        infoBar2.setBackground(new Color(234, 245, 255));
                        infoBar2.setForeground(new Color(234, 245, 255));
                        infoBar2.setOpaque(true);
                        infoBar2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //---- fileSize2 ----
                        fileSize2.setForeground(new Color(152, 181, 205));
                        fileSize2.setOpaque(false);
                        fileSize2.setText("5MB");
                        fileSize2.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar2.add(fileSize2, new GridConstraints(0, 2, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- fileNumber2 ----
                        fileNumber2.setForeground(new Color(152, 181, 205));
                        fileNumber2.setOpaque(false);
                        fileNumber2.setText("8 files");
                        fileNumber2.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar2.add(fileNumber2, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status2 ----
                        status2.setForeground(new Color(152, 181, 205));
                        status2.setOpaque(false);
                        status2.setText("downloading");
                        status2.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar2.add(status2, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));
                    }
                    Upload.add(infoBar2, new GridConstraints(5, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                tabbedPane1.addTab("Upload", Upload);
            }
            mainPanel.add(tabbedPane1, BorderLayout.CENTER);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yiner
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel Download;
    private JPanel infoBar1;
    private JLabel fileSize;
    private JLabel fileNumber;
    private JLabel status;
    private JList fileList1;
    private JPanel filePath1;
    private JPanel Upload;
    private JList fileList2;
    private JPanel filePath2;
    private JPanel fileChooser;
    private JPanel fileSelect;
    private JTextPane addFilesOrDropTextPane;
    private JPanel infoBar2;
    private JLabel fileSize2;
    private JLabel fileNumber2;
    private JLabel status2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
