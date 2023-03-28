package com.rohitsood.urlybird.gui.record;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.panel.AbstractDataPanel;
import com.rohitsood.urlybird.gui.widget.panel.DataPanelOrientation;
import com.rohitsood.urlybird.gui.widget.textfield.CommonTextField;


/**
 * Creates the panel to hold the user interface which lets the agent manage the room records. Buttons are provided to
 * add, update (save), and delete. Also a quick search by record id functionality is provided. This user interface is
 * intended to be used by adminstrators. Adminstrators may modify available records, delete customer bookings and look
 * up records quickly for reading and updating.
 *
 * @author Rohit Sood
 * @version 1.4
 */
public class ManageRecordDataPanel extends AbstractDataPanel {
    /** Total number of textfields. */
    private static final int FIELD_COUNT = 8;

    /** Total number of rows in the grid of widgets. */
    private static final int ROW_COUNT = 8;

    /** Total number of columns in the grid of widgets. */
    private static final int COL_COUNT = 2;

    /**
     * Creates a new ManageRecordDataPanel object.
     *
     * @param manager The manager to delegate to.
     */
    public ManageRecordDataPanel(GuiManager manager) {
        manager.setManageRecordDataPanel(this);
        super.setFieldCount(FIELD_COUNT);

        orientation = new DataPanelOrientation(ROW_COUNT, COL_COUNT);
        init();
        enableFields(false);
    }

    /**
     * Sets the data for a given record id. Text fields are loaded based on the record id and data.
     *
     * @param recId The record id to be populated.
     * @param data The data to populate the text fields.
     */
    public void setData(int recId, String[] data) {
        loadTextFields(recId, data);
    }

    /**
     * Accessor for the id text field. Once a reference is obtained, the text field can be manipulated to be enabled or
     * disabled.
     *
     * @return The <tt>CommonTextField</tt> that holds the record id;
     */
    public CommonTextField getIDField() {
        return fields[RECORD_INDEX];
    }

    /**
     * Creates widgets of this panel.
     */
    protected void createWidgets() {
        labels[RECORD_INDEX] = recordTextLabel;
        fields[RECORD_INDEX] = recordTextField;

        labels[HOTEL_INDEX] = hotelTextLabel;
        fields[HOTEL_INDEX] = hotelTextField;

        labels[CITY_INDEX] = cityTextLabel;
        fields[CITY_INDEX] = cityTextField;

        labels[MAX_INDEX] = maxTextLabel;
        fields[MAX_INDEX] = maxTextField;

        labels[SMOKING_INDEX] = smokingTextLabel;
        fields[SMOKING_INDEX] = smokingTextField;

        labels[PRICE_INDEX] = priceTextLabel;
        fields[PRICE_INDEX] = priceTextField;

        labels[DATE_INDEX] = dateTextLabel;
        fields[DATE_INDEX] = dateTextField;

        labels[CUSTOMER_INDEX] = customerTextLabel;
        fields[CUSTOMER_INDEX] = customerTextField;
    }

    /**
     * Enables all fields except the record id field.
     *
     * @param enable If <tt>true</tt> all fields are enabled except the record id field, if <tt>false</tt> all fields
     *        are disabled.
     */
    public void enableFields(boolean enable) {
        super.enableFields(enable);
        recordTextField.setEnabled(false); //always disable the record field
    }
}
