  package com.rohitsood.urlybird.client.gui.widget.panel;

  import com.rohitsood.urlybird.client.gui.GuiManager;


  /**
   * Panel which lets the agent book a room for a customer.
   * Agent must enter the number of guests, date for booking, and customer id.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class BookingDataPanel extends DataPanel
  {
      /**Total number of textfields. */
      private static final int FIELD_COUNT = 3;

      /**Total number of rows in the grid of widgets.*/
      private static final int ROW_COUNT = 3;

      /**Total number of columns in the grid of widgets. */
      private static final int COL_COUNT = 2;

      /**The manager to delegate to. */
      private GuiManager manager;

      /**The record against which the booking should be made. */
      private int recId;

      /**
       * Creates a new BookingDataPanel object.
       *
       * @param manager The manager to delegate to.
       * @param recId The record against which the booking should be made.
       */
      public BookingDataPanel(GuiManager manager, int recId)
      {
          this.manager = manager;
          manager.setBookingDataPanel(this);
          this.recId    = recId;
          orientation   = new DataPanelOrientation(ROW_COUNT, COL_COUNT);
          setFieldCount(FIELD_COUNT);

          init();
      }

      /**
       * Initializes the panel.
       */
      protected void init()
      {
          super.init();
          fields[0].setText("" + recId);
          fields[0].setEnabled(false);
      }

      /**
       * Creates gui widgets for the panel.
       */
      protected void createWidgets()
      {
          labels[0]   = recordTextLabel;
          fields[0]   = recordTextField;

          labels[1]   = maxTextLabel;
          fields[1]   = maxTextField;

          labels[2]   = customerTextLabel;
          fields[2]   = customerTextField;
      }
  }
