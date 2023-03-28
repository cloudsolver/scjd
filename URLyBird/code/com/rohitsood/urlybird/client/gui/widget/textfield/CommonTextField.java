  package com.rohitsood.urlybird.client.gui.widget.textfield;

  import com.rohitsood.urlybird.client.gui.widget.textfield.listener.CommonTextFieldFocusListener;

  import java.awt.Color;
  import java.awt.Dimension;

  import javax.swing.JTextField;


  /**
   * A custom <tt>JTextField</tt> with embellishments to be used through out the user interface. The
   * common text field has the following behavior: On focus the field will become yellow if the field is
   * not disabled When focus is lost and the field is not disabled then the color is defaulted
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class CommonTextField extends JTextField
  {
      /** The default color for the text field. */
      public static final Color DEFAULT_COLOR = Color.WHITE;

      /** The disabled color for the text field. */
      public static final Color DISABLED_COLOR = Color.GRAY;

      /** The selected color for the text field. */
      public static final Color SELECTED_COLOR = Color.YELLOW;

      /**DOCUMENT ME! */
      private static final int MINIMUM_HEIGHT = 20;

      /**DOCUMENT ME! */
      private static final int MINIMUM_WIDTH = 120;

      /**
       * Creates a new CommonTextField object.
       *
       * @param len The length of the field.
       */
      public CommonTextField(int len)
      {
          super(len);
          init();
      }

      /**
       * Creates a new CommonTextField object.
       */
      public CommonTextField()
      {
          super();
          init();
      }

      /**
       * Sets the color to the selected color.
       */
      public void setOnFocus()
      {
          if (isEnabled())
          {
              setBackground(SELECTED_COLOR);
          }
      }

      /**
       * Sets the color to the defaullt color.
       */
      public void setOffFocus()
      {
          if (isEnabled())
          {
              setBackground(DEFAULT_COLOR);
          }
      }

      /**
       * Initializes the text field. Sets the default color and enables the field. Adds a focus listener.
       */
      private void init()
      {
          setDisabledTextColor(DISABLED_COLOR);
          setEnabled(true);
          addFocusListener(new CommonTextFieldFocusListener(this));
          setPreferredSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
      }
  }
