  package com.rohitsood.urlybird.client.gui.widget.iframe;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.panel.GoPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordButtonPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordDataPanel;
  import com.rohitsood.urlybird.util.SpringLayoutUtilities;

  import java.awt.BorderLayout;

  import javax.swing.JInternalFrame;


  /**
   * The internal frame enables the administrator to search for entire records based on record.
   * In addition it also provides a button panel which allows adding,editing, deleting records.
   * It is a singleton.
   * @author Rohit Sood
   * @version 1.4
   */
  public final class ManageRecordInternalFrame extends JInternalFrame
  {
      /**The title of the internal frame */
      private static final String TITLE = "Manage Record";

      /**A reference to self. Single reference. */
      private static ManageRecordInternalFrame iframe;

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new ManageRecordInternalFrame object.
       *
       * @param manager The manager from which it will retrieve the desktop pane and add itself.
       */
      private ManageRecordInternalFrame(GuiManager manager)
      {
          super(TITLE, true, true, true, true);
          this.manager = manager;
          init();
          setVisible(true);
      }

      /**
       * Gets the instance of this internal frame. This is the singleton implementation method.
       *
       * @param manager The manager from which it will retrieve the desktop pane and add itself.
       *
       * @return An instance of itself.
       */
      public static ManageRecordInternalFrame getInstance(GuiManager manager)
      {
          if ((null == iframe) || iframe.isClosed())
          {
              iframe = new ManageRecordInternalFrame(manager);
          }

          return iframe;
      }

      /**
       * Initializes the internal frame.
       */
      private void init()
      {
          getContentPane().setLayout(new BorderLayout());

          final GoPanel               goPanel     = new GoPanel(this.manager);
          final ManageRecordDataPanel dataPanel   = new ManageRecordDataPanel(manager);
          final ManageRecordButtonPanel buttonPanel = new ManageRecordButtonPanel(this.manager);

          getContentPane().add(goPanel, BorderLayout.PAGE_START);
          getContentPane().add(dataPanel, BorderLayout.CENTER);
          getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

          setSize(SpringLayoutUtilities.getPreferredDimension(this.getContentPane(),
                  SpringLayoutUtilities.VERTICAL));
          pack(); //layout the components to their preferred sizes
          manager.getDesktopPane().add(this);
      }
  }
