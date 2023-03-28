  package com.rohitsood.urlybird.client.gui.widget.iframe;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.panel.SearchButtonPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.SearchRecordDataPanel;
  import com.rohitsood.urlybird.client.gui.widget.table.SearchResultTable;
  import com.rohitsood.urlybird.util.SpringLayoutUtilities;

  import java.awt.BorderLayout;

  import javax.swing.JInternalFrame;
  import javax.swing.JScrollPane;


  /**
   * The internal frame enables the user to search for rooms and see search results.
   * In addition it also provides a button panel which launches the booking dialog.
   * It is a singleton.
   * @author Rohit Sood
   * @version 1.4
   */
  public final class SearchRecordInternalFrame extends JInternalFrame
  {
      /**The title of the internal frame. */
      private static final String TITLE = "Search Records";

      /**A reference to self. Single reference. */
      private static SearchRecordInternalFrame iframe;

      /**The manager to which calls are delegated */
      private GuiManager manager;

      /**
       * Creates a new SearchRecordInternalFrame object.
       *
       * @param manager The manager from which it will retrieve the desktop pane and add itself.
       */
      private SearchRecordInternalFrame(GuiManager manager)
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
      public static SearchRecordInternalFrame getInstance(GuiManager manager)
      {
          if ((null == iframe) || iframe.isClosed())
          {
              iframe = new SearchRecordInternalFrame(manager);
          }

          return iframe;
      }

      /**
       * Initializes the internal frame.
       */
      private void init()
      {
          getContentPane().setLayout(new BorderLayout());

          final SearchRecordDataPanel dataPanel = new SearchRecordDataPanel(manager);
          final SearchButtonPanel   buttonPanel = new SearchButtonPanel(manager);

          final SearchResultTable   table  = new SearchResultTable(manager);
          final JScrollPane         scroll = new JScrollPane(table);
          scroll.setPreferredSize(dataPanel.getPreferredSize());

          //scroll.setPreferredSize(table.getPreferredSize());
          getContentPane().add(dataPanel, BorderLayout.PAGE_START);
          getContentPane().add(scroll, BorderLayout.CENTER);
          getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

          setSize(SpringLayoutUtilities.getPreferredDimension(this.getContentPane(),
                  SpringLayoutUtilities.VERTICAL));
          pack(); //layout the components to their preferred sizes
          manager.getDesktopPane().add(this);
      }
  }
