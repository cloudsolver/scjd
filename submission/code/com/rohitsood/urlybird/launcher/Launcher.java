package com.rohitsood.urlybird.launcher;

/**
 * Defines the contract for all launchers of the application. Applications can be launched in multiple modes. Each
 * launch mode has special behavior. The launcher unifies all types of launchers based on mode. All new launchers must
 * implement this.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public interface Launcher {
    /** Server mode constant. */
    String SERVER = "server";

    /** Stand alone mode constant. */
    String ALONE = "alone";

    /** Network enabled client mode constant. */
    String CLIENT = "client";

    /**
     * Launches the application.
     */
    void launch();
}
