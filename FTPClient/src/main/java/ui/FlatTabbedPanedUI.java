package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * @author yiner
 * @since 2019-04-20
 */
public class FlatTabbedPanedUI extends BasicTabbedPaneUI {
    private static final Color SELECTED_TAB_COLOR = new Color(255, 255, 255);
    private static final Color UNSELECTED_TAB_COLOR = new Color(234, 245, 255);

    private static final Color UNSELECTED_TEXT_COLOR = new Color(152, 181, 205);
    private static final Color SELECTED_TEXT_COLOR = new Color(64, 73, 105);

    private static final int TAB_MINIMUM_SIZE = 8;

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                  int x, int y, int w, int h, boolean isSelected) {
        // do nothing
    }

    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects,
                            int tabIndex, Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);

    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement,
                                      int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        if (isSelected) {
            if (tabPlacement == TOP) {
                g.setColor(SELECTED_TAB_COLOR);
                g.fillRect(x, y, w, h);
            }
        } else {
            g.setColor(UNSELECTED_TAB_COLOR);
            g.fillRect(x, y, w, h);
        }
    }

    /**
     * Do not paint a focus indicator.
     */
    @Override
    protected void paintFocusIndicator(Graphics arg0, int arg1,
                                       Rectangle[] arg2, int arg3, Rectangle arg4, Rectangle arg5,
                                       boolean arg6) {
        // Leave it
    }

    /**
     * We do not want the tab to "lift up" when it is selected.
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        tabAreaInsets = new Insets(0, 0, 0, 0);
        selectedTabPadInsets = new Insets(0, 0, 0, 0);
        contentBorderInsets = new Insets(0, 0, 0, 0);
    }

    /**
     * Nor do we want the label to move.
     */
    @Override
    protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
                                    boolean isSelected) {
        return 0;
    }

    /**
     * Increase the tab height a bit
     */
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex,
                                     int fontHeight) {
        return fontHeight + 40;
    }

    @Override
    protected void layoutLabel(int arg0, FontMetrics arg1, int arg2,
                               String arg3, Icon arg4, Rectangle arg5, Rectangle arg6,
                               Rectangle arg7, boolean arg8) {
        super.layoutLabel(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    /**
     * Selected labels have a dark blue color.
     */
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font,
                             FontMetrics metrics, int tabIndex, String title,
                             Rectangle textRect, boolean isSelected) {
        if (isSelected && tabPlacement == TOP) {
            g.setColor(SELECTED_TEXT_COLOR);
        } else {
            g.setColor(UNSELECTED_TEXT_COLOR);
        }
        if (title.length() > TAB_MINIMUM_SIZE) {
            title = "..."
                    + title.substring(title.length() - TAB_MINIMUM_SIZE + 3,
                    title.length());
            textRect.x += 14;
        }
         Font tabFont = new Font(".SF NS Text", Font.BOLD, 14);
         g.setFont(tabFont);
        g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
    }

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex,
                                    FontMetrics metrics) {
        int tabWidth = tabPane.getWidth();
        int taille = tabWidth / 2;
        return taille;
    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
                                             int selectedIndex, int x, int y, int w, int h) {
        // do nothing
    }

    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
                                                int selectedIndex, int x, int y, int w, int h) {
        // do nothing
    }

    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
                                              int selectedIndex, int x, int y, int w, int h) {
        // do nothing;
    }

    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
                                               int selectedIndex, int x, int y, int w, int h) {
        // do nothing
    }

    @Override
    protected void paintIcon(Graphics g, int tabPlacement,
                             int tabIndex, Icon icon, Rectangle iconRect,
                             boolean isSelected ) {
        if (icon != null && isSelected) {
            icon.paintIcon(tabPane, g, iconRect.x, iconRect.y);
        }
    }

}
