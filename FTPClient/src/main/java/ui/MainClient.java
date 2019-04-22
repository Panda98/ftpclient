/*
 * Created by JFormDesigner on Mon Apr 22 15:45:40 CST 2019
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import com.intellij.uiDesigner.core.*;
import model.File;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;
import util.FTPClient;

/**
 * @author yiner
 */
public class MainClient extends JFrame {

    static private FTPClient client;
    static private ExecutorService threadPool;
    static {
        threadPool = Executors.newCachedThreadPool();
        client = new FTPClient();
    }

    // Java bean for binding
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldValue = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldValue, status);
    }

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    // Java bean - End

    public MainClient() {
        files = new ArrayList<File>();
        initComponents();
        tabbedPane1.setUI(new FlatTabbedPanedUI());
        setStatus("未连接");
    }

    // Util methods
    private boolean isEmpty(String s) {
        return  (s==null || s.equals(""));
    }

    private String performConnect(String host, int port, String username, String pwd) {
        String msg;
        try {
            client.connect(host, port, username, pwd);
            msg = "连接成功！";
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    private void refreshMainFrame() {
        threadPool.execute(new Runnable() {
            public void run() {
                listFile("/");
//                SwingUtilities.invokeLater(new Runnable() {
//                    public void run() {
//                        status1.setText(status);
//                        status2.setText(status);
//                    }
//                });
                mainPanel.updateUI();
            }
        });

    }

    private void listFile (String path) {
        String msg;

        try {
            LinkedHashMap<String,String> fileList =  client.list(path);
            Iterator<Map.Entry<String, String>> iterator = fileList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                files.add(new File(entry.getKey(), entry.getValue()));
            }

        } catch (Exception e) {
            msg = e.getMessage();
            JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.OK_OPTION, null);
        }

    }
    // Util methods - End

    // UI methods
    private void buttonConnActionPerformed(ActionEvent e) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String msg = null;

                try {
                    String host = textFieldHost.getText().trim();
                    String portStr = textFieldPort.getText().trim();
                    String username = textFieldName.getText().trim();
                    String pwd = new String(passwordField.getPassword());

                    if (isEmpty(host) || isEmpty(portStr) || isEmpty(username) || isEmpty(pwd)) {
                        msg = "输入框不能为空！";
                        return;
                    }
                    int port = Integer.parseInt(portStr);

                    msg = performConnect(host, port, username, pwd);

                } catch (NumberFormatException e1) {
                    msg = "端口必须为整数！";

                } finally {

                    if (msg.equals("")) {
                        msg = "连接失败！";
                        setStatus("未连接");
                    }
                    JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.OK_OPTION, null);

                    if (msg.equals("连接成功！") || msg.equals("连接已经建立！") ) {
                        Window window = SwingUtilities.getWindowAncestor(buttonConn);
                        window.dispose();
                        setStatus("已连接");

                       refreshMainFrame();
                    }
                }
            }
        });
    }

    private void connectActionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Connect");
        frame.setSize(335, 236);
        frame.setResizable(false);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    private void disconnectActionPerformed(ActionEvent e) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                client.close();
                JOptionPane.showMessageDialog(null, "断开连接成功！", "提示", JOptionPane.OK_OPTION, null);
                setStatus("未连接");
            }
        });
    }
    // UI methods - End

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yiner
        mainPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        Download = new JPanel();
        filePath1 = new JPanel();
        buttonBack1 = new JButton();
        button2Root1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        infoBar1 = new JPanel();
        fileSize1 = new JLabel();
        fileNumber1 = new JLabel();
        status1 = new JLabel();
        Upload = new JPanel();
        fileList2 = new JList<>();
        fileChooser = new JPanel();
        addFilesOrDropTextPane = new JLabel();
        infoBar2 = new JPanel();
        fileSize2 = new JLabel();
        fileNumber2 = new JLabel();
        status2 = new JLabel();
        filePath2 = new JPanel();
        buttonBack2 = new JButton();
        button2Root2 = new JButton();
        panel1 = new JPanel();
        labelConn = new JLabel();
        labelHost = new JLabel();
        textFieldHost = new JTextField();
        labelPort = new JLabel();
        textFieldPort = new JTextField();
        labelName = new JLabel();
        textFieldName = new JTextField();
        labelPwd = new JLabel();
        passwordField = new JPasswordField();
        buttonConn = new JButton();
        popupMenu1 = new JPopupMenu();
        connect = new JMenuItem();
        disconnect = new JMenuItem();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== mainPanel ========
        {
            mainPanel.setBackground(Color.white);
            mainPanel.setInheritsPopupMenu(false);
            mainPanel.setMinimumSize(new Dimension(600, 500));
            mainPanel.setPreferredSize(new Dimension(600, 500));
            mainPanel.setVisible(true);
            mainPanel.setForeground(Color.white);
            mainPanel.setMaximumSize(new Dimension(-1, -1));
            mainPanel.setFocusable(false);
            mainPanel.setComponentPopupMenu(popupMenu1);

            // JFormDesigner evaluation mark
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), mainPanel.getBorder())); mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            mainPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== tabbedPane1 ========
            {
                tabbedPane1.setBackground(Color.white);
                tabbedPane1.setFocusable(true);
                tabbedPane1.setToolTipText("");
                tabbedPane1.setComponentPopupMenu(popupMenu1);

                //======== Download ========
                {
                    Download.setBackground(Color.white);
                    Download.setEnabled(true);
                    Download.setComponentPopupMenu(popupMenu1);
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

                    //======== scrollPane1 ========
                    {

                        //---- table1 ----
                        table1.setColumnSelectionAllowed(true);
                        table1.setRowHeight(40);
                        table1.setRowMargin(5);
                        scrollPane1.setViewportView(table1);
                    }
                    Download.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //======== infoBar1 ========
                    {
                        infoBar1.setBackground(new Color(234, 245, 255));
                        infoBar1.setForeground(new Color(234, 245, 255));
                        infoBar1.setOpaque(true);
                        infoBar1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //---- fileSize1 ----
                        fileSize1.setForeground(new Color(152, 181, 205));
                        fileSize1.setOpaque(false);
                        fileSize1.setText("5MB");
                        fileSize1.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar1.add(fileSize1, new GridConstraints(0, 2, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- fileNumber1 ----
                        fileNumber1.setForeground(new Color(152, 181, 205));
                        fileNumber1.setOpaque(false);
                        fileNumber1.setText("0\u4e2a\u6587\u4ef6");
                        fileNumber1.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar1.add(fileNumber1, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status1 ----
                        status1.setForeground(new Color(152, 181, 205));
                        status1.setOpaque(false);
                        status1.setText("\u672a\u8fde\u63a5");
                        status1.setHorizontalAlignment(SwingConstants.CENTER);
                        status1.setPreferredSize(new Dimension(47, 16));
                        infoBar1.add(status1, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));
                    }
                    Download.add(infoBar1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                tabbedPane1.addTab("\u4e0b\u8f7d", new ImageIcon(getClass().getResource("/ui/icon/download_selected.png")), Download);
                tabbedPane1.setDisabledIconAt(0, new ImageIcon(getClass().getResource("/ui/icon/download_unselected.png")));

                //======== Upload ========
                {
                    Upload.setBackground(Color.white);
                    Upload.setComponentPopupMenu(popupMenu1);
                    Upload.setLayout(new GridBagLayout());
                    ((GridBagLayout)Upload.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)Upload.getLayout()).rowHeights = new int[] {60, 90, 80, 30, 0};
                    ((GridBagLayout)Upload.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
                    ((GridBagLayout)Upload.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};

                    //---- fileList2 ----
                    fileList2.setModel(new AbstractListModel<String>() {
                        String[] values = {

                        };
                        @Override
                        public int getSize() { return values.length; }
                        @Override
                        public String getElementAt(int i) { return values[i]; }
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
                        addFilesOrDropTextPane.setText("\u70b9\u51fb\u6216\u62d6\u52a8\u6dfb\u52a0\u6587\u4ef6");
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
                        fileNumber2.setText("0\u4e2a\u6587\u4ef6");
                        fileNumber2.setHorizontalAlignment(SwingConstants.CENTER);
                        infoBar2.add(fileNumber2, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status2 ----
                        status2.setForeground(new Color(152, 181, 205));
                        status2.setOpaque(false);
                        status2.setText("\u672a\u8fde\u63a5");
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
                tabbedPane1.addTab("\u4e0a\u4f20", new ImageIcon(getClass().getResource("/ui/icon/upload_selected.png")), Upload);
                tabbedPane1.setDisabledIconAt(1, new ImageIcon(getClass().getResource("/ui/icon/upload_unselected.png.png")));
            }
            mainPanel.add(tabbedPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(mainPanel);
        pack();
        setLocationRelativeTo(getOwner());

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {58, 95, 48, 94, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {55, 65, 65, 40, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- labelConn ----
            labelConn.setText("\u8fde\u63a5\u670d\u52a1\u5668");
            labelConn.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(labelConn, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- labelHost ----
            labelHost.setText("\u4e3b\u673a");
            panel1.add(labelHost, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldHost ----
            textFieldHost.setPreferredSize(new Dimension(90, 28));
            textFieldHost.setMinimumSize(new Dimension(90, 28));
            textFieldHost.setText("192.168.2.7");
            panel1.add(textFieldHost, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 15), 0, 0));

            //---- labelPort ----
            labelPort.setText("\u7aef\u53e3");
            panel1.add(labelPort, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldPort ----
            textFieldPort.setPreferredSize(new Dimension(90, 28));
            textFieldPort.setMinimumSize(new Dimension(90, 28));
            textFieldPort.setText("21");
            panel1.add(textFieldPort, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 10), 0, 0));

            //---- labelName ----
            labelName.setText("\u7528\u6237\u540d");
            panel1.add(labelName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldName ----
            textFieldName.setPreferredSize(new Dimension(90, 28));
            textFieldName.setMinimumSize(new Dimension(90, 28));
            textFieldName.setText("Experiment");
            panel1.add(textFieldName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 15), 0, 0));

            //---- labelPwd ----
            labelPwd.setText("\u5bc6\u7801");
            panel1.add(labelPwd, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- passwordField ----
            passwordField.setMinimumSize(new Dimension(90, 28));
            passwordField.setPreferredSize(new Dimension(90, 28));
            passwordField.setText("123456");
            panel1.add(passwordField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 10), 0, 0));

            //---- buttonConn ----
            buttonConn.setText("\u8fde\u63a5");
            buttonConn.setBackground(Color.white);
            buttonConn.addActionListener(e -> buttonConnActionPerformed(e));
            panel1.add(buttonConn, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        //======== popupMenu1 ========
        {

            //---- connect ----
            connect.setText("\u8fde\u63a5");
            connect.addActionListener(e -> connectActionPerformed(e));
            popupMenu1.add(connect);

            //---- disconnect ----
            disconnect.setText("\u65ad\u5f00\u8fde\u63a5");
            disconnect.addActionListener(e -> disconnectActionPerformed(e));
            popupMenu1.add(disconnect);
        }

        //---- bindings ----
        bindingGroup = new BindingGroup();
        {
            JTableBinding binding = SwingBindings.createJTableBinding(UpdateStrategy.READ,
                files, table1);
            binding.addColumnBinding(BeanProperty.create("name"))
                .setColumnName("Name")
                .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("type"))
                .setColumnName("Type")
                .setColumnClass(String.class);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${status}"),
            status2, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${status}"),
            status1, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yiner
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel Download;
    private JPanel filePath1;
    private JButton buttonBack1;
    private JButton button2Root1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel infoBar1;
    private JLabel fileSize1;
    private JLabel fileNumber1;
    private JLabel status1;
    private JPanel Upload;
    private JList<String> fileList2;
    private JPanel fileChooser;
    private JLabel addFilesOrDropTextPane;
    private JPanel infoBar2;
    private JLabel fileSize2;
    private JLabel fileNumber2;
    private JLabel status2;
    private JPanel filePath2;
    private JButton buttonBack2;
    private JButton button2Root2;
    private JPanel panel1;
    private JLabel labelConn;
    private JLabel labelHost;
    private JTextField textFieldHost;
    private JLabel labelPort;
    private JTextField textFieldPort;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelPwd;
    private JPasswordField passwordField;
    private JButton buttonConn;
    private JPopupMenu popupMenu1;
    private JMenuItem connect;
    private JMenuItem disconnect;
    private List<model.File> files;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
