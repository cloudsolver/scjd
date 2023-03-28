package com.rohitsood.urlybird.gui.manager;

import com.rohitsood.urlybird.mode.Mode;


/**
 * Factory for getting a concrete <tt>GuiManager</tt> implementation instance. The <tt>Launcher</tt> type invokes the
 * factory to get the appropriate instance.
 *
 * @author Rohit Sood
 * @version 1.2
 */
public class GuiManagerFactory {
    /**
     * Default no-args constructor creates a new GuiManagerFactory object.
     */
    public GuiManagerFactory() {
    }

    /**
     * Provides a concrete implementation instance of <tt>GuiManager</tt>.
     * Based on the mode, a concrete instance of <tt>GuiManager</tt> is created with the appropriate mode setting.
     * That instance is returned, if no mode is found then an <tt>IllegalArgumentException</tt> is thrown.
     *
     * @param mode The mode to use.
     *
     * @return A concrete implementation of <tt>GuiManager</tt>
     */
    public GuiManager getInstance(int mode) {
        GuiManager manager = null;

        switch (mode) {
        case Mode.OFFLINE:
            manager = new GuiManagerImpl(Mode.OFFLINE);

            break;

        case Mode.ONLINE:
            manager = new GuiManagerImpl(Mode.ONLINE);

            break;

        case Mode.SERVER:
            manager = new GuiManagerImpl(Mode.SERVER);

            break;

        default:
            throw new IllegalArgumentException("Unknown mode: " + mode);
        }

        return manager;
    }
}
