package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.exit.ExitMenuItem;
import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenu;


/**
 * The server implementation of <tt>JMenu</tt>.
 * This menu appears when the application server is started.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class ServerMenu extends JMenu {
    /** The title of the menu. */
    private static final String TITLE = "Server";

    /** The mnemonic for this menu. */
    private static final char MNEMONIC = 'S';

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>ServerMenu</tt> object.
     *
     * @param manager The manager to initialize menu items with.
     */
    ServerMenu(GuiManager manager) {
        super(TITLE);
        this.manager = manager;

        init();
    }

    /**
     * Initializes the server mode menu items.
     */
    private void init() {
        add(new ServerModeMenuItem(manager));
        add(new ExitMenuItem(manager));
        setMnemonic(MNEMONIC);
    }
}
