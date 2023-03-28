  package com.rohitsood.urlybird.client.gui.widget.textfield.listener;

  import com.rohitsood.urlybird.client.gui.widget.textfield.CommonTextField;

  import java.awt.event.FocusEvent;
  import java.awt.event.FocusListener;


  /**
   * The focus listener that provides call backs into the <tt>CommonTextField</tt>.
   * Detects focus events and calls appropriate methods on the text field.
   *
   * @author Rohit Sood
   * @version 1.3
   */
  public class CommonTextFieldFocusListener implements FocusListener
  {
      /**The textfield to call when events trigger. */
      private CommonTextField textField;

      /**
       * Creates a new <tt>CommonTextFieldFocusListener</tt> object.
       * Registers the <tt>CommonTextField</tt> with this.
       * @param textField The textfield to call methods on.
       */
      public CommonTextFieldFocusListener(CommonTextField textField)
      {
          this.textField = textField;
      }

      /**
       * Called when the focus was gained on the text field.
       * Changes the state of the Text Field.
       * @param event The event that was triggered.
       */
      public void focusGained(FocusEvent event)
      {
          if (!textField.isEnabled())
          {
              return;
          }

          textField.setOnFocus();
      }

      /**
       * Called when the focus event was lost from
       * the text field.
       * Changes the state of the Text Field.
       * @param event The event that was triggered.
       */
      public void focusLost(FocusEvent event)
      {
          if (!textField.isEnabled())
          {
              return;
          }

          textField.setOffFocus();
      }
  }
