  package com.rohitsood.urlybird.client.gui.widget.dialog;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.button.CommonButton;
  import com.rohitsood.urlybird.client.gui.widget.button.listener.PreferencesCancelButtonActionListener;
  import com.rohitsood.urlybird.client.gui.widget.button.listener.SavePreferencesButtonActionListener;
import com.rohitsood.urlybird.client.gui.widget.panel.PreferencesPanel;
  import com.rohitsood.urlybird.server.ConfigurationProperties;
  import com.rohitsood.urlybird.util.SpringLayoutUtilities;

  import java.awt.BorderLayout;
  import java.awt.Dimension;

  import javax.swing.JDialog;
  import javax.swing.JPanel;
  import javax.swing.SpringLayout;


  /**
   * User interface for the preferences. This a <tt>JDialog</tt> which holds the <tt>PreferencesPanel</tt>.
   * Settings for both alone, default and server mode can are made from this dialog.
   * @author Rohit Sood
   * @version 1.3
   */
  public class PreferencesDialog extends JDialog
  {
      /**The title of the preferences dialog. */
      private static final String TITLE = "Client Preferences";

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new PreferencesDialog object.
       *
       * @param manager The manager to register this with.
       */
      public PreferencesDialog(GuiManager manager)
      {
          super(manager.getMainWindow(), true);
          this.manager = manager;
          manager.setPreferencesDialog(this);

          init();

          setVisible(true);
      }

      /**
       * Initializes the dialog user interface.
       */
      private void init()
      {
          setTitle(TITLE);

          final PreferencesPanel      preferencesPanel = new PreferencesPanel(manager);
          final ConfigurationProperties config = new ConfigurationProperties();
          preferencesPanel.setProperties(config);

          //create the button panel
          final JPanel buttonPanel = new JPanel();
          buttonPanel.setLayout(new SpringLayout());

          final CommonButton preferencesCancelButton = new CommonButton(CommonButton.CANCEL);
          preferencesCancelButton.addActionListener(new PreferencesCancelButtonActionListener(
                  manager));

          final CommonButton saveButton = new CommonButton(CommonButton.SAVE);
          saveButton.addActionListener(new SavePreferencesButtonActionListener(manager));
          buttonPanel.add(saveButton);
          buttonPanel.add(preferencesCancelButton);
          SpringLayoutUtilities.makeSpringGrid(buttonPanel, 1, 2, SpringLayoutUtilities.SPREAD,
              SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
          buttonPanel.setPreferredSize(buttonPanel.getSize());

          getContentPane().add(BorderLayout.CENTER, preferencesPanel);
          getContentPane().add(BorderLayout.PAGE_END, buttonPanel);

          final Dimension d = SpringLayoutUtilities.getPreferredDimension(this.getContentPane(),
                  SpringLayoutUtilities.VERTICAL);
          setSize(d);
          pack(); //layout the components to their preferred sizes

          final int h = (manager.getMainWindow().getSize().height / 2) - (d.height / 2);
          final int w = (manager.getMainWindow().getSize().width / 2) - (d.width / 2);
          setLocation(w, h);
      }
  }
