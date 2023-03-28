  package com.rohitsood.urlybird.client.gui.widget.dialog;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.button.CommonButton;
  import com.rohitsood.urlybird.client.gui.widget.button.listener.ConfirmCloseReservationActionListener;
  import com.rohitsood.urlybird.client.gui.widget.button.listener.ConfirmReservationActionListener;
  import com.rohitsood.urlybird.client.gui.widget.panel.BookingDataPanel;
  import com.rohitsood.urlybird.client.gui.widget.textfield.CommonTextField;

  import java.awt.BorderLayout;
  import java.awt.Dimension;
  import java.awt.Point;

  import javax.swing.JDialog;
  import javax.swing.JPanel;


  /**
   * This dialog appears when the user clicks the Book It button on the search screen.
   * It is a <tt>JDialog</tt> that containes the <tt>BookingDataPanel</tt>.
   * @author Rohit Sood
   * @version 1.3
   **/
  public class ConfirmBookingDialog extends JDialog
  {
      /**The dialog title. */
      private static final String TITLE = "Confirm Booking";

      /**The confirmation text. */
      private static final String CONFIRM = "Confirm Reservation";

      /**The close dialog text. */
      private static final String CLOSE = "Close";

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**The record id for which to confirm the booking. */
      private int recId;

      /**
       * Creates a new ConfirmBookingDialog object.
       *
       * @param manager The manager to register this with.
       * @param recId The record id for which the booking has to be made.
       */
      public ConfirmBookingDialog(GuiManager manager, int recId)
      {
          super(manager.getMainWindow(), true);
          this.manager   = manager;
          this.recId     = recId;
          manager.setConfirmBookingDialog(this);
          init();
          pack();
          setVisible(true);
      }

      /**
       * Initializes the confirm booking dialog.
       */
      private void init()
      {
          setTitle(TITLE);

          final ConfirmReservationActionListener confirmReservationActionListener =
														new ConfirmReservationActionListener(manager);

          final BorderLayout                   layout = new BorderLayout();
          getContentPane().setLayout(layout);

          final BookingDataPanel dataPanel = new BookingDataPanel(manager, recId);
          getContentPane().add(BorderLayout.CENTER, dataPanel);

          final CommonTextField[] fields = dataPanel.getFields();

          for (int c = 0;c < fields.length;c++)
          {
              fields[c].addActionListener(confirmReservationActionListener);
          }

          final CommonButton confirm = new CommonButton(CONFIRM);
          confirm.addActionListener(confirmReservationActionListener);

          final CommonButton cancel = new CommonButton(CLOSE);
          cancel.addActionListener(new ConfirmCloseReservationActionListener(manager));

          final JPanel buttonPanel = new JPanel();
          buttonPanel.add(confirm);
          buttonPanel.add(cancel);

          getContentPane().add(BorderLayout.PAGE_END, buttonPanel);

          final Dimension size = manager.getMainWindow().getSize();
          final int     x = (size.width / 2) + (this.getSize().width / 2);
          final int     y = (size.height / 2) + (this.getSize().height / 2);
          setLocation(new Point(x, y));
      }
  }
