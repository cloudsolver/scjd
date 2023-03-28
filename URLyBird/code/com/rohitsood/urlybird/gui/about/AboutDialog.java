package com.rohitsood.urlybird.gui.about;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.button.CommonButton;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


/**
 * Displays information about the application, author and version.  Contains an area for displaying information and one
 * that holds an 'Ok' button.  This for informational purposes only.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public class AboutDialog extends JDialog {
    /** Text to display on the ok button. */
    private static final String OK_TEXT = "Ok";

    /** Prometric id to display. */
    private static final String PROMETRIC_ID = "Prometric ID: SP7343294";

    /** Programmer information to display. */
    private static final String PROGRAMMER_TEXT = "Programmed by : Rohit Sood";

    /** Version number to display. */
    private static final String VERSION_TEXT = "Version : 1.0";

    /** Application name to display. */
    private static final String APPLICATION_NAME = "Application: URLyBird";

    /** Information regarding the online manual. */
    private static final String MANUAL = "Help:  Please refer to the user guide shipped with this application.";

    /** The title of the preferences dialog. */
    private static final String TITLE = "About URLyBird";

    /** The column count for this dialog. */
    private static final int COLUMN_COUNT = 5;

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>AboutDialog</tt> object. Registers this with the <tt>GuiManager</tt>. Calls to initialize this
     * and makes this visible.
     *
     * @param manager The manager to register this with.
     */
    public AboutDialog(GuiManager manager) {
        super(manager.getMainWindow(), true);
        this.manager = manager;
        manager.setAboutDialog(this);
        init();
        setVisible(true);
    }

    /**
     * Initializes the dialog user interface. Sets the layout manager, initializes labels and internal panels. Sets the
     * <tt>ActionListener</tt> for the 'ok' button.
     */
    protected void init() {
        setTitle(TITLE);

        final JPanel mainPanel = new JPanel();
        final JPanel buttonPanel = new JPanel();

        final JLabel application = new JLabel(APPLICATION_NAME);
        final JLabel version = new JLabel(VERSION_TEXT);
        final JLabel programmer = new JLabel(PROGRAMMER_TEXT);
        final JLabel studentId = new JLabel(PROMETRIC_ID);
        final JLabel manual = new JLabel(MANUAL);
        mainPanel.setLayout(new SpringLayout());

        mainPanel.add(application);
        mainPanel.add(version);
        mainPanel.add(programmer);
        mainPanel.add(studentId);
        mainPanel.add(manual);

        SpringLayoutUtilities.makeSpringGrid(mainPanel, COLUMN_COUNT, 1, SpringLayoutUtilities.SPREAD,
            SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.setPreferredSize(mainPanel.getSize());

        final CommonButton closeButton = new CommonButton(OK_TEXT);

        closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    manager.closeAboutDialog();
                }
            });
        buttonPanel.add(closeButton);

        getContentPane().add(BorderLayout.CENTER, mainPanel);
        getContentPane().add(BorderLayout.PAGE_END, buttonPanel);

        final Dimension d = SpringLayoutUtilities.getPreferredDimension(this.getContentPane(),
                SpringLayoutUtilities.VERTICAL);
        setSize(d);
        pack(); //layout the components to their preferred sizes

		final Point location=manager.getMainWindow().getLocation();
		final Dimension size = manager.getMainWindow().getSize();
		final int x = location.x+ (size.width / 2) - (this.getPreferredSize().width / 2);
		final int y = location.y+ (size.height / 2) - (this.getPreferredSize().height / 2);
		setLocation(new Point(x, y));
    }
}
