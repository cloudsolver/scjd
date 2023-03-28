  package com.rohitsood.urlybird.gui.search;

  import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.panel.AbstractDataPanel;
import com.rohitsood.urlybird.gui.widget.panel.DataPanelOrientation;


  /**
   * Allows the user to search records and view the results.
   * In addition, allows the user to book a record from the results.
   * @author Rohit Sood
   * @version 1.1
   */
  public class SearchRecordDataPanel extends AbstractDataPanel
  {
      /**Array index of the hotel record. */
      protected static final int HOTEL_INDEX = 0;

      /**Array index of the city record. */
      protected static final int CITY_INDEX = 1;

      /**Array index of the max occupancy record. */
      protected static final int MAX_INDEX = 2;

      /**Array index of the smoking record. */
      protected static final int SMOKING_INDEX = 3;

      /**Array index of the price record. */
      protected static final int PRICE_INDEX = 4;

      /**Array index of the available date record. */
      protected static final int DATE_INDEX = 5;

      /**Array index of the customer id. */
      protected static final int CUSTOMER_INDEX = 6;

      /**Total number of textfields. */
      private static final int FIELD_COUNT = 7;

      /**Total number of rows in the grid of widgets.*/
      private static final int ROW_COUNT = 4;

      /**Total number of columns in the grid of widgets. */
      private static final int COL_COUNT = 4;

      /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new <tt>SearchRecordDataPanel</tt> object.
       * Sets the orientation and enables all fields by default.
       * @param manager The manager to delegate to.
       */
      public SearchRecordDataPanel(GuiManager manager)
      {
          this.manager = manager;
          manager.setSearchRecordDataPanel(this);
          super.setFieldCount(FIELD_COUNT);
          orientation = new DataPanelOrientation(ROW_COUNT, COL_COUNT);

          init();
          enableFields(true);
      }

      /**
       * Initializes the panel. Adds action listeners to fields to enable search when the enter key is hit.
       */
      protected void init()
      {
          super.init();

          final SearchButtonActionListener listener = new SearchButtonActionListener(manager);
          hotelTextField.addActionListener(listener);
          cityTextField.addActionListener(listener);
          maxTextField.addActionListener(listener);
          smokingTextField.addActionListener(listener);
          priceTextField.addActionListener(listener);
          dateTextField.addActionListener(listener);
          customerTextField.addActionListener(listener);
      }

      /**
       * Creates widgets of this panel.
       */
      protected void createWidgets()
      {
          labels[HOTEL_INDEX]   = hotelTextLabel;
          fields[HOTEL_INDEX]   = hotelTextField;

          labels[CITY_INDEX]   = cityTextLabel;
          fields[CITY_INDEX]   = cityTextField;

          labels[MAX_INDEX]   = maxTextLabel;
          fields[MAX_INDEX]   = maxTextField;

          labels[SMOKING_INDEX]   = smokingTextLabel;
          fields[SMOKING_INDEX]   = smokingTextField;

          labels[PRICE_INDEX]   = priceTextLabel;
          fields[PRICE_INDEX]   = priceTextField;

          labels[DATE_INDEX]   = dateTextLabel;
          fields[DATE_INDEX]   = dateTextField;

          labels[CUSTOMER_INDEX]   = customerTextLabel;
          fields[CUSTOMER_INDEX]   = customerTextField;
      }
  }
