  package com.rohitsood.urlybird.launcher;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.GuiManagerFactory;
  import com.rohitsood.urlybird.client.gui.MainWindow;


/**
 * This will launch the application client in the "alone" mode. The client works offline.
 * Starts the <tt>GuiManager</tt> with a local client that connects to the local database.
 * Launches the main window of the application.
 *
 * @author Rohit Sood
 * @version 1.2
 */
  public class AloneLauncher implements Launcher
  {
	/**
	 * Launches the application in "alone" mode. Network capabilities are disabled.
	 * The main window is launched with a <tt>GuiManager</tt> that uses a local database client.
	 */
      public void launch()
      {
          final GuiManagerFactory factory = new GuiManagerFactory();
          final GuiManager      manager = factory.getInstance(Mode.OFFLINE);
          final MainWindow      window  = new MainWindow(manager);
      }
  }
