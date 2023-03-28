package com.rohitsood.urlybird.gui.widget;

import com.rohitsood.urlybird.gui.AbstractMainWindow;

import javax.swing.JOptionPane;


/**
 * Convenience class to show modal messages on errors, warnings, and information triggers. <tt>ExceptionOptionPane</tt>
 * is a <tt>JOptionPane</tt> which shows messages only on top of a <tt>MainWindow</tt> instance.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public final class ExceptionOptionPane extends JOptionPane {
    /** String constant for an unknown error. */
    private static final String ERROR_HEADLINE = "Error Occurred";

    /**
     * Shows an exception message. If no message is available in the exception a default message is shown.
     *
     * @param window The window on top of which this dialog will appear.
     * @param e The exception to display.
     */
    public static void showException(AbstractMainWindow window, Exception e) {
        String detailedMessage = ERROR_HEADLINE;

        if ((e.getMessage() != null) && !e.getMessage().equals("")) {
            detailedMessage = e.getMessage();
        }

        JOptionPane.showMessageDialog(window, detailedMessage, ERROR_HEADLINE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an exception message.
     *
     * @param window The window on top of which this dialog will appear.
     * @param detailedMessage The detailed message to display.
     * @param e The exception to display.
     */
    public static void showException(AbstractMainWindow window, String detailedMessage, Exception e) {
        JOptionPane.showMessageDialog(window, detailedMessage, e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an information dialog. Information is shown in the dialog box.
     *
     * @param window The window on top of which this dialog will appear.
     * @param message The message to be displayed
     */
    public static void showInformation(AbstractMainWindow window, String headline, String message) {
        JOptionPane.showMessageDialog(window, message, headline, JOptionPane.INFORMATION_MESSAGE);
    }
}
