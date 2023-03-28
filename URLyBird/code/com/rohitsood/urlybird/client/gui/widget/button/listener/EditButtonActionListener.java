  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * When the user clicks the edit button in the Manager Record window this listener is invoked.
   * Calls are delegated to the <tt>GuiManager</tt>.
   * @author Rohit Sood
   * @version 1.1
   */
  public class EditButtonActionListener implements ActionListener
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new <tt>EditButtonActionListener</tt> object.
       * Internally stores the manager in a memeber variable for delegation of action events.
       * @param manager The manager to which calls should be delegate.
       */
      public EditButtonActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * This is invoked when the user clicks on the edit button.
       * The call is delegated to the <tt>GuiManager</tt>.
       * @param event The event that was triggered by the user.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.editButtonClicked();
      }
  }
