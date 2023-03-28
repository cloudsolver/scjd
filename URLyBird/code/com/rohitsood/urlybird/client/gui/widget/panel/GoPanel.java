  package com.rohitsood.urlybird.client.gui.widget.panel;

  import com.rohitsood.urlybird.client.gui.GuiManager;
  import com.rohitsood.urlybird.client.gui.widget.button.CommonButton;
  import com.rohitsood.urlybird.client.gui.widget.button.listener.GoButtonActionListener;
  import com.rohitsood.urlybird.client.gui.widget.textfield.CommonTextField;
  import com.rohitsood.urlybird.util.SpringLayoutUtilities;

  import java.awt.Dimension;

  import javax.swing.Box;
  import javax.swing.BoxLayout;
  import javax.swing.JLabel;
  import javax.swing.JPanel;
  import javax.swing.border.LineBorder;


  /**
   * User interface component that allows the user to enter a record id and hit a 'Go' button. A quick
   * search by id is made possible by this user interace.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class GoPanel extends JPanel
  {
      /** Maximum text field width in pixels. */
      private static final int FIELD_WIDTH = 100;

      /** Maximum text field height in pixels */
      private static final int FIELD_HEIGHT = 20;

      /** Rigid area wiidth constant. */
      private static final int RIGID_AREA_WIDTH = 5;

      /** Rigid area height constant. */
      private static final int RIGID_AREA_HEIGHT = 45;

      /** The label text for the go panel. */
      private static final String LABEL_TEXT = "Enter Record ID:";

      /** The manager to delegate to. */
      private GuiManager manager;

      /** The text field that holds the record id to search for. */
      private CommonTextField goTextField;

      /** The Go button which triggers the search. */
      private CommonButton goButton;

      /**
       * Creates a new GoPanel object.
       *
       * @param manager The manager to delegate to.
       */
      public GoPanel(GuiManager manager)
      {
          super();
          this.manager = manager;
          manager.setGoPanel(this);
          init();
      }

      /**
       * Initializes the panel. Instantiates text fields and the user interface layout.
       */
      private void init()
      {
          final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
          setLayout(layout);
          goTextField = new CommonTextField();
          goTextField.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
          goTextField.addActionListener(new GoButtonActionListener(this.manager));

          final CommonButton goButton = new CommonButton(" Go ");
          goButton.addActionListener(new GoButtonActionListener(this.manager));

          final Box verticalBox = Box.createVerticalBox();
          add(verticalBox);

          //create a text field and a go button
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
          add(new JLabel(LABEL_TEXT));
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
          add(goTextField);
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
          add(goButton);
          add(Box.createRigidArea(new Dimension(RIGID_AREA_WIDTH, RIGID_AREA_HEIGHT)));
          setBorder(LineBorder.createBlackLineBorder());

          setPreferredSize(SpringLayoutUtilities.getPreferredDimension(this,
                  SpringLayoutUtilities.HORIZONTAL));

          //setPreferredSize(getSize());
      }

      /**
       * Accessor for the go button instance.
       *
       * @return The instance of the go button.
       */
      public CommonButton getGoButton()
      {
          return goButton;
      }

      /**
       * Accessor for the text field instance.
       *
       * @return The text field instance
       */
      public CommonTextField getGoTextField()
      {
          return goTextField;
      }
  }
