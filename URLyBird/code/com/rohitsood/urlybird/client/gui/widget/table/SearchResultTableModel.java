  package com.rohitsood.urlybird.client.gui.widget.table;

  import com.rohitsood.urlybird.client.gui.GuiManager;

  import java.util.ArrayList;
  import java.util.List;

  import javax.swing.table.AbstractTableModel;


  /**
   * Holds the search result data.
   * Tracks the record id, defines the column names and convenience methods.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class SearchResultTableModel extends AbstractTableModel
  {
      /** Column names of the result. */
      private static String[] colNames =
      {
          "Rec Id", "Hotel", "City", "Max Cap", "Smoking", "Price", "Date", "Cust ID"
      };

      /**The list of records returned. */
      private List dataList;

      /**
       * Creates a new SearchResultTableModel object.
       * Registers this with the <tt>GuiManager</tt>.
       * @param manager The manager to delegate to.
       */
      public SearchResultTableModel(GuiManager manager)
      {
          manager.setSearchResultTableModel(this);

          init();
      }

      /**
       * Sets the list of records that should be displayed in the tabel.
       *
       * @param dataList The list of records.
       */
      public void setDataList(List dataList)
      {
          this.dataList = dataList;
      }

      /**
       * Adds a new data record to the list of records
       *
       * @param recId The record id to which this record belongs.
       * @param data The data record.
       */
      public void addData(int recId, String[] data)
      {
          dataList.add(scrub(recId, data));
      }

      /**
       * Creates a new data record with the first object in the array as the record id.
       * Sets the record id at index zero.
       * @param recId The record id to add.
       * @param data The data record to scrub.
       *
       * @return The scrubbed data record. Contains the record id at index zero.
       */
      private static String[] scrub(int recId, String[] data)
      {
          final String[] appendData = new String[data.length + 1];
          appendData[0] = "" + recId;

          for (int a = 1;a < appendData.length;a++)
          {
              appendData[a] = data[a - 1];
          }

          return appendData;
      }

      /**
       * Initializes the table model.
       */
      private void init()
      {
          clear();
      }

      /**
       * Clears the datalist of all records.
       */
      public void clear()
      {
          dataList = new ArrayList();
      }

      /**
       * Gets the record id of the selected record by looking up the row number.
       *
       * @param rowId The row number from which to look up the record id.
       *
       * @return The record id.
       */
      public int getRecordId(int rowId)
      {
          return Integer.parseInt((String) getValueAt(rowId, 0));
      }

      /**
       * Accessor for the object at a given row and column
       * This will ignore the column index and return the id
       * as an Integer.
       * @param rowIndex The row index.
       * @param columnIndex The column index.
       *
       * @return The object at the given row and column.
       */
      public Object getValueAt(int rowIndex, int columnIndex)
      {
          String       dataItem = null;
          final String[] data = (String[]) dataList.get(rowIndex);
          dataItem = data[columnIndex];

          return dataItem;
      }

      /**
       * Returns the row count. Counts the number of items in the data list and returns the size.
       *
       * @return The count of the data records.
       */
      public int getRowCount()
      {
          return dataList.size();
      }

      /**
       * Accessor for the column name.
       * The model provides the name of the column given the column index.
       *
       * @param column The column index to query.
       *
       * @return The name of the column.
       */
      public String getColumnName(int column)
      {
          return colNames[column];
      }

      /**
       * Returns the column count.
       * Counts the number of columns in the table model and returns the size.
       *
       * @return The count of the columns.
       */
      public int getColumnCount()
      {
          return colNames.length;
      }
  }
