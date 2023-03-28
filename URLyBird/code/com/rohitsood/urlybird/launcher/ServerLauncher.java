  package com.rohitsood.urlybird.launcher;

  import com.rohitsood.urlybird.server.ConfigurationProperties;
  import com.rohitsood.urlybird.server.DatabaseNetworkServerFactory;
  import com.rohitsood.urlybird.server.DatabaseNetworkServerFactoryImpl;

  import java.rmi.Naming;
  import java.rmi.RMISecurityManager;
  import java.rmi.RemoteException;
  import java.rmi.registry.LocateRegistry;


  /**
   * Launches the application in "server" mode.
   * Reads the <tt>suncertify.properties</tt> file
   * This automatically starts the RMI registry.
   *
   * It binds the <tt>DatabaseNetworkServerFactory</tt> registry.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  class ServerLauncher implements Launcher
  {
      /**The codebase property. */
      private static final String CODEBASE_PROPERTY = "java.rmi.server.codebase";

      /**The user directory property. */
      private static final String USER_DIR_PROPERTY = "user.dir";

      /**The file separator property.*/
      private static final String FILE_SEPARATOR_PROPERTY = "file.separator";

      /**The policy file property.*/
      private static final String POLICY_FILE_PROPERTY = "java.security.policy";

      /**The policy file name.*/
      private static final String POLICY_FILE_NAME = "policy";

      /**The jar file name.*/
      private static final String JAR_FILE_NAME = "runme.jar";

      /**The protocol for loading the codebase. */
      private static final String PROTOCOL = "file:///";

      /**Configuration as specified in the <tt>suncertify.properties</tt> file. */
      ConfigurationProperties config = new ConfigurationProperties();

      /**
       * Launches the server. Reads the configuration file settings.
       * Sets the location of the codebase to the jar runtime.
       * Sets system properties for codebase location, policy file location.
       * Starts the registry and rebinds the server factory implementation.
       *
       */
      public void launch()
      {
          final String cbLoc = PROTOCOL + System.getProperty(USER_DIR_PROPERTY)
              + System.getProperty(FILE_SEPARATOR_PROPERTY) + JAR_FILE_NAME;
          System.setProperty(CODEBASE_PROPERTY, cbLoc);
          System.setProperty(POLICY_FILE_PROPERTY, POLICY_FILE_NAME);
          debug("\nCode base: " + cbLoc);

          try
          {
              startRegistry();
          }
          catch (Exception e)
          {
              debug("Failed to start the registry. " + e.getMessage());
          }

          try
          {
              rebind();
          }
          catch (Exception e)
          {
              debug("Failed to bind. " + e.getMessage());
              e.printStackTrace();
          }
      }

      /**
       * Prints debug statements on the console for development and bug fixes.
       *
       * @param msg The message to print to the console.
       */
      private void debug(String msg)
      {
          if (!DEBUG)
          {
              return;
          }

          System.out.print(msg);
      }

      /**
       * Attempts to locate a current registry and export it.
       * Then attempts to create a new registry and export that.
       */
      private void startRegistry()
      {
          debug("\nStarting Server\n");

          final String port = config.getPort();

          try
          {
              final int portNumber = Integer.parseInt(port);

              LocateRegistry.createRegistry(portNumber);

              debug("Created Registry on " + portNumber);
          }
          catch (RemoteException e)
          {
              System.err.println("Port:" + port + " already in use. Assuming RMI registry exists.");
          }

          if (System.getSecurityManager() == null)
          {
              debug("\nSetting RMI Security Manager...");
              System.setSecurityManager(new RMISecurityManager());
              debug("done\n");
          }

          debug("Server Ready.\n");
      }

      /**
       * Binds the factory object to the registry. If the binding already exists it rebinds it.
       */
      private void rebind()
      {
          debug("\nBinding Remote Object(s)...");

          try
          {
              final DatabaseNetworkServerFactory obj = new DatabaseNetworkServerFactoryImpl();
              debug("\nbinding DatabaseNetworkServerFactory (impl stub) to "
                  + config.getLookupName());

              Naming.rebind(config.getLookupName(), obj);

              debug("\nDatabaseNetworkServerFactory bound in registry.\n");
          }
          catch (SecurityException se)
          {
              System.err.println(
                  "\n Not enough security access to connect & bind remote objects.\n "
                  + se.getMessage());
              System.exit(-1);
          }
          catch (RemoteException re)
          {
              System.err.println("\nCould not bind remote objects to registry: " + re.getMessage());
              System.exit(-1);
          }
          catch (Exception e)
          {
              System.out.println(e.getClass());
              System.err.println("\nCould not bind remote objects  error: " + e.getMessage());
              System.exit(-1);
          }
      }
  }
