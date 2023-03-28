package com.rohitsood.urlybird.launcher;

/**
 * The factory that creates a launcher based on type requested. The factory returns a reference to a sub-type of the
 * <tt>Launcher</tt> interface. Invoking the launch method will luanch the application in a mode specified by the type
 * of the subclass.
 *
 * @author Rohit Sood
 * @version 1.2
 */
public class LauncherFactory {
    /**
     * No args default constructor creates a new <tt>LauncherFactory</tt> object.
     */
    public LauncherFactory() {
    }

    /**
     * Creates and returns an instance of a <tt>Launcher</tt> sub-class based on type. This will throw an
     * <tt>IllegalArguementException</tt> if an unknown mode is requested.
     *
     * @param mode The mode of the <tt>Launcher</tt>
     *
     * @return A instance of the sub-type of <tt>Launcher</tt> corresponding to the mode requested.
     */
    public Launcher getLauncher(String mode) {
        Launcher launcher = null;

        if (mode != null) {
            if (Launcher.ALONE.equals(mode)) {
                launcher = new AloneLauncher();
            } else if (Launcher.SERVER.equals(mode)) {
                launcher = new ServerLauncher();
            } else if (Launcher.CLIENT.equals(mode)) {
                launcher = new ClientLauncher();
            } else {
                throw new IllegalArgumentException("Unknown Launcher Mode " + mode);
            }
        }

        return launcher;
    }
}
