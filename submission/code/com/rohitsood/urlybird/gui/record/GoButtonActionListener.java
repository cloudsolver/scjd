package com.rohitsood.urlybird.gui.record;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * When the user clicks the Go button in the Manage Record window this listener is invoked. The record id is read from
 * the corresponding text field. Calls are delegated to the <tt>GuiManager</tt>.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class GoButtonActionListener implements ActionListener {
    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>GoButtonActionListener</tt> object. Internally stores a reference to the <tt>GuiManager</tt>.
     *
     * @param manager The <tt>GuiManager</tt> to which the calls should be delegated.
     */
    public GoButtonActionListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * This is invoked when the user clicks on the go button. The call is delegated to the <tt>GuiManager</tt>.
     *
     * @param event The event that was triggered by the user.
     */
    public void actionPerformed(ActionEvent event) {
        manager.goButtonClicked();
    }
}
