package com.rohitsood.urlybird.gui.preferences;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.textfield.CommonTextField;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import suncertify.db.DataRow;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;


/**
 * The panel that holds user interface components to store preferences. All configuration for the system is done from
 * this panel. It delegates persistence of the attributes to <tt>ConfigurationProperties</tt>.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class PreferencesPanel extends JPanel {
    /** The length of the database field. */
    private static final int DB_LENGTH = 20;

    /** Row count for the ui. */
    private static final int ROW = 6;

    /** Column count for the ui. */
    private static final int COL = 2;

    /** Field for communication protocol. */
    private CommonTextField protocolField;

    /** Field for host name. */
    private CommonTextField hostField;

    /** Field for port number. */
    private CommonTextField portField;

    /** Field for server name. */
    private CommonTextField serverNameField;

    /** Field for the database file location. */
    private CommonTextField dbLocationField;

    /** Field for the magic cookie value. */
    private CommonTextField magicCookieField;

    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new PreferencesPanel object.
     *
     * @param manager The manager to delegate calls to.
     */
    public PreferencesPanel(GuiManager manager) {
        this.manager = manager;
        init();
    }



    /**
     * Sets the configuration properties on the user interface. All text fields are populated from the
     * <tt>ConfigurationProperties</tt> element.
     *
     * @param properties The properties from which to populate the text field.
     */
    public void setProperties(ConfigurationProperties properties) {
        protocolField.setText(properties.getProtocol());
        hostField.setText(properties.getHost());
        portField.setText(properties.getPort());
        serverNameField.setText(properties.getServerName());
        dbLocationField.setText(properties.getDbLocation());
        magicCookieField.setText(properties.getMagicCookie());
    }

    /**
     * Persists the properties from the text fields to a properties file. Instantiates a
     * <tt>ConfiguarationProperties</tt> object and populates it. It then invokes the persist method on the
     * configuration properties.
     *
     * @throws IOException If there are problems persisting to the disk.
     */
    public void persistProperties() throws IOException {
        final ConfigurationProperties config = new ConfigurationProperties();
        config.setProtocol(protocolField.getText());
        config.setHost(hostField.getText());
        config.setPort(portField.getText());
        config.setServerName(serverNameField.getText());
        config.setDbLocation(dbLocationField.getText());
        config.setMagicCookie(magicCookieField.getText());
        config.persist();
    }

    /**
     * Intitalizes the user interface of this component. Sets the labels and text fields for all attributes.
     */
    private void init() {
        setLayout(new SpringLayout());

        final JLabel protocolLabel = new JLabel("Communication Protocol:");
        protocolField = new CommonTextField();
        protocolField.setText("rmi");

        final JLabel hostLabel = new JLabel("Remote Host:");
        hostField = new CommonTextField();

        final JLabel portLabel = new JLabel("Communication Port:");
        portField = new CommonTextField();

        final JLabel lookupNameLabel = new JLabel("Registry Lookup Name:");
        serverNameField = new CommonTextField();

        final JLabel dbLocationLabel = new JLabel("Local Database Location:");
        dbLocationField = new CommonTextField(DB_LENGTH);

        final JLabel magicCookieLabel = new JLabel("Magic cookie value:");
        magicCookieField = new CommonTextField();
        magicCookieField.setText("" + DataRow.MAGIC_COOKIE_VALUE);

        dbLocationField.addFocusListener(new FocusAdapter() {
                private boolean show = true;

                public void focusGained(FocusEvent fe) {
                    if (!show) {
                        return;
                    }

                    final JFileChooser chooser = new JFileChooser();

                    final int c = chooser.showOpenDialog(manager.getMainWindow());

                    if (JFileChooser.APPROVE_OPTION == c) {
                        dbLocationField.setText(chooser.getSelectedFile().toString());
                    }
                }

                public void focusLost(FocusEvent fe) {
                    show = !show;
                }
            });

        this.add(protocolLabel);
        this.add(protocolField);

        this.add(hostLabel);
        this.add(hostField);

        this.add(portLabel);
        this.add(portField);

        this.add(lookupNameLabel);
        this.add(serverNameField);

        this.add(dbLocationLabel);
        this.add(dbLocationField);

        this.add(magicCookieLabel);
        this.add(magicCookieField);

        SpringLayoutUtilities.makeSpringGrid(this, ROW, COL, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
        this.setBorder(LineBorder.createGrayLineBorder());

        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }
}
