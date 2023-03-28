  package com.rohitsood.urlybird.client.gui;

  import com.rohitsood.urlybird.Mode;


  /**
   * Factory for getting a concrete <tt>GuiManager</tt> implementation instance.
   * The <tt>Launcher</tt> type invokes the factory to get the appropriate instance.
   *
   * @author Rohit Sood
   * @version 1.2
   */
  public class GuiManagerFactory
  {
      /**
       * Provides a concrete implementation instance of <tt>GuiManager</tt>.
       * Based on the mode, it calls workOnline or workOffline on the GuiManager instance.
       * @param mode The mode to use.
       * @return A concrete implementation of <tt>GuiManager</tt>
       */
      public GuiManager getInstance(int mode)
      {
          GuiManager manager = null;

          switch (mode)
          {
              case Mode.OFFLINE:
                  manager = new GuiManagerImpl();
                  manager.workOffline();

                  break;

              case Mode.ONLINE:
                  manager = new GuiManagerImpl();
                  manager.workOnline();

                  break;

              case Mode.SERVER:
                  manager = new GuiManagerImpl();
                  manager.workOffline();

                  break;

              default:
                  throw new IllegalArgumentException("Invalid mode provided " + mode);
          }

          return manager;
      }
  }
