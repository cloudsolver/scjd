  package com.rohitsood.urlybird.gui.record;

  import com.rohitsood.urlybird.gui.manager.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * This <tt>ActionListener</tt> implementation listens to user action on the <tt>ManageRecordMenuItem</tt>.
   * Calls are delegated to the <tt>GuiManager</tt> for execution.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  class ManageRecordMenuItemActionListener implements ActionListener
  {
	/**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new ManageRecordMenuItemActionListener object.
       *
       * @param manager The manager to delegate to.
       */
      public ManageRecordMenuItemActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

	/**
	 * Called when an action event was triggered on the menu item.
	 * Delegates to the <tt>GuiManager</tt>
	 * @param event The event that was triggered.
	 */
      public void actionPerformed(ActionEvent event)
      {
          manager.openManageRecordInternalFrame();
      }
  }
