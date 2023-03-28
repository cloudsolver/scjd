package com.rohitsood.urlybird.gui.reservation;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.button.CommonButton;
import com.rohitsood.urlybird.gui.widget.textfield.CommonTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JPanel;


/**
 * This dialog appears when the user clicks the Book It button on the search
 * screen. It is a <tt>JDialog</tt> that containes the <tt>BookingDataPanel</tt>.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public class ConfirmBookingDialog extends JDialog {
    /** The dialog title. */
    private static final String TITLE = "Confirm Booking";

    /** The confirmation text. */
    static final String CONFIRM = "Reserve Room";

    /** The close dialog text. */
    static final String CLOSE = "Cancel";

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /** The record id for which to confirm the booking. */
    private int recId;

    /** The data panel that holds the booking data */
    private BookingDataPanel dataPanel;

    /**
     * Creates a new <tt>ConfirmBookingDialog</tt> object.
     *
     * @param manager The manager to register this with.
     * @param recId The record id for which the booking has to be made.
     */
    public ConfirmBookingDialog(GuiManager manager, int recId) {
        super(manager.getMainWindow(), true);
        this.manager = manager;
        this.recId = recId;
        manager.setConfirmBookingDialog(this);
        init();
        pack();
        setVisible(true);
    }

    /**
     * Returns the record id for which the booking is to be made.
     * Call is delegated to the package-visible <tt>BookingDataPanel</tt>.
     * @return Returns the record id.
     */
    public String getRecordId() {
        return dataPanel.getRecordId();
    }

    /**
     * Returns the maximum occupancy.
     * Call is delegated to the package-visible <tt>BookingDataPanel</tt>.
     * @return The maximum occupancy for the reservation request.
     */
    public String getMaxOccupancy() {
        return dataPanel.getMaxOccupancy();
    }

    /**
     * Returns the customer id as entered in the panel.
     * Call is delegated to the package-visible <tt>BookingDataPanel</tt>.
     * @return The customer id for the reservation request.
     */
    public String getCustomerId() {
        return dataPanel.getCustomerId();
    }

    /**
     * Initializes the confirm booking dialog.
     */
    private void init() {
        setTitle(TITLE);

        final ReservationActionListener listener = new ReservationActionListener(manager);

        final BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);

        dataPanel = new BookingDataPanel(recId);
        getContentPane().add(BorderLayout.CENTER, dataPanel);

        final CommonTextField[] fields = dataPanel.getFields();

        for (int c = 0; c < fields.length; c++) {
            fields[c].addActionListener(listener);
        }

        final CommonButton confirm = new CommonButton(CONFIRM);
        confirm.addActionListener(listener);

        final CommonButton cancel = new CommonButton(CLOSE);
        cancel.addActionListener(listener);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        getContentPane().add(BorderLayout.PAGE_END, buttonPanel);
		final Point location=manager.getMainWindow().getLocation();
        final Dimension size = manager.getMainWindow().getSize();
        final int x = location.x+ (size.width / 2) - (this.getPreferredSize().width / 2);
        final int y = location.y+ (size.height / 2) - (this.getPreferredSize().height / 2);
        setLocation(new Point(x, y));
    }
}
