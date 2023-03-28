  package com.rohitsood.urlybird.client.gui;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.gui.widget.MainMenuBar;

  import java.awt.event.WindowAdapter;
  import java.awt.event.WindowEvent;

  import javax.swing.JDesktopPane;
  import javax.swing.JFrame;


  /**
   * The main window of the gui. This window is a JFrame which can hold internal frames. The window starts
   * maximized to current screen size, default size is set to 640 by 480 pixels.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class MainWindow extends JFrame
  {
      /** Constant for default screen width. */
      private static final int SCREEN_WIDTH = 640;

      /** Constant for default screen height. */
      private static final int SCREEN_HEIGHT = 480;

      /** Title for online mode. */
      private static final String ONLINE_TITLE = "URLyBird Application (Online)";

      /** Title for offline mode. */
      private static final String OFFLINE_TITLE = "URLyBird Application (Offline)";

      /** Manager where this component registers itelf. */
      private GuiManager manager;

      /** The desktop pane of this window. */
      private JDesktopPane desktop;

      /**
       * Constructs a <tt>MainWindow</tt> object. Initializes the window with a default size, then
       * maximizes it.
       *
       * @param manager The <tt>GuiManager</tt> to register with.
       */
      public MainWindow(GuiManager manager)
      {
          this.manager = manager;
          manager.setMainWindow(this);
          init();
      }

      /**
       * Sets the title based on the mode.  The window title alters to reflect the title.
       *
       * @param online The mode the title should read.
       */
      public void setTitle(boolean online)
      {
          if (online)
          {
              setTitle(ONLINE_TITLE);
          }
          else
          {
              setTitle(OFFLINE_TITLE);
          }
      }

      /**
       * Initializes the frame. Adds a desktop pane, menu bar, window listener, and sets the title.
       */
      private void init()
      {
          desktop = new JDesktopPane();
          manager.setDesktopPane(desktop);
          getContentPane().add(desktop);

          super.setJMenuBar(new MainMenuBar(manager));
          super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
          super.setExtendedState(JFrame.MAXIMIZED_BOTH);
          super.setVisible(true);

          super.addWindowListener(new WindowAdapter()
              {
                  public void windowClosing(WindowEvent we)
                  {
                      manager.exitApplication();
                  }
              });

          if (manager.getMode() == Mode.ONLINE)
          {
              setTitle(true);
          }
          else
          {
              setTitle(false);
          }
      }
  }
