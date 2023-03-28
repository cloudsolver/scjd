package com.rohitsood.urlybird.gui.help;

import com.rohitsood.urlybird.gui.about.AboutMenuItem;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.preferences.PreferencesMenuItem;

import javax.swing.JMenu;


/**
 * The help menu allows the user to set preferences for the application and see
 * the about dialog. In the future this can be extended to provide online help
 * funtionality.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class HelpMenu extends JMenu {
    /** The title of the menu item. */
    private static final String NAME = "Help";

    /** The mnemonic for this menu. */
    private static final char MNEMONIC = 'H';

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>HelpMenu</tt> object.
     * Initializes the <tt>PreferencesMenuItem</tt> and <tt>AboutMenuItem</tt> and sets the mnemonic.
     * @param manager The manager to register this component with.
     */
    public HelpMenu(GuiManager manager) {
        super(NAME);
        this.manager = manager;

        init();
    }

    /**
     * Initializes the help menu.
     */
    private void init() {
        final PreferencesMenuItem preferences = new PreferencesMenuItem(manager);
        add(preferences);

        final AboutMenuItem about = new AboutMenuItem(manager);
        add(about);

        setMnemonic(MNEMONIC);
    }
}
