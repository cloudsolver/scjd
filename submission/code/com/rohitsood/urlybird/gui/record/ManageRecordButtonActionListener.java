package com.rohitsood.urlybird.gui.record;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listens for all actions on the manage record button panel of the Manage Record window. Actions are delegated to the
 * <tt>GuiManager</tt>
 *
 * @author Rohit Sood
 * @version 1.1
 */
class ManageRecordButtonActionListener implements ActionListener {
    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>AddButtonActionListener</tt> object.
     *
     * @param manager The manager to delegate to.
     */
    public ManageRecordButtonActionListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Called when the add button is clicked. The action is delegated to the <tt>GuiManager</tt> for handling.
     *
     * @param event The event that occured.
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ManageRecordButtonPanel.ADD_TEXT)) {
            manager.addButtonClicked();
        } else if (event.getActionCommand().equals(ManageRecordButtonPanel.EDIT_TEXT)) {
            manager.editButtonClicked();
        } else if (event.getActionCommand().equals(ManageRecordButtonPanel.SAVE_TEXT)) {
            manager.updateButtonClicked();
        } else if (event.getActionCommand().equals(ManageRecordButtonPanel.DELETE_TEXT)) {
            manager.deleteButtonClicked();
        } else if (event.getActionCommand().equals(ManageRecordButtonPanel.CANCEL_TEXT)) {
            manager.cancelButtonClicked();
        }else{
        	throw new IllegalArgumentException("Unknown action occurred on the button panel: "+event.getActionCommand());
        }
    }
}
