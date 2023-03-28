package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.button.CommonButton;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * Provides buttons for the user to interact with the record.  Allows the user to add a new record, edit, update and
 * delete an existing record.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class ServerConsoleButtonPanel extends JPanel {
    /** The width for the rigid area. */
    private static final int RIGID_AREA_WIDTH = 10;

    /** Extra width for a rigid area. */
    private static final int RIGID_AREA_WIDTH_EXTRA = 20;

    /** Rigid area height. */
    private static final int RIGID_AREA_HEIGHT = 30;

    /** Close button text. */
    static final String CLOSE_TEXT = "Close";

    /** Cancel button text. */
    static final String CLEAR_TEXT = "Clear";

    /** The manager to delegate to. */
    private GuiManager manager;

    /** The close button of the server console. */
    private CommonButton closeButton;

    /** The clear button of the server console. */
    private CommonButton clearButton;

    /**
     * Creates a new <tt>ServerConsoleButtonPanel</tt> object. Registers with the <tt>GuiManager</tt>
     *
     * @param manager The manager to delegate to.
     */
    public ServerConsoleButtonPanel(GuiManager manager) {
        super();
        this.manager = manager;
        init();
    }

    /**
     * Initializes the user interface.
     */
    private void init() {
        final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);

        closeButton = new CommonButton(CLOSE_TEXT);
        closeButton.addActionListener(new ServerConsoleButtonActionListener(manager));

        clearButton = new CommonButton(CLEAR_TEXT);
        clearButton.addActionListener(new ServerConsoleButtonActionListener(manager));
        clearButton.setMnemonic('L');

        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
        add(clearButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
        add(closeButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
        setBorder(LineBorder.createBlackLineBorder());
    }
}
