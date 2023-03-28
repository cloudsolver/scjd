package com.rohitsood.urlybird.gui.preferences;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenuItem;


/**
 * The preferences menu item triggers a call to open the preferences dialog window. The call gets delegated to the
 * manager.
 *
 * @author Rohit Sood
 * @version 1.1.
 */
public class PreferencesMenuItem extends JMenuItem {
    /** The title text of the menu item. */
    private static final String TITLE = "Preferences";

    /** The mnemonic for this menu. */
    private static final char MNEMONIC = 'P';

    /**
     * Creates a new PreferencesMenuItem object. Adds the <tt>PreferencesMenuItemActionListener</tt>.
     *
     * @param manager The manager to delegate calls to.
     */
    public PreferencesMenuItem(GuiManager manager) {
        super(TITLE);
        addActionListener(new PreferencesMenuItemActionListener(manager));
        setMnemonic(MNEMONIC);
    }
}
