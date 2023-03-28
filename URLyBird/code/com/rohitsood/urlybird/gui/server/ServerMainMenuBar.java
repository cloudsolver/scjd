package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.help.HelpMenu;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.MainMenuBar;


/**
 * The <tt>MainMenuBar</tt> implementation that appears on the window if the application is started in Server
 * mode. Contains a server menu and help menu.
 *
 * @author Rohit Sood
 * @version 1.0
 */
 class ServerMainMenuBar extends MainMenuBar {
    /**
     * Creates a new ServerMainMenuBar object.
     *
     * @param manager The manager to use for other objects.
     */
    ServerMainMenuBar(GuiManager manager) {
        super(manager);
    }

    /**
     * Adds custom menus. Adds a server menu and the standard help menu.
     *
     * @param manager The manager used for constucting menus.
     */
    protected void addMenus(GuiManager manager) {
        add(new ServerMenu(manager));
        add(new HelpMenu(manager));
    }
}
