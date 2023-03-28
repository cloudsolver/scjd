package com.rohitsood.urlybird.gui.reservation;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listens for action events on the confirm reservation button and close buttons. When the user clicks on either button
 * this listener is trigerred. The calls are delegated to the <tt>GuiManager</tt>
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class ReservationActionListener implements ActionListener {
    /** The manager to which calls are delegated to. */
    private GuiManager manager;

    /**
     * Creates a new <tt>ConfirmReservationActionListener</tt> object.
     *
     * @param manager The manager to delegate calls to.
     */
    public ReservationActionListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Delegates the event to the manager. This is called when the user clicks the Confirm Reservation Button.
     *
     * @param event The event that triggered.
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ConfirmBookingDialog.CONFIRM)) {
            manager.confirmReservationButtonClicked();
        } else if (event.getActionCommand().equals(ConfirmBookingDialog.CLOSE)) {
            manager.confirmCloseReservationButtonClicked();
        }
    }
}
