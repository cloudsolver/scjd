package com.rohitsood.urlybird.gui;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.MainMenuBar;


/**
 * The main window of the gui in <tt>Mode.OFFLINE</tt> and <tt>Mode.ONLINE</tt> modes.  This window is a JFrame which
 * can hold internal frames. The window starts maximized to current screen size, default size is set to 640 by 480
 * pixels.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class ClientMainWindow extends AbstractMainWindow {
    /**
     * Constructs a <tt>MainWindow</tt> object. Initializes the window with a default size, then maximizes it.
     *
     * @param manager The <tt>GuiManager</tt> to register with.
     */
    public ClientMainWindow(GuiManager manager) {
        this.manager = manager;
        manager.setMainWindow(this);
        init();
    }

    /**
     * Adds the menu bar on the window. Creates a new instance of <tt>MainMenuBar</tt> and sets it.
     *
     * @param manager The manager to use.
     */
    protected void initMainMenuBar(GuiManager manager) {
        super.setJMenuBar(new MainMenuBar(manager));
    }

    /**
     * Initializes the frame. Adds a desktop pane, menu bar, window listener, and sets the title.
     */
    protected void init() {
        initWindow();
        initMainMenuBar(manager);
        initSizes();
        initListener();
        initMode();
    }
}
