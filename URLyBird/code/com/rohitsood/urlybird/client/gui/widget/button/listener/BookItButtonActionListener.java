  package com.rohitsood.urlybird.client.gui.widget.button.listener;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;


  /**
   * Listens to all actions on the Book It button. Actions are delegated to the <tt>GuiManager</tt>
   *
   * @author Rohit Sood
   * @version 1.1
   * */
  public class BookItButtonActionListener implements ActionListener
  {
      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new BookItButtonActionListener object.
       * Registers this object with the <tt>GuiManager</tt>
       * @param manager The manager to delegate to.
       */
      public BookItButtonActionListener(GuiManager manager)
      {
          this.manager = manager;
      }

      /**
       * Called when an action event happens. Call is delegate to the <tt>GuiManager</tt>
       * @param event The action event that is delegated.
       */
      public void actionPerformed(ActionEvent event)
      {
          manager.bookButtonClicked();
      }
  }
