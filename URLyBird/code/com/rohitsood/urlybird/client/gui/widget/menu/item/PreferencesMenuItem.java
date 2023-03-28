  package com.rohitsood.urlybird.client.gui.widget.menu.item;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.listener.PreferencesMenuItemActionListener;

  import javax.swing.JMenuItem;


  /**
   * The preferences menu item triggers a call to open the preferences dialog window.
   * The call gets delegated to the manager.
   * @author Rohit Sood
   * @version 1.1.
   */
  public class PreferencesMenuItem extends JMenuItem
  {
      /**The title text of the menu item. */
      private static final String TITLE = "Preferences";

      /**
       * Creates a new PreferencesMenuItem object.
       * Adds the <tt>PreferencesMenuItemActionListener</tt>.
       *
       * @param manager The manager to delegate to.
       */
      public PreferencesMenuItem(GuiManager manager)
      {
          super(TITLE);
          addActionListener(new PreferencesMenuItemActionListener(manager));
      }
  }
