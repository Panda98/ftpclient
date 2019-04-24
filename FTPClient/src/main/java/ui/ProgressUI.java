package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * @author yiner
 * @since 2019-04-24
 */
public class ProgressUI extends BasicProgressBarUI {

    private JProgressBar bar;
    private int value;
    private Color foreColor;
    private Color bgColor;

    ProgressUI(JProgressBar bar, Color foreColor, Color bgColor) {
        this.bar = bar;
        this.foreColor = foreColor;
        this.bgColor = bgColor;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {

        this.bar.setBackground(bgColor);
        this.bar.setForeground(foreColor);
        super.paintDeterminate(g, c);
    }

}
