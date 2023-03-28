  package com.rohitsood.urlybird.client.gui.widget.menu.item;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.listener.ExitMenuItemActionListener;

  import javax.swing.JMenuItem;


  /**
   * Allows the user to exit the application. Delegates an exit request to the manager.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class ExitMenuItem extends JMenuItem
  {
       /**The title text of the menu item. */
      private static final String TITLE = "Exit";

       /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new ExitMenuItem object.
       *
       * @param manager The manager to delegate to.
       */
      public ExitMenuItem(GuiManager manager)
      {
          super(TITLE);
          this.manager = manager;
          manager.setExitMenuItem(this);
          init();
      }

      /**
       * Initialize the exit menu item. Adds an <tt>ActionListener</tt>.
       */
      private void init()
      {
          addActionListener(new ExitMenuItemActionListener(manager));
      }
  }
