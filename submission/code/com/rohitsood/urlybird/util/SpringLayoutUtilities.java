package com.rohitsood.urlybird.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Spring;
import javax.swing.SpringLayout;


/**
 * Utility that helps in the layout of user interface components that use the <tt>SpringLayout</tt>. Provides a generic
 * way of taking a <tt>Container</tt> object and creating a form of labels and text fields in a standard grid All
 * spacing and sizing are calculated and set here.
 *
 * @author Rohit Sood
 * @version 1.5
 */
public final class SpringLayoutUtilities {
    /** Constant for a vertical layout. */
    public static final int VERTICAL = 1;

    /** Constant for a horizontal layout. */
    public static final int HORIZONTAL = 2;

    /** Constant for a 2-Dimensional layout. */
    public static final int BOTH = 0;

    /** Constant for the default spread of the components in the container. */
    public static final int SPREAD = 5;

    /**
     * Prevents the creation a new SpringLayoutUtilities object.
     */
    private SpringLayoutUtilities() {
    }

    /**
     * This method is used to create a grid view for the form. All components in the container are layout as specified
     * in the row and columns. Padding and initial coordinates within the container is provided for embellishments.
     *
     * @param container The container to layout. Component's layout must be a <tt>SpringLayout</tt>.
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param x The initial x co-ordinate.
     * @param y The inital y co-ordinate.
     * @param padX The padding in the x-axis among components.
     * @param padY The padding in the y-axis among components.
     */
    public static void makeSpringGrid(Container container, int rows, int cols, int x, int y, int padX, int padY) {
        //calculate the total width and height of the container
        //figure out the max width for the column and set it for all columns
        final int totalWidth = resolveWidth(container, rows, cols, padX, x);

        //end cols
        final int totalHeight = resolveHeight(container, rows, cols, padY, y);

        container.setSize(totalWidth, totalHeight);
    }

    /**
     * Figures out the maximum width of the component across the container.
     *
     * @param container The container which containes the components.
     * @param rows The number of rows in the container.
     * @param cols The number of columns in the container.
     * @param padX The horizontal padding.
     * @param startX The initial left padding.
     *
     * @return The maximum width of the component.
     */
    private static int resolveWidth(Container container, int rows, int cols, int padX, int startX) {
        for (int c = 0; c < cols; c++) {
            final int maxColumnWidth = getMaximumWidth(container, rows, cols, c);
            setMaximumWidth(container, rows, cols, maxColumnWidth, startX, c);
            startX += (maxColumnWidth + padX);
        }

        //end loop cols
        return startX;
    }

    /**
     * Figures out the maximum width of the component across the container.
     *
     * @param container The container which containes the components.
     * @param rows The number of rows in the container.
     * @param cols The number of columns in the container.
     * @param padY The vertical padding.
     * @param startY The inital top padding.
     *
     * @return The maximum height of the component.
     */
    private static int resolveHeight(Container container, int rows, int cols, int padY, int startY) {
        int maxRowHeight = -1;

        for (int r = 0; r < rows; r++) {
            maxRowHeight = getMaxHeight(container, cols, r);
            setMaxHeight(container, cols, maxRowHeight, startY, r);
            startY += (maxRowHeight + padY);
        }

        return startY;
    }

    /**
     * Sets the y coordinate of every component in the container. Sets height of the <tt>SpringLayout.Constraints</tt>.
     *
     * @param container The container which contains the components.
     * @param cols The total number of columns in the container.
     * @param maxRowHeight The maximum height of the row.
     * @param startY The starting y coordinate of the components.
     * @param row The row of the component for which the height needs to be set.
     */
    private static void setMaxHeight(Container container, int cols, int maxRowHeight, int startY, int row) {
        for (int col = 0; col < cols; col++) {
            try {
                final Component comp = container.getComponent(getComponentID(row, col, cols));
                final SpringLayout.Constraints constraints = ((SpringLayout) container.getLayout()).getConstraints(comp);
                constraints.setY(Spring.constant(startY));
                constraints.setHeight(Spring.constant(maxRowHeight));
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    /**
     * Gets the maximum height of the row.
     *
     * @param container The container that holds the components.
     * @param cols The total number of columns.
     * @param r The row number.
     *
     * @return The maximum height.
     */
    private static int getMaxHeight(Container container, int cols, int r) {
        int maxRowHeight = 0;

        for (int c = 0; c < cols; c++) {
            try {
                final Component comp = container.getComponent(getComponentID(r, c, cols));
                final int componentHeight = ((SpringLayout) container.getLayout()).getConstraints(comp).getHeight().getValue();
                maxRowHeight = ((maxRowHeight > componentHeight) ? maxRowHeight : componentHeight);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        return maxRowHeight;
    }

    /**
     * Sets the x coordinate of every component in the container.
     *
     * @param container The container that holds the components.
     * @param rows The total number of rows.
     * @param cols The total number of columns.
     * @param maxColumnWidth The maximum column width.
     * @param startX The starting point in the x axis.
     * @param c The column for which the width has to be set.
     */
    private static void setMaximumWidth(Container container, int rows, int cols, int maxColumnWidth, int startX, int c) {
        for (int r = 0; r < rows; r++) {
            try {
                final Component comp = container.getComponent(getComponentID(r, c, cols));
                final SpringLayout.Constraints constraints = ((SpringLayout) container.getLayout()).getConstraints(comp);
                constraints.setX(Spring.constant(startX));
                constraints.setWidth(Spring.constant(maxColumnWidth));
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    /**
     * Gets the maximum width of the row.
     *
     * @param container The container that has the components.
     * @param rows The total number of rows.
     * @param cols The total number of columns.
     * @param c The column for which the maximum width is required.
     *
     * @return The maximum width.
     */
    private static int getMaximumWidth(Container container, int rows, int cols, int c) {
        int maxColumnWidth = -1;

        for (int r = 0; r < rows; r++) {
            try {
                final Component comp = container.getComponent(getComponentID(r, c, cols));
                final int componentWidth = ((SpringLayout) container.getLayout()).getConstraints(comp).getWidth().getValue();
                maxColumnWidth = ((maxColumnWidth > componentWidth) ? maxColumnWidth : componentWidth);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        return maxColumnWidth;
    }

    /**
     * Gets the component id given a particular row and column.
     *
     * @param row The row in which the component resides.
     * @param col The column in which the component resides.
     * @param totalColumns The total number of columns in the grid.
     *
     * @return The id of the component.
     */
    private static int getComponentID(int row, int col, int totalColumns) {
        return (row * totalColumns) + col;
    }

    /**
     * Calculates the preferred dimension of the component given a mode. It takes the mode based on the mode constants
     * and calculates the preferred size.
     *
     * @param container The container for which the preferred size is to be determined.
     * @param mode The mode for which the calculation takes place.
     *
     * @return The preferred <tt>Dimension</tt> of the container.
     */
    public static Dimension getPreferredDimension(Container container, int mode) {
        final int count = container.getComponentCount();

        Dimension totalSize = null;
        int sumWidth = 0;
        int sumHeight = 0;
        int maxHeight = 0;
        int maxWidth = 0;

        for (int a = 0; a < count; a++) {
            final Component c = container.getComponent(a);

            Dimension pref = c.getPreferredSize();

            if ((pref.width < 1) && (pref.height < 1)) {
                pref = c.getSize();
            }

            sumWidth += pref.width;
            sumHeight += pref.height;
            maxWidth = ((maxWidth > pref.width) ? maxWidth : pref.width);
            maxHeight = ((maxHeight > pref.height) ? maxHeight : pref.height);
        }

        switch (mode) {
        case BOTH:
            totalSize = new Dimension(sumWidth, sumHeight);

            break;

        case VERTICAL:
            totalSize = new Dimension(maxWidth, sumHeight);

            break;

        case HORIZONTAL:
            totalSize = new Dimension(sumWidth, maxHeight);

            break;

        default:
            throw new IllegalArgumentException("Mode can either be BOTH, VERTICAL or HORIZONTAL");
        }

        return totalSize;
    }
}
