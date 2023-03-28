  package com.rohitsood.urlybird.client.gui.widget.button;

  import java.awt.event.MouseAdapter;
  import java.awt.event.MouseEvent;


  /**
   * Listener for the <tt>CommonButton</tt>.
   * Provides mouse roll-over effects by changing the state of the button on mouse events.
   *
   * @author Rohit Sood
   * @version 1.1
   * */
  public class CommonButtonMouseListener extends MouseAdapter
  {
      /**The button whose state will be changed. */
      private CommonButton button;

      /**
       * Creates a new CommonButtonMouseListener object.
       *
       * @param button The button on which this listener will act to change state.
       */
      public CommonButtonMouseListener(CommonButton button)
      {
          this.button = button;
      }

      /**
       * Called when the mouse hovers on top of the button.
       * Changes the state of the button.
       * @param event The event that fires.
       */
      public void mouseEntered(MouseEvent event)
      {
          if (button.isEnabled())
          {
              button.setState(CommonButton.MOUSE_OVER);
          }
      }

      /**
       * Called when the mouse moves away from the button.
       * Changes the state of the button.
       * @param event The event that fires.
       */
      public void mouseExited(MouseEvent event)
      {
          button.setState(CommonButton.DEFAULT_STATE);
      }

      /**
       * Called when the mouse is pressed on the button.
       * Changes the state of the button.
       * @param event The event that fires.
       */
      public void mousePressed(MouseEvent event)
      {
          if (button.isEnabled())
          {
              button.setState(CommonButton.MOUSE_PRESSED);
          }
      }

      /**
       * Called when the mouse is released after a press on the button.
       * Changes the state of the button.
       * @param event The event that fires.
       */
      public void mouseReleased(MouseEvent event)
      {
          button.setState(CommonButton.DEFAULT_STATE);
      }
  }
