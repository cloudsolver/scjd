package com.rohitsood.urlybird.gui.widget;

import com.rohitsood.urlybird.gui.help.HelpMenu;
import com.rohitsood.urlybird.gui.record.RecordMenu;
import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenuBar;


/**
 * The main menu that appears on the main window. This displays all the options
 * available to interact with the system. Consists of a menus and corresponding
 * menu items.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class MainMenuBar extends JMenuBar {
    /** The manager to which calls are delegated. */
    protected GuiManager manager;

    /**
     * Creates a new <tt>MainMenuBar</tt> object and registers it with a
     * <tt>GuiManager</tt>.
     *
     * @param manager The <tt>GuiManager</tt> to register this with.
     */
    public MainMenuBar(GuiManager manager) {
        super();
        this.manager = manager;
        init();
    }

    /**
     * Initializes the main menu bar. Adds a <tt>FileMenu</tt> and a
     * <tt>HelpMenu</tt>. This may be overriden by sub-classes.
     */
    protected void init() {
        addMenus(manager);
    }

    /**
     * Delegates calls to add menus to the menu bar.
     * Calls addRecordMenu.
     * @param manager The manager to register the menus with.
     */
    protected void addMenus(GuiManager manager) {
        addRecordMenu(manager);
        addHelpMenu(manager);
    }

    /**
     * Adds help menu.
     *
     * @param manager The manager to register the menus with.
     */
    private void addHelpMenu(GuiManager manager) {
        final HelpMenu help = new HelpMenu(manager);
        add(help);
    }

    /**
     * Adds record menu.
     *
     * @param manager The manager to register the menus with.
     */
    private void addRecordMenu(GuiManager manager) {
        final RecordMenu record = new RecordMenu(manager);
        add(record);
    }
}
