  package com.rohitsood.urlybird.gui.widget.panel;


  /**
   * Defines the orientation for panels. This is simply a holder of row count and column count.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class DataPanelOrientation
  {
      /**The row count. */
      private int row;

      /**The column count */
      private int col;

      /**
       * Creates a new <tt>DataPanelOrientation</tt> object.
       *
       * @param row The row count.
       * @param col The column count.
       */
      public DataPanelOrientation(int row, int col)
      {
          this.row   = row;
          this.col   = col;
      }

      /**
       * Accessor for the row count.
       *
       * @return int - The row count.
       */
      public int getRowCount()
      {
          return row;
      }

      /**
       * Accessor for the column count.
       *
       * @return int - The column count.
       */
      public int getColumnCount()
      {
          return col;
      }
  }
