package com.rohitsood.urlybird.gui;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.mode.Mode;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;


/**
 * Abstract class definition for the main window. All frames that are required for this application should extend from
 * this class. Provides titles, default listeners for the frame.
 *
 * @author Rohit Sood
 * @version 1.2
 */
public abstract class AbstractMainWindow extends JFrame {
    /** Title for default mode. Used when mode is <tt>Mode.ONLINE</tt>. */
    public static final String ONLINE_TITLE = "URLyBird Application (Online)";

    /** Title for alone mode.Used when mode is <tt>Mode.OFFLINE</tt>. */
    public static final String OFFLINE_TITLE = "URLyBird Application (Offline)";

    /** Title for server mode.Used when mode is <tt>Mode.SERVER</tt>. */
    public static final String SERVER_TITLE = "URLyBird Application (Server)";

    /** Constant for default screen width. */
    private static final int SCREEN_WIDTH = 640;

    /** Constant for default screen height. */
    private static final int SCREEN_HEIGHT = 480;

    /** The desktop pane of this window. */
    private JDesktopPane desktop;

    /** The manager to register subclasses with. */
    protected GuiManager manager;

    /** Debug flag indicating if debug messages are to be shown. */
    private boolean debug = false;

    /**
     * Initialize the main window. Sub-classes are expected to have their own initializations sequence for windows.
     */
    protected abstract void init();

    /**
     * Initializes the desktop pane. Adds a <tt>JDesktopPane</tt>.
     */
    protected void initWindow() {
        desktop = new JDesktopPane();
        manager.setDesktopPane(desktop);
        getContentPane().add(desktop);
    }

    /**
     * Convenience debug method for maintenance developers. Based on the <tt>boolean</tt> debug flag the message is
     * printed on the console. If the debug flag is set to <tt>false</tt> then nothing is printed.
     *
     * @param msg The message to print to console.
     */
    protected void debug(String msg) {
        if (!debug) {
            return;
        }

        System.out.print(msg);
    }

    /**
     * Initializes the size of the window. Initialized the window to maximum size of the screen and makes it visible.
     */
    protected void initSizes() {
        super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setVisible(true);
    }

    /**
     * Initializes and adds a <tt>WindowListner</tt>. Hitting the close button will automatically close the
     * application. If the server gui is closed it will not close the server.
     */
    protected void initListener() {
        super.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
    }

    /**
     * Initializes the mode title. Based on the mode the title is changed.
     */
    protected void initMode() {
        if (manager.getMode() == Mode.ONLINE) {
            setTitle(ONLINE_TITLE);
        } else if (manager.getMode() == Mode.OFFLINE) {
            setTitle(OFFLINE_TITLE);
        } else if (manager.getMode() == Mode.SERVER) {
            setTitle(SERVER_TITLE);
        }
    }
}
