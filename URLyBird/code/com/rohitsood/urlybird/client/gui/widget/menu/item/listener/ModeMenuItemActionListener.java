  package com.rohitsood.urlybird.client.gui.widget.menu.item.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ModeMenuItem;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


/**
 * The listner implementation for listening to user action on the <tt>ModeMenuItem</tt>.
 * Calls are delegated to the <tt>GuiManager</tt> for execution.
 *
 * @author Rohit Sood
 * @version 1.1
 */
  public class ModeMenuItemActionListener implements ActionListener
  {
	/**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new ModeMenuItemActionListener object.
       *
       * @param manager The manager to delegate to.
       */
      public ModeMenuItemActionListener(GuiManager manager)
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
          //intercept the command and make a decision on the manager
          if (event.getActionCommand().equals(ModeMenuItem.ONLINE_TEXT))
          {
              manager.workOnline();
          }

          if (event.getActionCommand().equals(ModeMenuItem.OFFLINE_TEXT))
          {
              manager.workOffline();
          }
      }
  }
