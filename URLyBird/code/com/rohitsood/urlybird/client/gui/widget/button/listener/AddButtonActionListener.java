  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * Listens for all actions on the add button of the Manage Record window.
   * Actions are delegate to the <tt>GuiManager</tt>
   *
   * @author Rohit Sood
   * @version 1.0
   */
  public class AddButtonActionListener implements ActionListener
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new AddButtonActionListener object.
       *
       * @param manager The manager to delegate to.
       */
      public AddButtonActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * Called when the add button is clicked. The action is delegate to the <tt>GuiManager</tt> for handling.
       * @param event The event that occured.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.addButtonClicked();
      }
  }
