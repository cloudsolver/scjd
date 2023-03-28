  package com.rohitsood.urlybird.gui.search;

  import com.rohitsood.urlybird.gui.manager.GuiManager;

  import java.awt.Color;

  import javax.swing.JTable;
  import javax.swing.ListSelectionModel;


  /**
   * Displays the result based on the search criteria.
   * Uses <tt>SearchResultTabelModel</tt> to display all record values in the result.
   * @author Rohit Sood
   * @version 1.1
   */
  public class SearchResultTable extends JTable
  {
      /**The manager to delegate to. */
      private GuiManager manager;

      /**
       * Creates a new <tt>SearchResultTable</tt> object.
       * Registers this with the <tt>GuiManager</tt>.
       * @param manager The manager to delegate to.
       */
      public SearchResultTable(GuiManager manager)
      {
          this.manager = manager;
          manager.setSearchResultTable(this);
          init();
      }

      /**
       * Initializes the search table. Set a table model, seletion mode and background color.
       */
      private void init()
      {
          final SearchResultTableModel model = new SearchResultTableModel(manager);
          setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          super.setModel(model);
          setSelectionBackground(Color.YELLOW);
      }
  }
