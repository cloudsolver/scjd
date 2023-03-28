package com.rohitsood.urlybird.gui.record;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;


/**
 * This singleton <tt>JInternalFrame</tt> enables the administrator to search for entire records based on record id.
 * Also provides a button panel which allows adding,editing, and deleting records.
 *
 * @author Rohit Sood
 * @version 1.4
 */
public final class ManageRecordInternalFrame extends JInternalFrame {
    /** The title of the internal frame */
    private static final String TITLE = "Manage Record";

    /** A reference to self. Single reference. */
    private static ManageRecordInternalFrame iframe;

    /**The data panel which holds the record.*/
    private ManageRecordDataPanel dataPanel;

    /**The go panel which holds the go button and text field.*/
    private GoPanel goPanel;

    /**The button panel which holds action button.*/
    private ManageRecordButtonPanel buttonPanel;

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new ManageRecordInternalFrame object.
     *
     * @param manager The manager from which it will retrieve the desktop pane and add itself.
     */
    private ManageRecordInternalFrame(GuiManager manager) {
        super(TITLE, true, true, true, true);
        this.manager = manager;
        init();
        setVisible(true);
    }

    /**
     * Gets the instance of this internal frame. This is the singleton implementation method.
     *
     * @param manager The manager from which it will retrieve the desktop pane and add itself.
     *
     * @return An instance of itself.
     */
    public static ManageRecordInternalFrame getInstance(GuiManager manager) {
        if ((null == iframe) || iframe.isClosed()) {
            iframe = new ManageRecordInternalFrame(manager);
        }

        return iframe;
    }

    /**
     * Initializes the internal frame.
     */
    private void init() {
        getContentPane().setLayout(new BorderLayout());

        goPanel = new GoPanel(this.manager);
        dataPanel = new ManageRecordDataPanel(manager);
        buttonPanel = new ManageRecordButtonPanel(this.manager);

        getContentPane().add(goPanel, BorderLayout.PAGE_START);
        getContentPane().add(dataPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        setSize(SpringLayoutUtilities.getPreferredDimension(this.getContentPane(), SpringLayoutUtilities.VERTICAL));
        pack(); //layout the components to their preferred sizes
        manager.getDesktopPane().add(this);
    }
}
