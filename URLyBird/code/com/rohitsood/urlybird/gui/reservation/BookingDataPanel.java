package com.rohitsood.urlybird.gui.reservation;


import com.rohitsood.urlybird.gui.widget.panel.AbstractDataPanel;
import com.rohitsood.urlybird.gui.widget.panel.DataPanelOrientation;


/**
 * The <tt>AbstractDataPanel</tt> implementation which lets the agent book a room for a customer.
 * Agent must enter the number of guests, date for booking, and customer id.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class BookingDataPanel extends AbstractDataPanel {

    /**The text to display for number of guests.*/
    private static final String NUMBER_OF_GUESTS = "Number of Guests:";

    /**Total number of textfields. */
    private static final int FIELD_COUNT = 3;

    /**Total number of rows in the grid of widgets.*/
    private static final int ROW_COUNT = 3;

    /**Total number of columns in the grid of widgets. */
    private static final int COL_COUNT = 2;

    /**The record against which the booking should be made. */
    private int recId;

    /**
     * Creates a new BookingDataPanel object.
     * Sets the orientation of the panel , sets the field count for initialization.
     * @param recId The record against which the booking should be made.
     */
    public BookingDataPanel(int recId) {
        this.recId = recId;
        orientation = new DataPanelOrientation(ROW_COUNT, COL_COUNT);
        setFieldCount(FIELD_COUNT);
        init();
    }

    /**
     * Initializes the panel.
     * Disables the record id field, because it can only be read-only.
     */
    protected void init() {
        super.init();
        fields[0].setText("" + recId);
        fields[0].setEnabled(false);
    }

    /**
     * Creates gui widgets for the panel.
     * Populates the labels and fields arrays with references of labels and text fields.
     */
    protected void createWidgets() {
        labels[0] = recordTextLabel;
        fields[0] = recordTextField;

        labels[1] = maxTextLabel;
        fields[1] = maxTextField;
        maxTextLabel.setText(NUMBER_OF_GUESTS);

        labels[2] = customerTextLabel;
        fields[2] = customerTextField;
    }
}
