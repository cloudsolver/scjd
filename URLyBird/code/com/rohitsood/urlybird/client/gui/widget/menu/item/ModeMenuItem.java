  package com.rohitsood.urlybird.client.gui.widget.menu.item;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.listener.ModeMenuItemActionListener;

  import javax.swing.JMenuItem;


/**
 * The mode menu item triggers a call to toggle the mode of the application from offline to online and vice versa.
 * The call gets delegated to the manager.
 * @author Rohit Sood
 * @version 1.1.
 */
  public class ModeMenuItem extends JMenuItem
  {
      /**Text to show when the mode is offline. */
      public static final String ONLINE_TEXT = "Work Online";

      /**Text to show when the mode is online. */
      public static final String OFFLINE_TEXT = "Work Offline";

      /**Mode status. */
      private boolean online;

      /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new ModeMenuItem object.
       *
       * @param manager The manager to delegate to.
       */
      public ModeMenuItem(GuiManager manager)
      {
          this.manager = manager;
          manager.setModeMenuItem(this);

          init();
      }

      /**
       * Initialize the manger record menu item. Adds an <tt>ActionListener</tt>.
       */
      private void init()
      {
          final ModeMenuItemActionListener listener = new ModeMenuItemActionListener(manager);
          addActionListener(listener);

          if (manager.getMode() == Mode.ONLINE)
          {
              setOnline(true);
          }
          else
          {
              setOnline(false);
          }
      }

      /**
       * Getter for the mode status. Values determine if the client is connected to a local database or a remote server.
       * @return If <tt>true</tt> then the client in online.
       */
      public boolean isOnline()
      {
          return online;
      }

      /**
       * Sets the mode status for the application. This results in a change of text.
       *
       * @param online The mode to set the item listener at.
       */
      public void setOnline(boolean online)
      {
          if (online)
          {
              setText(OFFLINE_TEXT);
          }
          else
          {
              setText(ONLINE_TEXT);
          }
      }
  }
