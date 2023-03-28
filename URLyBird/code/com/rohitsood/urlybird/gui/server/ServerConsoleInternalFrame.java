package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;


/**
 * The singleton internal frame enables the administrator to search for entire records
 * based on record id. In addition it also provides a button panel which allows
 * adding records,editing existing records, and deleting records.
 *
 * @author Rohit Sood
 * @version 1.4
 */
public final class ServerConsoleInternalFrame extends JInternalFrame {
    /** The title of the internal frame */
    private static final String TITLE = "Server Console";

    /** A reference to self. Single reference. */
    private static ServerConsoleInternalFrame iframe;

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new ManageRecordInternalFrame object.
     *
     * @param manager The manager from which it will retrieve the desktop pane
     *        and add itself.
     */
    private ServerConsoleInternalFrame(GuiManager manager) {
        super(TITLE, true, true, true, true);
        this.manager = manager;
        init();
        setVisible(true);
    }

    /**
     * Gets the instance of this internal frame. This is the singleton
     * implementation method.
     *
     * @param manager The manager from which it will retrieve the desktop pane
     *        and add itself.
     *
     * @return An instance of itself.
     */
    public static ServerConsoleInternalFrame getInstance(GuiManager manager) {
        if ((null == iframe) || iframe.isClosed()) {
            iframe = new ServerConsoleInternalFrame(manager);
        }

        return iframe;
    }

    /**
     * Initializes the internal frame.
     */
    private void init() {
        getContentPane().setLayout(new BorderLayout());

        //add content
        getContentPane().add(new ServerConsoleDataPanel(), BorderLayout.CENTER);
        getContentPane().add(new ServerConsoleButtonPanel(manager),
            BorderLayout.PAGE_END);

        setSize(SpringLayoutUtilities.getPreferredDimension(
                this.getContentPane(), SpringLayoutUtilities.VERTICAL));
        pack(); //layout the components to their preferred sizes
        manager.getDesktopPane().add(this);
    }
}
