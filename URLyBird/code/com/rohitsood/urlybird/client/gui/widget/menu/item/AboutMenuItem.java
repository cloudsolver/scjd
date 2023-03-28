  package com.rohitsood.urlybird.client.gui.widget.menu.item;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import javax.swing.JMenuItem;


  /**
   * Menu item which triggers the display of an 'about' information dialog.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class AboutMenuItem extends JMenuItem
  {
      /**The title text of the menu item. */
      private static final String TITLE = "About";

      /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new AboutMenuItem object.
       *
       * @param manager The manager to delegate to.
       */
      public AboutMenuItem(GuiManager manager)
      {
          super(TITLE);
          this.manager = manager;
      }
  }
