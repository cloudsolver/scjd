package com.rohitsood.urlybird.gui.server;

import java.awt.BorderLayout;

import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Contains the console text area within a scrollpane. Provides methods to append and remove text from the text area.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class ServerConsoleDataPanel extends JPanel {
    /** The number of rows. */
    private static final int ROWS = 20;

    /** The total number of columns. */
    private static final int COLS = 50;

    /** The text area that displays console text. */
    private static JTextArea textArea = new JTextArea(ROWS, COLS);

    /** The scrollpane which contains the text area. */
    private static JScrollPane textScroller = new JScrollPane(textArea);

    /**
     * Creates a new <tt>ServerConsoleDataPanel</tt> object. Initializes the panel.
     */
    public ServerConsoleDataPanel() {
        init();
    }

    /**
     * Initializes the panel. Adds a text area to the scroll pane and appends default text to the text area.
     */
    private void init() {
        setLayout(new BorderLayout());
        add(textScroller, BorderLayout.CENTER);
        appendDefaultMessages();
    }

    /**
     * Appends default messages to the server console. This helps the user to initialize the server.
     */
    private void appendDefaultMessages() {
        textArea.append("\nServer Console" + (new Date()) + "\n");
        textArea.append("\nServer is not started.\n");
        textArea.append("\nTo start the server click on Server>Start Server.\n");
    }

    /**
     * Appends text to the console.
     *
     * @param text The text to append to the console.
     */
    public static void appendToConsole(String text) {
        textArea.append(text);
    }

    /**
     * Clears the console. Sets the text of the console to <tt>null</tt>.
     */
    public static void clearConsole() {
        textArea.setText(null);
    }
}
