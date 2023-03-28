  package com.rohitsood.urlybird.client.gui.widget.menu;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ExitMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ManageRecordMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ModeMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.SearchMenuItem;

  import javax.swing.JMenu;


  /**
   * The menu for all major day-to-day functionality of the booking agent lives here.
   * Menu items in this menu permit the user to manage, search, and work online or offline and exit.
   * @author Rohit Sood
   * @version 1.1
   */
  public class RecordMenu extends JMenu
  {
      /**The title of the menu. */
      private static final String TITLE  = "Record";

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new FileMenu object.
       *
       * @param manager The manager to register the object with.
       */
      public RecordMenu(GuiManager manager)
      {
          super(TITLE);
          this.manager = manager;

          init();
      }

      /**
       * Initialized the menu item.
       */
      private void init()
      {
          final SearchMenuItem     searchMenuItem = new SearchMenuItem(manager);
          final ManageRecordMenuItem editMenuItem = new ManageRecordMenuItem(manager);
          final ExitMenuItem       exitMenuItem = new ExitMenuItem(manager);
          final ModeMenuItem       modeMenuItem = new ModeMenuItem(manager);

          add(searchMenuItem);
          add(editMenuItem);
          add(modeMenuItem);
          add(exitMenuItem);
      }
  }
