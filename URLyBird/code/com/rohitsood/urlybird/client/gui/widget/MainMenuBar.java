  package com.rohitsood.urlybird.client.gui.widget;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.RecordMenu;
  import com.rohitsood.urlybird.client.gui.widget.menu.HelpMenu;

  import javax.swing.JMenuBar;


  /**
   * The main menu that appears on the main window. This displays all the options available to interact with the system.
   * Consists of a File menu and a Help Menu.
   * @author Rohit Sood
   * @version 1.0
   */
  public class MainMenuBar extends JMenuBar
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new <tt>MainMenuBar</tt> object and registers it with a <tt>GuiManager</tt>.
       *
       * @param manager The <tt>GuiManager</tt> to register this with.
       */
      public MainMenuBar(GuiManager manager)
      {
          super();
          this.manager = manager;
          init();
      }

      /**
       * Initializes the main menu bar. Adds a <tt>FileMenu</tt> and a <tt>HelpMenu</tt>.
       */
      private void init()
      {
          final RecordMenu file = new RecordMenu(manager);
          final HelpMenu help = new HelpMenu(manager);
          add(file);
          add(help);
      }
  }
