  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * Listens to all actions on the cancel button. This is invoked when user clicks cancel.
   *
   * @author Rohit Sood
   * @version 1.0
   */
  public class CancelButtonActionListener implements ActionListener
  {
      /**The manager which will recieve all calls from this class.*/
      private GuiManager manager;

      /**
       * Creates a new CancelButtonActionListener object.
       *
       * @param manager The <tt>GuiManager</tt> to delegate to
       */
      public CancelButtonActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * Called when an action event was triggered on the cancel button.
       * Delegates to the <tt>GuiManager</tt>
       * @param event The event that was triggered.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.cancelButtonClicked();
      }
  }
