  package com.rohitsood.urlybird.gui.search;

  import com.rohitsood.urlybird.gui.manager.GuiManager;
  import com.rohitsood.urlybird.gui.widget.button.CommonButton;

  import java.awt.Dimension;

  import javax.swing.Box;
  import javax.swing.BoxLayout;
  import javax.swing.JPanel;
  import javax.swing.border.LineBorder;


  /**
   * User interface that allows the user to search for records and book a selected records.
   * Provides two buttons that allow a user to search based on the criteria and book a selected record.
   * @author Rohit Sood
   * @version 1.1
   */
  public class SearchButtonPanel extends JPanel
  {
      /**The width for the rigid area. */
      private static final int RIGID_AREA_WIDTH = 10;

      /**Extra width for a rigid area. */
      private static final int RIGID_AREA_WIDTH_EXTRA = 20;

      /**Rigid area height. */
      private static final int RIGID_AREA_HEIGHT = 30;

      /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new <tt>SearchButtonPanel</tt> object.
       *
       * @param manager The manager to delegate to.
       */
      public SearchButtonPanel(GuiManager manager)
      {
          this.manager = manager;
          init();
      }

      /**
       * Initializes the user interface. Lays out the panel , adds buttons and listeners.
       */
      private void init()
      {
          final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
          setLayout(layout);

          final CommonButton searchButton = new CommonButton("Search");
          searchButton.addActionListener(new SearchButtonActionListener(manager));

          final CommonButton bookItButton = new CommonButton("Book It");
          bookItButton.addActionListener(new BookItButtonActionListener(manager));

          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
          add(searchButton);
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
          add(bookItButton);
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH_EXTRA, RIGID_AREA_HEIGHT)));
          setBorder(LineBorder.createBlackLineBorder());
      }
  }
