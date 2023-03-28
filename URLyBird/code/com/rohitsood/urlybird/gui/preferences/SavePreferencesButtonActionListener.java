  package com.rohitsood.urlybird.gui.preferences;

  import com.rohitsood.urlybird.gui.manager.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * When the user clicks the Save button on the Preferences window this listener is invoked.
   * Calls are delegated to the <tt>GuiManager</tt>.
   * @author Rohit Sood
   * @version 1.1
   */
  class SavePreferencesButtonActionListener implements ActionListener
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new <tt>SavePreferencesButtonActionListener</tt> object.
       * Internally stores a reference to the <tt>GuiManager</tt> for later delegation.
       * @param manager The <tt>GuiManager</tt> instance to which calls should be delegated to.
       */
      public SavePreferencesButtonActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * This is invoked when the user clicks on the Save Preferences button.
       * The call is delegated to the <tt>GuiManager</tt>.
       * @param event The event that was triggered by the user.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.savePreferencesButtonClicked();
      }
  }
