  package com.rohitsood.urlybird.mode;


  /**
   * Specifies the mode of the application.
   * Three modes are currently specified:<br>
   * 1. Online - This connects to the remote database via a network database server.<br>
   * 2. Offline - This works by connecting to a local database file bypassing all networking.<br>
   * 3. Server - This starts the network database server.<br>
   * All classes implementing this interface should be mode-conscious.
   * Every class that implements this interface must know the mode its running in.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public interface Mode
  {
      /**
       * Constant for Network Mode (Online).
       */
      int ONLINE = 1;

      /**
       * Constant for Non-Network Mode (Offline).
       */
      int OFFLINE = 2;

      /**
       * Constant for Server mode.
       */
      int SERVER = 3;

      /**
       * Gets the mode in which the current application instance is running.
       *
       * @return The mode constant for this client instance.
       */
      int getMode();
  }
