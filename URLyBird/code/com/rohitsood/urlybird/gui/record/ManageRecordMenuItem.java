  package com.rohitsood.urlybird.gui.record;

  import com.rohitsood.urlybird.gui.manager.GuiManager;

  import javax.swing.JMenuItem;


  /**
   * The manage record menu item triggers a call to open the manage record internal frame.
   * The call gets delegated to the manager.
   * @author Rohit Sood
   * @version 1.1.
   */
  class ManageRecordMenuItem extends JMenuItem
  {
        /**The title text of the menu item. */
      private static final String TITLE = "Manage Record";

       /**The manager to delegate to. */
      private GuiManager manager;

	/**
	 * The mnemonic for this menu.
	 */
	private static final char MNEMONIC = 'M';
      /**
       * Creates a new ManageRecordMenuItem object.
       *
       * @param manager The manager to delegate to.
       */
      public ManageRecordMenuItem(GuiManager manager)
      {
          super(TITLE);
          this.manager = manager;
          init();
      }

	/**
	 * Initialize the manger record menu item. Adds an <tt>ActionListener</tt>.
	 */
      private void init()
      {
          addActionListener(new ManageRecordMenuItemActionListener(manager));
          setMnemonic(MNEMONIC);
      }
  }
