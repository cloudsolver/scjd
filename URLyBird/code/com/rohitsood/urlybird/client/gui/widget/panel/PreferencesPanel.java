  package com.rohitsood.urlybird.client.gui.widget.panel;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.textfield.CommonTextField;
  import com.rohitsood.urlybird.server.ConfigurationProperties;
  import com.rohitsood.urlybird.util.SpringLayoutUtilities;

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
   * The panel that holds user interface components to store preferences.
   * @author Rohit Sood
   */
  public class PreferencesPanel extends JPanel
  {
      /**The length of the database field. */
      private static final int DB_LENGTH = 20;

      /**Row count for the ui. */
      private static final  int ROW = 5;

      /**Column count for the ui. */
      private static final  int COL = 2;

      /**Field for communication protocol. */
      private CommonTextField protocolField;

      /**Field for host name. */
      private CommonTextField hostField;

      /**Field for port number. */
      private CommonTextField portField;

      /**Field for server name. */
      private CommonTextField serverNameField;

      /**Field for the database file location.*/
      private CommonTextField dbLocationField;

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new PreferencesPanel object.
       *
       * @param manager The manager with which to register this.
       */
      public PreferencesPanel(GuiManager manager)
      {
          this.manager = manager;
          manager.setPreferencesPanel(this);
          init();
      }

      /**
       * Sets the configuration properties on the user interface.
       * All text fields are populated from the <tt>ConfigurationProperties</tt> element.
       * @param properties The properties from which to populate the text field.
       */
      public void setProperties(ConfigurationProperties properties)
      {
          protocolField.setText(properties.getProtocol());
          hostField.setText(properties.getHost());
          portField.setText(properties.getPort());
          serverNameField.setText(properties.getServerName());
          dbLocationField.setText(properties.getDbLocation());
      }

      /**
       * Persists the properties from the text fields to a properties file.
       * Instantiates a <tt>ConfiguarationProperties</tt> object and populates it.
       * It then invokes the persist method on the configuration properties.
       * @throws IOException If there are problems persisting to the disk.
       */
      public void persistProperties() throws IOException
      {
          final ConfigurationProperties config = new ConfigurationProperties();
          config.setHost(hostField.getText());
          config.setPort(portField.getText());
          config.setProtocol(protocolField.getText());
          config.setServerName(serverNameField.getText());
          config.setDbLocation(dbLocationField.getText());
          config.persist();
      }

      /**
       * Intitalizes the user interface of this component.
       */
      private void init()
      {
          setLayout(new SpringLayout());

          final JLabel protocolLabel = new JLabel("Communication Protocol:");
          protocolField = new CommonTextField();

          final JLabel hostLabel = new JLabel("Remote Host:");
          hostField = new CommonTextField();

          final JLabel portLabel = new JLabel("Communication Port:");
          portField = new CommonTextField();

          final JLabel lookupNameLabel = new JLabel("Registry Lookup Name:");
          serverNameField = new CommonTextField();

          final JLabel dbLocationLabel = new JLabel("Local Database Location:");
          dbLocationField = new CommonTextField(DB_LENGTH);

          dbLocationField.addFocusListener(new FocusAdapter()
              {
                  private boolean show = true;

                  public void focusGained(FocusEvent fe)
                  {
                      if (!show)
                      {
                          return;
                      }

                      final JFileChooser chooser = new JFileChooser();
                      final int        c = chooser.showOpenDialog(manager.getMainWindow());

                      if (JFileChooser.APPROVE_OPTION == c)
                      {
                          dbLocationField.setText(chooser.getSelectedFile().toString());
                      }
                  }

                  public void focusLost(FocusEvent fe)
                  {
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

          SpringLayoutUtilities.makeSpringGrid(this, ROW, COL, SpringLayoutUtilities.SPREAD,
              SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
          this.setBorder(LineBorder.createGrayLineBorder());

          setPreferredSize(new Dimension(getWidth(), getHeight()));
      }
  }
