package com.rohitsood.urlybird.gui.record;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.button.CommonButton;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * Provides buttons for the user to interact with the record. Allows the user to add a new record, edit, update and
 * delete an existing record.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class ManageRecordButtonPanel extends JPanel {
    /** The width for the rigid area. */
    private static final int RIGID_AREA_WIDTH = 10;

    /** Extra width for a rigid area. */
    private static final int RIGID_AREA_WIDTH_EXTRA = 20;

    /** Rigid area height. */
    private static final int RIGID_AREA_HEIGHT = 30;

    /** The manager to delegate to. */
    private GuiManager manager;

    /** The add button. Adds a new record into the data store. */
    private CommonButton addButton;

    /** The edit button. Enables editing of the record from the user interace. */
    private CommonButton editButton;

    /** The save button. Updates the data. */
    private CommonButton saveButton;

    /** The delete button. Deletes the current record from the data store. */
    private CommonButton deleteButton;

    /** The cancel button. Cancels the current record and disables all fields. */
    private CommonButton cancelButton;

    /**The text for the add button.*/
    static final String ADD_TEXT = "Add";

    /**The text for the edit button.*/
    static final String EDIT_TEXT = "Edit";

    /**The text for the save button.*/
	static final String SAVE_TEXT = "Save";

    /**The text for the delete button.*/
	static final String DELETE_TEXT = "Delete";

    /**The text for the cancel button.*/
	static final String CANCEL_TEXT = "Cancel";

    /**
     * Creates a new <tt>ManageRecordButtonPanel</tt> object. Registers with the <tt>GuiManager</tt>
     *
     * @param manager The manager to delegate to.
     */
    public ManageRecordButtonPanel(GuiManager manager) {
        super();
        this.manager = manager;
        manager.setManageRecordButtonPanel(this);
        init();
    }

    /**
     * Accessor for the cancel button instance.
     *
     * @return The cancel button instance.
     */
    public CommonButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Accessor for the add button instance.
     *
     * @return The add button instance.
     */
    public CommonButton getAddButton() {
        return addButton;
    }

    /**
     * Accessor for the delete button.
     *
     * @return The delete button.
     */
    public CommonButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Accessor for the edit button.
     *
     * @return The edit button.
     */
    public CommonButton getEditButton() {
        return editButton;
    }

    /**
     * Accessor for the save button.
     *
     * @return The save button.
     */
    public CommonButton getSaveButton() {
        return saveButton;
    }

    /**
     * Initializes the user interface.
     */
    private void init() {
        final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
		ManageRecordButtonActionListener listener=new ManageRecordButtonActionListener(manager);
        addButton = new CommonButton(ADD_TEXT);
        addButton.addActionListener(listener);

        editButton = new CommonButton(EDIT_TEXT);
        editButton.addActionListener(listener);

        saveButton = new CommonButton(SAVE_TEXT);
        saveButton.addActionListener(listener);

        deleteButton = new CommonButton(DELETE_TEXT);
        deleteButton.addActionListener(listener);

        cancelButton = new CommonButton(CANCEL_TEXT);
        cancelButton.addActionListener(listener);

        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
        add(addButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
        add(editButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
        add(saveButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
        add(deleteButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
        add(cancelButton);
        add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
        setBorder(LineBorder.createBlackLineBorder());
    }
}
