  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * Listener for user actions on the cancel button on the preferences dialog.
   * The user triggers a call this class. This class delegates the handling to the <tt>GuiManager</tt>
   *
   * @author Rohit Sood
   * @version 1.2
   */
  public class PreferencesCancelButtonActionListener implements ActionListener
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new PreferencesCancelButtonActionListener object.
       *
       * @param manager The manager to register this component with.
       */
      public PreferencesCancelButtonActionListener(GuiManager manager)
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
          manager.preferencesCancelButtonClicked();
      }
  }
