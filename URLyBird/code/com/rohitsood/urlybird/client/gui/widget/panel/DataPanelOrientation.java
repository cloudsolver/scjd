  package com.rohitsood.urlybird.client.gui.widget.panel;


  /**
   * Defines the orientation for panels. This is simply a holder of row count and column count.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  class DataPanelOrientation
  {
      /**The row count. */
      private int row;

      /**The column count */
      private int col;

      /**
       * Creates a new DataPanelOrientation object.
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
