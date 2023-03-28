  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * The listener for the confirm close button. This is called when the actor clicks close.
   *
   * @author Rohit Sood
   * @version 1.0
   */
  public class ConfirmCloseReservationActionListener implements ActionListener
  {
      /**The manager which will recieve all calls from this class.*/
      private GuiManager manager;

      /**
       * Creates a new ConfirmCloseReservationActionListener object.
       *
       * @param manager The <tt>GuiManager</tt> that will recieve calls from this object.
       */
      public ConfirmCloseReservationActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * This method is called when the Close button is clicked by the user.
       * Call is delegate to the <tt>GuiManager</tt>
       * @param event The action event that was triggered.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.confirmCloseReservationButtonClicked();
      }
  }
