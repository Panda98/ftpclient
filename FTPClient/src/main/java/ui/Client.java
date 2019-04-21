package ui;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import com.intellij.uiDesigner.core.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import util.FTPClient;


/**
 * @author yiner
 * @since 2019-04-17
 */
public class Client {

    private FTPClient client = new FTPClient();

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

    private void connectActionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Connect");
        frame.setSize(318, 286);
        frame.setResizable(false);
        frame.setContentPane(new Client().panel1);
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
        client.close();
    }

    private boolean isEmpty(String s) {
        return  (s==null || s.equals(""));
    }

    class ConnectRun implements Runnable{
        String host;
        int port;
        String username;
        String pwd;
        String msg = "";

        public ConnectRun(String host, int port, String username, String pwd) {
            this.host = host;
            this.port = port;
            this.username = username;
            this.pwd = pwd;
        }

        public void run() {
            try {
                client.connect(host, port, username, pwd);
                msg = "连接成功！";
            } catch (Exception e) {
                msg = e.getMessage();
            }
        }
    }

    private void buttonConnActionPerformed(ActionEvent e) {

        boolean isRun = false;
        ConnectRun runner = null;
        String msg = null;

        try {
            String host = this.textFieldHost.getText();
            String portStr = this.textFieldPort.getText();
            String username = this.textFieldName.getText();
            String pwd = new String(this.passwordField.getPassword());

            if (isEmpty(host) || isEmpty(portStr) || isEmpty(username) || isEmpty(pwd)) {
                msg = "输入框不能为空！";
                return;
            }

            int port = Integer.parseInt(portStr);

            runner = new ConnectRun(host, port, username, pwd);
            Thread thread = new Thread(runner);
            thread.run();
            isRun = true;

        } catch (NumberFormatException e1) {
            msg = "端口必须为整数！";

        } finally {
            if (isRun) {
                msg = runner.msg;
            }

            JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.OK_OPTION, null);
            if (msg.equals("连接成功！")) {
                Window window = SwingUtilities.getWindowAncestor(this.buttonConn);
                window.dispose();
                this.status1.setText("已连接");
                this.status2.setText("已连接");
            }
        }

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yiner
        this.mainPanel = new JPanel();
        this.tabbedPane1 = new JTabbedPane();
        this.Download = new JPanel();
        this.filePath1 = new JPanel();
        this.buttonBack1 = new JButton();
        this.button2Root1 = new JButton();
        this.infoBar1 = new JPanel();
        this.fileSize1 = new JLabel();
        this.fileNumber1 = new JLabel();
        this.status1 = new JLabel();
        this.fileList1 = new JList();
        this.Upload = new JPanel();
        this.fileList2 = new JList();
        this.fileChooser = new JPanel();
        this.addFilesOrDropTextPane = new JLabel();
        this.infoBar2 = new JPanel();
        this.fileSize2 = new JLabel();
        this.fileNumber2 = new JLabel();
        this.status2 = new JLabel();
        this.filePath2 = new JPanel();
        this.buttonBack2 = new JButton();
        this.button2Root2 = new JButton();
        this.popupMenu1 = new JPopupMenu();
        this.connect = new JMenuItem();
        this.disconnect = new JMenuItem();
        this.panel1 = new JPanel();
        this.labelConn = new JLabel();
        this.labelHost = new JLabel();
        this.textFieldHost = new JTextField();
        this.labelPort = new JLabel();
        this.textFieldPort = new JTextField();
        this.labelName = new JLabel();
        this.textFieldName = new JTextField();
        this.labelPwd = new JLabel();
        this.passwordField = new JPasswordField();
        this.buttonConn = new JButton();
        this.panel2 = new JPanel();
        this.label2 = new JLabel();
        this.labelMsg = new JLabel();
        this.buttonVerify = new JButton();

        //======== mainPanel ========
        {
            this.mainPanel.setBackground(Color.white);
            this.mainPanel.setInheritsPopupMenu(false);
            this.mainPanel.setMinimumSize(new Dimension(600, 500));
            this.mainPanel.setPreferredSize(new Dimension(600, 500));
            this.mainPanel.setVisible(true);
            this.mainPanel.setForeground(Color.white);
            this.mainPanel.setMaximumSize(new Dimension(-1, -1));
            this.mainPanel.setFocusable(false);
            this.mainPanel.setComponentPopupMenu(this.popupMenu1);

            // JFormDesigner evaluation mark
            this.mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), this.mainPanel.getBorder())); this.mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            this.mainPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)this.mainPanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)this.mainPanel.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)this.mainPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)this.mainPanel.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== tabbedPane1 ========
            {
                this.tabbedPane1.setBackground(Color.white);
                this.tabbedPane1.setFocusable(true);
                this.tabbedPane1.setToolTipText("");
                this.tabbedPane1.setComponentPopupMenu(this.popupMenu1);

                //======== Download ========
                {
                    this.Download.setBackground(Color.white);
                    this.Download.setEnabled(true);
                    this.Download.setComponentPopupMenu(this.popupMenu1);
                    this.Download.setLayout(new GridBagLayout());
                    ((GridBagLayout)this.Download.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)this.Download.getLayout()).rowHeights = new int[] {65, 105, 30, 0};
                    ((GridBagLayout)this.Download.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
                    ((GridBagLayout)this.Download.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

                    //======== filePath1 ========
                    {
                        this.filePath1.setBackground(Color.white);
                        this.filePath1.setMinimumSize(new Dimension(400, 50));
                        this.filePath1.setMaximumSize(new Dimension(-1, -1));
                        this.filePath1.setPreferredSize(new Dimension(476, 50));
                        this.filePath1.setAlignmentX(0.0F);
                        this.filePath1.setAlignmentY(0.0F);
                        this.filePath1.setLayout(new GridBagLayout());
                        ((GridBagLayout)this.filePath1.getLayout()).columnWidths = new int[] {50, 50, 46, 55, 66, 0, 0};
                        ((GridBagLayout)this.filePath1.getLayout()).rowHeights = new int[] {60, 0};
                        ((GridBagLayout)this.filePath1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)this.filePath1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                        //---- buttonBack1 ----
                        this.buttonBack1.setBackground(Color.white);
                        this.buttonBack1.setIcon(new ImageIcon(getClass().getResource("/ui/icon/leftRorrow.png")));
                        this.buttonBack1.setBorder(null);
                        this.buttonBack1.setMaximumSize(new Dimension(40, 40));
                        this.buttonBack1.setMinimumSize(new Dimension(40, 40));
                        this.buttonBack1.setMargin(new Insets(0, 0, 0, 0));
                        this.buttonBack1.setIconTextGap(0);
                        this.buttonBack1.setPreferredSize(new Dimension(40, 40));
                        this.filePath1.add(this.buttonBack1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //---- button2Root1 ----
                        this.button2Root1.setBackground(Color.white);
                        this.button2Root1.setIcon(new ImageIcon(getClass().getResource("/ui/icon/folder.png")));
                        this.button2Root1.setBorder(null);
                        this.button2Root1.setForeground(new Color(152, 181, 205));
                        this.button2Root1.setMargin(new Insets(0, 0, 0, 0));
                        this.button2Root1.setMaximumSize(new Dimension(40, 40));
                        this.button2Root1.setMinimumSize(new Dimension(40, 40));
                        this.button2Root1.setPreferredSize(new Dimension(40, 40));
                        this.filePath1.add(this.button2Root1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    this.Download.add(this.filePath1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 10, 5), 0, 0));

                    //======== infoBar1 ========
                    {
                        this.infoBar1.setBackground(new Color(234, 245, 255));
                        this.infoBar1.setForeground(new Color(234, 245, 255));
                        this.infoBar1.setOpaque(true);
                        this.infoBar1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //---- fileSize1 ----
                        this.fileSize1.setForeground(new Color(152, 181, 205));
                        this.fileSize1.setOpaque(false);
                        this.fileSize1.setText("5MB");
                        this.fileSize1.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar1.add(this.fileSize1, new GridConstraints(0, 2, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- fileNumber1 ----
                        this.fileNumber1.setForeground(new Color(152, 181, 205));
                        this.fileNumber1.setOpaque(false);
                        this.fileNumber1.setText("0\u4e2a\u6587\u4ef6");
                        this.fileNumber1.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar1.add(this.fileNumber1, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status1 ----
                        this.status1.setForeground(new Color(152, 181, 205));
                        this.status1.setOpaque(false);
                        this.status1.setText("\u672a\u8fde\u63a5");
                        this.status1.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar1.add(this.status1, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));
                    }
                    this.Download.add(this.infoBar1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- fileList1 ----
                    this.fileList1.setMaximumSize(new Dimension(40, 60));
                    this.fileList1.setMinimumSize(new Dimension(40, 60));
                    this.Download.add(this.fileList1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                this.tabbedPane1.addTab("\u4e0b\u8f7d", new ImageIcon(getClass().getResource("/ui/icon/download_selected.png")), this.Download);
                this.tabbedPane1.setDisabledIconAt(0, new ImageIcon(getClass().getResource("/ui/icon/download_unselected.png")));

                //======== Upload ========
                {
                    this.Upload.setBackground(Color.white);
                    this.Upload.setComponentPopupMenu(this.popupMenu1);
                    this.Upload.setLayout(new GridBagLayout());
                    ((GridBagLayout)this.Upload.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)this.Upload.getLayout()).rowHeights = new int[] {60, 90, 80, 30, 0};
                    ((GridBagLayout)this.Upload.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
                    ((GridBagLayout)this.Upload.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};

                    //---- fileList2 ----
                    this.fileList2.setModel(new AbstractListModel() {
                        String[] values = {

                        };
                        public int getSize() { return this.values.length; }
                        public Object getElementAt(int i) { return this.values[i]; }
                    });
                    this.Upload.add(this.fileList2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== fileChooser ========
                    {
                        this.fileChooser.setBackground(Color.white);
                        this.fileChooser.setLayout(new GridLayout());

                        //---- addFilesOrDropTextPane ----
                        this.addFilesOrDropTextPane.setBackground(Color.white);
                        this.addFilesOrDropTextPane.setFont(this.addFilesOrDropTextPane.getFont().deriveFont(17f));
                        this.addFilesOrDropTextPane.setForeground(new Color(152, 181, 205));
                        this.addFilesOrDropTextPane.setInheritsPopupMenu(false);
                        this.addFilesOrDropTextPane.setText("\u70b9\u51fb\u6216\u62d6\u52a8\u6dfb\u52a0\u6587\u4ef6");
                        this.addFilesOrDropTextPane.setHorizontalAlignment(SwingConstants.CENTER);
                        this.addFilesOrDropTextPane.setBorder(new LineBorder(new Color(152, 181, 205, 191), 3, true));
                        this.addFilesOrDropTextPane.setAlignmentY(0.0F);
                        this.addFilesOrDropTextPane.setIcon(new ImageIcon(getClass().getResource("/ui/icon/file.png")));
                        this.addFilesOrDropTextPane.setIconTextGap(8);
                        this.fileChooser.add(this.addFilesOrDropTextPane);
                    }
                    this.Upload.add(this.fileChooser, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 20, 0, 20), 0, 0));

                    //======== infoBar2 ========
                    {
                        this.infoBar2.setBackground(new Color(234, 245, 255));
                        this.infoBar2.setForeground(new Color(234, 245, 255));
                        this.infoBar2.setOpaque(true);
                        this.infoBar2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));

                        //---- fileSize2 ----
                        this.fileSize2.setForeground(new Color(152, 181, 205));
                        this.fileSize2.setOpaque(false);
                        this.fileSize2.setText("5MB");
                        this.fileSize2.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar2.add(this.fileSize2, new GridConstraints(0, 2, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- fileNumber2 ----
                        this.fileNumber2.setForeground(new Color(152, 181, 205));
                        this.fileNumber2.setOpaque(false);
                        this.fileNumber2.setText("0\u4e2a\u6587\u4ef6");
                        this.fileNumber2.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar2.add(this.fileNumber2, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));

                        //---- status2 ----
                        this.status2.setForeground(new Color(152, 181, 205));
                        this.status2.setOpaque(false);
                        this.status2.setText("\u672a\u8fde\u63a5");
                        this.status2.setHorizontalAlignment(SwingConstants.CENTER);
                        this.infoBar2.add(this.status2, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, new Dimension(150, 30), null));
                    }
                    this.Upload.add(this.infoBar2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== filePath2 ========
                    {
                        this.filePath2.setBackground(Color.white);
                        this.filePath2.setMinimumSize(new Dimension(200, 50));
                        this.filePath2.setMaximumSize(new Dimension(-1, -1));
                        this.filePath2.setPreferredSize(new Dimension(476, 50));
                        this.filePath2.setAlignmentX(0.0F);
                        this.filePath2.setAlignmentY(0.0F);
                        this.filePath2.setLayout(new GridBagLayout());
                        ((GridBagLayout)this.filePath2.getLayout()).columnWidths = new int[] {50, 50, 46, 55, 66, 0};
                        ((GridBagLayout)this.filePath2.getLayout()).rowHeights = new int[] {60, 0};
                        ((GridBagLayout)this.filePath2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)this.filePath2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                        //---- buttonBack2 ----
                        this.buttonBack2.setBackground(Color.white);
                        this.buttonBack2.setIcon(new ImageIcon(getClass().getResource("/ui/icon/leftRorrow.png")));
                        this.buttonBack2.setBorder(null);
                        this.buttonBack2.setMaximumSize(new Dimension(40, 40));
                        this.buttonBack2.setMinimumSize(new Dimension(40, 40));
                        this.buttonBack2.setMargin(new Insets(0, 0, 0, 0));
                        this.buttonBack2.setIconTextGap(0);
                        this.buttonBack2.setPreferredSize(new Dimension(40, 40));
                        this.filePath2.add(this.buttonBack2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));

                        //---- button2Root2 ----
                        this.button2Root2.setBackground(Color.white);
                        this.button2Root2.setIcon(new ImageIcon(getClass().getResource("/ui/icon/folder.png")));
                        this.button2Root2.setBorder(null);
                        this.button2Root2.setForeground(new Color(152, 181, 205));
                        this.button2Root2.setMargin(new Insets(0, 0, 0, 0));
                        this.button2Root2.setMaximumSize(new Dimension(40, 40));
                        this.button2Root2.setMinimumSize(new Dimension(40, 40));
                        this.button2Root2.setPreferredSize(new Dimension(40, 40));
                        this.filePath2.add(this.button2Root2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0), 0, 0));
                    }
                    this.Upload.add(this.filePath2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));
                }
                this.tabbedPane1.addTab("\u4e0a\u4f20", new ImageIcon(getClass().getResource("/ui/icon/upload_selected.png")), this.Upload);
                this.tabbedPane1.setDisabledIconAt(1, new ImageIcon(getClass().getResource("/ui/icon/upload_unselected.png.png")));
            }
            this.mainPanel.add(this.tabbedPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        //======== popupMenu1 ========
        {

            //---- connect ----
            this.connect.setText("\u8fde\u63a5");
            this.connect.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    connectActionPerformed(e);
                }
            });
            this.popupMenu1.add(this.connect);

            //---- disconnect ----
            this.disconnect.setText("\u65ad\u5f00\u8fde\u63a5");
            this.disconnect.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    disconnectActionPerformed(e);
                }
            });
            this.popupMenu1.add(this.disconnect);
        }

        //======== panel1 ========
        {
            this.panel1.setBackground(Color.white);

            // JFormDesigner evaluation mark
            this.panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), this.panel1.getBorder())); this.panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            this.panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)this.panel1.getLayout()).columnWidths = new int[] {65, 95, 50, 94, 0};
            ((GridBagLayout)this.panel1.getLayout()).rowHeights = new int[] {55, 65, 65, 40, 0};
            ((GridBagLayout)this.panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)this.panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- labelConn ----
            this.labelConn.setText("\u8fde\u63a5\u670d\u52a1\u5668");
            this.labelConn.setHorizontalAlignment(SwingConstants.CENTER);
            this.panel1.add(this.labelConn, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- labelHost ----
            this.labelHost.setText("\u4e3b\u673a");
            this.panel1.add(this.labelHost, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldHost ----
            this.textFieldHost.setPreferredSize(new Dimension(80, 28));
            this.textFieldHost.setMinimumSize(new Dimension(80, 28));
            this.panel1.add(this.textFieldHost, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 15), 0, 0));

            //---- labelPort ----
            this.labelPort.setText("\u7aef\u53e3");
            this.panel1.add(this.labelPort, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldPort ----
            this.textFieldPort.setPreferredSize(new Dimension(80, 28));
            this.textFieldPort.setMinimumSize(new Dimension(80, 28));
            this.panel1.add(this.textFieldPort, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 10), 0, 0));

            //---- labelName ----
            this.labelName.setText("\u7528\u6237\u540d");
            this.panel1.add(this.labelName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- textFieldName ----
            this.textFieldName.setPreferredSize(new Dimension(80, 28));
            this.textFieldName.setMinimumSize(new Dimension(80, 28));
            this.panel1.add(this.textFieldName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 15), 0, 0));

            //---- labelPwd ----
            this.labelPwd.setText("\u5bc6\u7801");
            this.panel1.add(this.labelPwd, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- passwordField ----
            this.passwordField.setMinimumSize(new Dimension(80, 28));
            this.passwordField.setPreferredSize(new Dimension(80, 28));
            this.panel1.add(this.passwordField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 10, 5, 10), 0, 0));

            //---- buttonConn ----
            this.buttonConn.setText("\u8fde\u63a5");
            this.buttonConn.setBackground(Color.white);
            this.buttonConn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    buttonConnActionPerformed(e);
                }
            });
            this.panel1.add(this.buttonConn, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        //======== panel2 ========
        {
            this.panel2.setBackground(Color.white);

            // JFormDesigner evaluation mark
            this.panel2.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), this.panel2.getBorder())); this.panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            this.panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)this.panel2.getLayout()).columnWidths = new int[] {200, 0};
            ((GridBagLayout)this.panel2.getLayout()).rowHeights = new int[] {44, 46, 40, 0};
            ((GridBagLayout)this.panel2.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)this.panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- label2 ----
            this.label2.setText("\u63d0\u793a");
            this.label2.setHorizontalAlignment(SwingConstants.CENTER);
            this.label2.setFont(new Font(".SF NS Text", Font.BOLD, 18));
            this.panel2.add(this.label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- labelMsg ----
            this.labelMsg.setHorizontalTextPosition(SwingConstants.CENTER);
            this.labelMsg.setHorizontalAlignment(SwingConstants.CENTER);
            this.labelMsg.setBackground(Color.white);
            this.panel2.add(this.labelMsg, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- buttonVerify ----
            this.buttonVerify.setText("\u786e\u5b9a");
            this.buttonVerify.setBackground(Color.white);
            this.buttonVerify.setOpaque(false);
            this.panel2.add(this.buttonVerify, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 5, 0));
        }

        //---- bindings ----
        this.bindingGroup = new BindingGroup();
        this.bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this.panel1, ELProperty.create("${connection.address}"),
            this.textFieldHost, BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST")));
        this.bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // Add by yiner
        tabbedPane1.setUI(new FlatTabbedPanedUI());

        // Add by yiner - End
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
    private JLabel fileSize1;
    private JLabel fileNumber1;
    private JLabel status1;
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
    private JPopupMenu popupMenu1;
    private JMenuItem connect;
    private JMenuItem disconnect;
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
    private JPanel panel2;
    private JLabel label2;
    private JLabel labelMsg;
    private JButton buttonVerify;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
