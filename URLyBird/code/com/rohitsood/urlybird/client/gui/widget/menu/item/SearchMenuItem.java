  package com.rohitsood.urlybird.client.gui.widget.menu.item;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.listener.SearchMenuItemActionListener;

  import javax.swing.JMenuItem;


  /**
   * The search menu item triggers a call to open the search internal frame.
   * This allows the user to search records in a very flexible manner.
   * The call gets delegated to the manager.
   * @author Rohit Sood
   * @version 1.1.
   */
  public class SearchMenuItem extends JMenuItem
  {
        /**The title text of the menu item. */
      private static final String TITLE = "Search";

      /**The manager to delegate to.*/
      private GuiManager manager;

      /**
       * Creates a new SearchMenuItem object.
       *
       * @param manager The manager to delegate to.
       */
      public SearchMenuItem(GuiManager manager)
      {
          super(TITLE);
          this.manager = manager;
          init();
      }

      /**
       * Initializes this. Adds an action listener which delegates call to the <tt>GuiManager</tt>.
       */
      private void init()
      {
          addActionListener(new SearchMenuItemActionListener(manager));
      }
  }
