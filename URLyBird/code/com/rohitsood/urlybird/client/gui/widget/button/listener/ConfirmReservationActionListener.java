  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * Listens for action events on the confirm reservation button.
   * When the user clicks on the Confirm Reservation button this listener is trigerred.
   * The calls are delegated to the <tt>GuiManager</tt>
   * @author Rohit Sood
   * @version 1.1
   */
  public class ConfirmReservationActionListener implements ActionListener
  {
      /**The manager to which calls are delegated to. */
      private GuiManager manager;

      /**
       * Creates a new ConfirmReservationActionListener object.
       *
       * @param manager The manager to delegate calls to.
       */
      public ConfirmReservationActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * Delegates the event to the manager. This is called when the user clicks the Confirm Reservation Button.
       *
       * @param event The event that triggered.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.confirmReservationButtonClicked();
      }
  }
