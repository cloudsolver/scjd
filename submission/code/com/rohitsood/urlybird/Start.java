package com.rohitsood.urlybird;

import com.rohitsood.urlybird.launcher.Launcher;
import com.rohitsood.urlybird.launcher.LauncherFactory;


/**
 * Starts the application. It is called by the user to start the gui and/or the server.<br>
 * The following command line arguments are supported for mode:<br>
 * (1)  server<br>
 * (2)  alone<br>
 * <br>
 * When no mode is specified, the <tt>default</tt> mode is used.  The default mode starts the client in online mode.
 * "alone" mode starts the application in offline mode, which completly bypasses all networking. <br>
 * If an invalid mode is used the usage text is displayed.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public final class Start {
    /**
     * The private constructor is required since all methods defined are static.
     */
    private Start() {
    }

    /**
     * Uses the <code>LauncherFactory</code> to obtain and launch a specific <code>Launcher</code>. The mode is passed
     * in as a string and the matching launcher is called which starts the application.
     *
     * @param mode the mode with which to launch the application
     */
    public static void launch(String mode) {
        final LauncherFactory launcherFactory = new LauncherFactory();
        final Launcher launcher = launcherFactory.getLauncher(mode);
        launcher.launch();
    }

    /**
     * Starting point of the application. Requires that a mode be passed in as a first parameter. Passing in no
     * arguments results in starting the application in the default mode with networking. Invalid arguments results in
     * usage being displayed on the console.
     *
     * @param args the arguments that decide which mode to launch the application in.
     */
    public static void main(String[] args) {
        if (!validate(args)) {
            showUsageAndExit();
        }

        launch(getMode(args));
    }

    /**
     * Gets the mode in which the application should be launched.
     *
     * @param args The command line arguements
     *
     * @return Gets the client.
     */
    private static String getMode(String[] args) {
        String mode = Launcher.CLIENT;//start the client in default mode.

        if ((args != null) && (args.length > 0)) {
            mode = args[0];
        }

        return mode;
    }

    /**
     * Validates the command line arguements.
     *
     * @param args The command line arguments to validate.
     *
     * @return Returns <tt>true</tt> if the arguements are valid, <tt>false</tt> otherwise.
     */
    private static boolean validate(String[] args) {
        boolean valid = false;

        if ((null == args) || (args.length < 1)) {
            valid = true;
        } else if (args.length > 0) {
            if (args[0].equals(Launcher.ALONE) || args[0].equals(Launcher.SERVER)) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Shows the usage. If there was a problem with the command line arguement this message is displayed.
     */
    private static void showUsageAndExit() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("\nUsage:\n");
        buffer.append("java -jar runme.jar server\n or \n");
        buffer.append("java -jar runme.jar alone\n or \n");
        buffer.append("java -jar runme.jar");
        System.out.println(buffer.toString());
        System.exit(0);
    }
}
