package com.rohitsood.urlybird.gui.about;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenuItem;


/**
 * Menu item which triggers the display of an 'about' information dialog. Sets the mneominic and adds an
 * <tt>ActionListener</tt> listener. Users must click the about menu item to see a dialog containing information about
 * the application.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class AboutMenuItem extends JMenuItem {
    /** The title text of the menu item. */
    private static final String TITLE = "About";

    /** The mnemonic for this menu. */
    private static final char MNEMONIC = 'A';

    /** The manager to delegate to. */
    private GuiManager manager;

    /**
     * Creates a new AboutMenuItem object. Initializes it with a mnemonice and an <tt>ActionListener</tt> which
     * delegates calls to the <tt>GuiManager</tt>.
     *
     * @param manager The manager to delegate to.
     */
    public AboutMenuItem(GuiManager manager) {
        super(TITLE);
        this.manager = manager;
        init();
    }

    /**
     * Initializes the <tt>JMenuItem</tt>. Sets the mnemonic and adds an <tt>ActionListener</tt>.
     */
    private void init() {
        addActionListener(new AboutMenuItemActionListener(manager));
        setMnemonic(MNEMONIC);
    }
}
