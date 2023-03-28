package com.rohitsood.urlybird.gui.preferences;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The listener implementation for listening to user action on the <tt>PreferencesMenuItem</tt>. Calls are delegated to
 * the <tt>GuiManager</tt> for execution.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class PreferencesMenuItemActionListener implements ActionListener {
    /** The manager to delegate to. */
    private GuiManager manager;

    /**
     * Creates a new <tt>PreferencesMenuItemActionListener</tt> object.
     *
     * @param manager The manager to delegate to.
     */
    public PreferencesMenuItemActionListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Called when an action event was triggered on the menu item. Delegates to the <tt>GuiManager</tt>.
     *
     * @param event The event that was triggered.
     */
    public void actionPerformed(ActionEvent event) {
        manager.preferencesMenuItemClicked();
    }
}
