package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author yiner
 * @since 2019-04-17
 */
public class Client {

    public Client() {
        initComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setSize(600, 500);
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setContentPane(new Client().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口居中显示
        int windowWidth = frame.getWidth(); // 获得窗口宽
        int windowHeight = frame.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        frame.setLocation(screenWidth / 2 - windowWidth / 2,
                screenHeight / 2 - windowHeight / 2 - 40); // 设置位置居中

        frame.pack();
        frame.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yiner
        mainPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        Download = new JPanel();
        filePath1 = new JPanel();
        buttonBack1 = new JButton();
        button2Root1 = new JButton();
        infoBar1 = new JPanel();
        fileSize = new JLabel();
        fileNumber = new JLabel();
        status = new JLabel();
        fileList1 = new JList();
        Upload = new JPanel();
        fileList2 = new JList();
        fileChooser = new JPanel();
        addFilesOrDropTextPane = new JLabel();
        infoBar2 = new JPanel();
        fileSize2 = new JLabel();
        fileNumber2 = new JLabel();
        status2 = new JLabel();
        filePath2 = new JPanel();
        buttonBack2 = new JButton();
        button2Root2 = new JButton();

        //======== mainPanel ========
        {
            mainPanel.setBackground(Color.white);
            mainPanel.setInheritsPopupMenu(false);
            mainPanel.setMinimumSize(new Dimension(600, 500));
            mainPanel.setPreferredSize(new Dimension(600, 500));
            mainPanel.setVisible(true);
            mainPanel.setForeground(Color.white);
            mainPanel.setMaximumSize(new Dimension(-1, -1));

            // JFormDesigner evaluation mark
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), mainPanel.getBorder())); mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            mainPanel.setLayout(new GridLayout());

            //======== tabbedPane1 ========
            {
                tabbedPane1.setBackground(Color.white);
                tabbedPane1.setFocusable(true);
                tabbedPane1.setToolTipText("");

                //======== Download ========
                {
                    Download.setBackground(Color.white);
                    Download.setEnabled(true);
                    Download.setLayout(new GridBagLayout());
                    ((GridBagLayout)Download.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)Download.getLayout()).rowHeights = new int[] {65, 105, 30, 0};
                    ((GridBagLayout)Download.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
                    ((GridBagLayout)Download.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

                    //======== filePath1 ========
                    {
                        filePath1.setBackground(Color.white);
                        filePath1.setMinimumSize(new Dimension(400, 50));
                        filePath1.setMaximumSize(new Dimension(-1, -1));
                        filePath1.setPreferredSize(new Dimension(476, 50));
                        filePath1.setAlignmentX(0.0F);
                        filePath1.setAlignmentY(0.0F);
                        filePath1.setLayout(new GridBagLayout());
                        ((GridBagLayout)filePath1.getLayout()).columnWidths = new int[] {50, 50, 46, 55, 66, 0, 0};
                        ((GridBagLayout)filePath1.getLayout()).rowHeights = new int[] {60, 0};
                        ((GridBagLayout)filePath1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)filePath1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                        //---- buttonBack1 ----
                        buttonBack1.setBackground(Color.white);
                        buttonBack1.setIcon(new ImageIcon(getClass().getResource("/ui/icon/leftRorrow.png")));
                        buttonBack1.setBorder(null);
                        buttonBack1.setMaximumSize(new Dimension(40, 40));
                        buttonBack1.setMinimumSize(new Dimension(40, 40));
                        buttonBack1.setMargin(new Insets(0, 0, 0, 0));
                        buttonBack1.setIconTextGap(0);
                        buttonBack1.setPreferredSize(new Dimension(40, 40));
                        filePath1.add(buttonBack1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //---- button2Root1 ----
                        button2Root1.setBackground(Color.white);
                        button2Root1.setIcon(new ImageIcon(getClass().getResource("/ui/icon/folder.png")));
                        button2Root1.setBorder(null);
                        button2Root1.setForeground(new Color(152, 181, 205));
                        button2Root1.setMargin(new Insets(0, 0, 0, 0));
                        button2Root1.setMaximumSize(new Dimension(40, 40));
                        button2Root1.setMinimumSize(new Dimension(40, 40));
                        button2Root1.setPreferredSize(new Dimension(40, 40));
                        filePath1.add(button2Root1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    Download.add(filePath1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 10, 5), 0, 0));

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
                    Download.add(infoBar1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- fileList1 ----
                    fileList1.setMaximumSize(new Dimension(40, 60));
                    fileList1.setMinimumSize(new Dimension(40, 60));
                    Download.add(fileList1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                tabbedPane1.addTab("Download", new ImageIcon(getClass().getResource("/ui/icon/download_selected.png")), Download);
                tabbedPane1.setDisabledIconAt(0, new ImageIcon(getClass().getResource("/ui/icon/download_unselected.png")));

                //======== Upload ========
                {
                    Upload.setBackground(Color.white);
                    Upload.setLayout(new GridBagLayout());
                    ((GridBagLayout)Upload.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)Upload.getLayout()).rowHeights = new int[] {60, 90, 80, 30, 0};
                    ((GridBagLayout)Upload.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
                    ((GridBagLayout)Upload.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};

                    //---- fileList2 ----
                    fileList2.setModel(new AbstractListModel() {
                        String[] values = {

                        };
                        public int getSize() { return values.length; }
                        public Object getElementAt(int i) { return values[i]; }
                    });
                    Upload.add(fileList2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== fileChooser ========
                    {
                        fileChooser.setBackground(Color.white);
                        fileChooser.setLayout(new GridLayout());

                        //---- addFilesOrDropTextPane ----
                        addFilesOrDropTextPane.setBackground(Color.white);
                        addFilesOrDropTextPane.setFont(addFilesOrDropTextPane.getFont().deriveFont(17f));
                        addFilesOrDropTextPane.setForeground(new Color(152, 181, 205));
                        addFilesOrDropTextPane.setInheritsPopupMenu(false);
                        addFilesOrDropTextPane.setText("Add files or drop files to upload");
                        addFilesOrDropTextPane.setHorizontalAlignment(SwingConstants.CENTER);
                        addFilesOrDropTextPane.setBorder(new LineBorder(new Color(152, 181, 205, 191), 3, true));
                        addFilesOrDropTextPane.setAlignmentY(0.0F);
                        addFilesOrDropTextPane.setIcon(new ImageIcon(getClass().getResource("/ui/icon/file.png")));
                        addFilesOrDropTextPane.setIconTextGap(8);
                        fileChooser.add(addFilesOrDropTextPane);
                    }
                    Upload.add(fileChooser, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 20, 0, 20), 0, 0));

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
                    Upload.add(infoBar2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== filePath2 ========
                    {
                        filePath2.setBackground(Color.white);
                        filePath2.setMinimumSize(new Dimension(200, 50));
                        filePath2.setMaximumSize(new Dimension(-1, -1));
                        filePath2.setPreferredSize(new Dimension(476, 50));
                        filePath2.setAlignmentX(0.0F);
                        filePath2.setAlignmentY(0.0F);
                        filePath2.setLayout(new GridBagLayout());
                        ((GridBagLayout)filePath2.getLayout()).columnWidths = new int[] {50, 50, 46, 55, 66, 0};
                        ((GridBagLayout)filePath2.getLayout()).rowHeights = new int[] {60, 0};
                        ((GridBagLayout)filePath2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)filePath2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                        //---- buttonBack2 ----
                        buttonBack2.setBackground(Color.white);
                        buttonBack2.setIcon(new ImageIcon(getClass().getResource("/ui/icon/leftRorrow.png")));
                        buttonBack2.setBorder(null);
                        buttonBack2.setMaximumSize(new Dimension(40, 40));
                        buttonBack2.setMinimumSize(new Dimension(40, 40));
                        buttonBack2.setMargin(new Insets(0, 0, 0, 0));
                        buttonBack2.setIconTextGap(0);
                        buttonBack2.setPreferredSize(new Dimension(40, 40));
                        filePath2.add(buttonBack2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //---- button2Root2 ----
                        button2Root2.setBackground(Color.white);
                        button2Root2.setIcon(new ImageIcon(getClass().getResource("/ui/icon/folder.png")));
                        button2Root2.setBorder(null);
                        button2Root2.setForeground(new Color(152, 181, 205));
                        button2Root2.setMargin(new Insets(0, 0, 0, 0));
                        button2Root2.setMaximumSize(new Dimension(40, 40));
                        button2Root2.setMinimumSize(new Dimension(40, 40));
                        button2Root2.setPreferredSize(new Dimension(40, 40));
                        filePath2.add(button2Root2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    Upload.add(filePath2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));
                }
                tabbedPane1.addTab("Upload", new ImageIcon(getClass().getResource("/ui/icon/upload_selected.png")), Upload);
                tabbedPane1.setDisabledIconAt(1, new ImageIcon(getClass().getResource("/ui/icon/upload_unselected.png.png")));
            }
            mainPanel.add(tabbedPane1);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        tabbedPane1.setUI(new FlatTabbedPanedUI());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yiner
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel Download;
    private JPanel filePath1;
    private JButton buttonBack1;
    private JButton button2Root1;
    private JPanel infoBar1;
    private JLabel fileSize;
    private JLabel fileNumber;
    private JLabel status;
    private JList fileList1;
    private JPanel Upload;
    private JList fileList2;
    private JPanel fileChooser;
    private JLabel addFilesOrDropTextPane;
    private JPanel infoBar2;
    private JLabel fileSize2;
    private JLabel fileNumber2;
    private JLabel status2;
    private JPanel filePath2;
    private JButton buttonBack2;
    private JButton button2Root2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
