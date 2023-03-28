package com.rohitsood.urlybird.gui.preferences;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.button.CommonButton;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


/**
 * User interface for the preferences. This a <tt>JDialog</tt> which holds the <tt>PreferencesPanel</tt>. Settings for
 * both alone, default and server mode can are made from this dialog.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public class PreferencesDialog extends JDialog {
    /** The title of the preferences dialog. */
    private static final String TITLE = "Preferences";

    /** Text for cancel. */
    public static final String CANCEL_TEXT = "Cancel";

    /** Text for save. */
    public static final String SAVE_TEXT = "Save";

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /** The preferences panel */
    private PreferencesPanel preferencesPanel;

    /**
     * Creates a new PreferencesDialog object.
     *
     * @param manager The manager to register this with.
     */
    public PreferencesDialog(GuiManager manager) {
        super(manager.getMainWindow(), true);
        this.manager = manager;
        manager.setPreferencesDialog(this);
        init();
        setVisible(true);
    }

    /**
     * Delegates call to the <tt>PreferencesPanel</tt> to persist the preferences to disk.
     *
     * @throws IOException If there is a problem in persisting the properties.
     */
    public void persistProperties() throws IOException {
        preferencesPanel.persistProperties();
    }

    /**
     * Initializes the dialog user interface.
     */
    private void init() {
        setTitle(TITLE);

        preferencesPanel = new PreferencesPanel(manager);

        final ConfigurationProperties config = new ConfigurationProperties();
        preferencesPanel.setProperties(config);

        //create the button panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new SpringLayout());

        final CommonButton preferencesCancelButton = new CommonButton(CANCEL_TEXT);
        preferencesCancelButton.addActionListener(new PreferencesCancelButtonActionListener(manager));

        final CommonButton saveButton = new CommonButton(SAVE_TEXT);
        saveButton.addActionListener(new SavePreferencesButtonActionListener(manager));
        buttonPanel.add(saveButton);
        buttonPanel.add(preferencesCancelButton);
        SpringLayoutUtilities.makeSpringGrid(buttonPanel, 1, 2, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
        buttonPanel.setPreferredSize(buttonPanel.getSize());

        getContentPane().add(BorderLayout.CENTER, preferencesPanel);
        getContentPane().add(BorderLayout.PAGE_END, buttonPanel);

        final Dimension d = SpringLayoutUtilities.getPreferredDimension(this.getContentPane(), SpringLayoutUtilities.VERTICAL);
        setSize(d);
        pack(); //layout the components to their preferred sizes
		final Point location=manager.getMainWindow().getLocation();
		final Dimension size = manager.getMainWindow().getSize();
		final int x = location.x+ (size.width / 2) - (this.getPreferredSize().width / 2);
		final int y = location.y+ (size.height / 2) - (this.getPreferredSize().height / 2);
		setLocation(new Point(x, y));
    }
}
