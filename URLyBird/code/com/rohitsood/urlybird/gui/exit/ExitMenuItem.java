package com.rohitsood.urlybird.gui.exit;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenuItem;


/**
 * Allows the user to exit the application. Defines a mnemonic for keyboard access to exit. Adds an
 * <tt>ActionListener</tt> which delegates the exit request to the <tt>GuiManager</tt>.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class ExitMenuItem extends JMenuItem {
    /** The title text of the menu item. */
    private static final String TITLE = "Exit";

    /** The mnemonic for this menu. */
    private static final char MNEMONIC = 'X';

    /** The manager to delegate to. */
    private GuiManager manager;

    /**
     * Creates a new ExitMenuItem object. Internally sets the manager to create an <tt>ActionListener</tt>.
     *
     * @param manager The manager to delegate to.
     */
    public ExitMenuItem(GuiManager manager) {
        super(TITLE);
        this.manager = manager;
        init();
    }

    /**
     * Initialize the exit menu item. Adds an <tt>ActionListener</tt>.
     */
    private void init() {
        addActionListener(new ExitMenuItemActionListener(manager));
        setMnemonic(MNEMONIC);
    }
}
