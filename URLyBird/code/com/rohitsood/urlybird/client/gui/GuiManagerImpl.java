  package com.rohitsood.urlybird.client.gui;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.biz.validator.DataValidator;
  import com.rohitsood.urlybird.client.DatabaseClient;
  import com.rohitsood.urlybird.client.DatabaseClientFactory;
  import com.rohitsood.urlybird.client.gui.widget.ExceptionOptionPane;
  import com.rohitsood.urlybird.client.gui.widget.MainMenuBar;
  import com.rohitsood.urlybird.client.gui.widget.dialog.ConfirmBookingDialog;
  import com.rohitsood.urlybird.client.gui.widget.dialog.PreferencesDialog;
  import com.rohitsood.urlybird.client.gui.widget.iframe.ManageRecordInternalFrame;
  import com.rohitsood.urlybird.client.gui.widget.iframe.SearchRecordInternalFrame;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ExitMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ManageRecordMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.ModeMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.menu.item.SearchMenuItem;
  import com.rohitsood.urlybird.client.gui.widget.panel.BookingDataPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.GoPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordButtonPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordDataPanel;
import com.rohitsood.urlybird.client.gui.widget.panel.PreferencesPanel;
  import com.rohitsood.urlybird.client.gui.widget.panel.SearchRecordDataPanel;
  import com.rohitsood.urlybird.client.gui.widget.table.SearchResultTable;
  import com.rohitsood.urlybird.client.gui.widget.table.SearchResultTableModel;

  import suncertify.db.ValidationException;

  import java.beans.PropertyVetoException;

  import java.io.IOException;

  import javax.swing.JDesktopPane;
  import javax.swing.JOptionPane;


  /**
   *
   * @author Rohit
   */
  /**
   * <tt>AbstractGuiManager</tt> provides default implementation for the user interface behavior.
   * @see GuiManager
   * @author Rohit Sood
   * @version 1.2
   **/
  class GuiManagerImpl implements GuiManager
  {
      /**The database client where all database access calls are delegated. */
      private DatabaseClient client;

      /**The main window of the application */
      private MainWindow window;

      /**The preferences dialog for the application. */
      private PreferencesDialog preferencesDialog;

      /**The main menu bar of the application.*/
      private MainMenuBar menuBar;

      /**The menu item that opens the search internal frame.*/
      private SearchMenuItem searchMenuItem;

      /**The menu item that opens the manage record menu item */
      private ManageRecordMenuItem manageRecordMenuItem;

      /**The menu item which lets the user exit from the application.*/
      private ExitMenuItem exitMenuItem;

      /**The panel that allows the preferences to for the client and server saved. */
      private PreferencesPanel preferencesPanel;

      /**The internal frame that lets the user manage a record. */
      private ManageRecordInternalFrame manageRecordInternalFrame;

      /**The desktop pane of the <tt>MainWindow</tt>. */
      private JDesktopPane desktopPane;

      /**The data panel that holds the record information. */
      private ManageRecordDataPanel manageRecordDataPanel;

      /**The button panel that holds buttons which act on the data panel. */
      private ManageRecordButtonPanel manageRecordButtonPanel;

      /** The panel which holds the search records. */
      private SearchRecordDataPanel searchRecordDataPanel;

      /** The panel that enables the user to search and manage records based on the record id alone. */
      private GoPanel goPanel;

      /**The panel which allows the user to search records. */
      private SearchRecordInternalFrame searchRecordInternalFrame;

      /**The table model of the search results table.*/
      private SearchResultTableModel searchResultTableModel;

      /**The search result table which holds search results from the users query. */
      private SearchResultTable searchResultTable;

      /**The dialog box which handles booking of a room. */
      private ConfirmBookingDialog confirmBookingDialog;

      /**Booking data panel which holds information that enables booking of a room. */
      private BookingDataPanel bookingDataPanel;

      /**The menu item which toggles between offline and online mode. */
      private ModeMenuItem modeMenuItem;

      /**
       * Creates a new GuiManagerImpl object.
       */
      GuiManagerImpl()
      {
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#getMainWindow()
       */
      public MainWindow getMainWindow()
      {
          return window;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.Mode#getMode()
       */
      public int getMode()
      {
          return client.getMode();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setModeMenuItem
       * (com.rohitsood.urlybird.client.gui.widget.menu.item.ModeMenuItem)
       */
      public void setModeMenuItem(ModeMenuItem modeMenuItem)
      {
          this.modeMenuItem = modeMenuItem;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setGoPanel
       * (com.rohitsood.urlybird.client.gui.widget.panel.GoPanel)
       */
      public void setGoPanel(GoPanel goPanel)
      {
          this.goPanel = goPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setSearchRecordDataPanel
       * (com.rohitsood.urlybird.client.gui.widget.panel.SearchRecordDataPanel)
       */
      public void setSearchRecordDataPanel(SearchRecordDataPanel searchRecordDataPanel)
      {
          this.searchRecordDataPanel = searchRecordDataPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setPreferencesPanel
       * (com.rohitsood.urlybird.client.gui.widget.dialog.preferences.PreferencesPanel)
       */
      public void setPreferencesPanel(PreferencesPanel preferencesPanel)
      {
          this.preferencesPanel = preferencesPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setSearchMenuItem
       * (com.rohitsood.urlybird.client.gui.widget.menu.item.SearchMenuItem)
       */
      public void setSearchMenuItem(SearchMenuItem searchMenuItem)
      {
          this.searchMenuItem = searchMenuItem;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setManageRecordMenuItem
       * (com.rohitsood.urlybird.client.gui.widget.menu.item.ManageRecordMenuItem)
       */
      public void setManageRecordMenuItem(ManageRecordMenuItem manageRecordMenuItem)
      {
          this.manageRecordMenuItem = manageRecordMenuItem;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#openManageRecordInternalFrame()
       */
      public void openManageRecordInternalFrame()
      {
          ManageRecordInternalFrame iframe = ManageRecordInternalFrame.getInstance(this);

          if (iframe == manageRecordInternalFrame)
          {
              iframe = null;
              manageRecordInternalFrame.toFront();
          }
          else
          {
              manageRecordInternalFrame = iframe;
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#openSearchRecordInternalFrame()
       */
      public void openSearchRecordInternalFrame()
      {
          SearchRecordInternalFrame iframe = SearchRecordInternalFrame.getInstance(this);

          if (iframe == searchRecordInternalFrame)
          {
              iframe = null;
              searchRecordInternalFrame.toFront();
          }
          else
          {
              searchRecordInternalFrame = iframe;

              try
              {
                  searchRecordInternalFrame.setMaximum(true);
              }
              catch (PropertyVetoException e)
              {
                  handleException("Could not Maximize\n", e);
              }
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#exitApplication()
       */
      public void exitApplication()
      {
          final int option = JOptionPane.showConfirmDialog(window, "Are you sure ?",
                  "Confirm Exit", JOptionPane.YES_NO_OPTION);

          if (option == JOptionPane.YES_OPTION)
          {
              client.disconnect();
              System.exit(0);
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#preferencesCancelButtonClicked()
       */
      public void preferencesCancelButtonClicked()
      {
          preferencesDialog.setVisible(true);
          preferencesDialog.dispose();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#preferencesMenuItemClicked()
       */
      public void preferencesMenuItemClicked()
      {
          final PreferencesDialog dialog = new PreferencesDialog(this);
      }

      /**
       * Handles the request to handle an exception.
       * An exception option pane is displayed with the message contained in the exception.
       * @param e The exception to handle.
       */
      public void handleException(Exception e)
      {
          ExceptionOptionPane.showException(window, e);
      }

      /**
       * Handles the request to handle an exception.
       *@param message The detailed message to display.
       * @param e The exception to handle.
       */
      public void handleException(String message, Exception e)
      {
          ExceptionOptionPane.showException(window, message, e);
      }

      /**
       * Handles the request to handle an information dialog.
       *@param message The detailed message to display.
       */
      public void handleInformation(String message)
      {
          ExceptionOptionPane.showInformation(window, message);
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#goButtonClicked()
       */
      public void goButtonClicked()
      {
          String[] data = null;

          try
          {
              final String id = goPanel.getGoTextField().getText();
              DataValidator.validateRecordNumber(id);

              final int recId = Integer.parseInt(id);
              data = client.read(recId);
              manageRecordDataPanel.setData(recId, data);
              manageRecordButtonPanel.getEditButton().setEnabled(true);
              manageRecordButtonPanel.getDeleteButton().setEnabled(true);
              manageRecordButtonPanel.getAddButton().setEnabled(false);
              manageRecordButtonPanel.getSaveButton().setEnabled(false);
              manageRecordButtonPanel.getCancelButton().setEnabled(false);

              manageRecordDataPanel.getIDField().setEnabled(false);
          }
          catch (Exception e)
          {
              handleException(e);
          }

          return;
      }

      /**
       * DOCUMENT ME!
       */
      public void updateButtonClicked()
      {
          final String[] data = manageRecordDataPanel.getData(false); //without rec id

          try
          {
              DataValidator.validate(data);
              client.update(Integer.parseInt(manageRecordDataPanel.getData(true)[0]), data);
              manageRecordDataPanel.enableFields(false);
              manageRecordButtonPanel.getEditButton().setEnabled(true);
              manageRecordButtonPanel.getAddButton().setEnabled(false);
          }
          catch (Exception e)
          {
              handleException(e);
          }
      }

      /**
       * DOCUMENT ME!
       */
      public void cancelButtonClicked()
      {
          manageRecordDataPanel.clearFields();

          //disable all fields
          manageRecordButtonPanel.getEditButton().setEnabled(false);
          manageRecordButtonPanel.getAddButton().setEnabled(true);
          manageRecordButtonPanel.getSaveButton().setEnabled(false);
          manageRecordButtonPanel.getAddButton().setEnabled(false);
          manageRecordButtonPanel.getCancelButton().setEnabled(false);
          manageRecordButtonPanel.getDeleteButton().setEnabled(false);
          goPanel.getGoTextField().setText("");
      }

      /**
       * DOCUMENT ME!
       */
      public void editButtonClicked()
      {
          manageRecordDataPanel.enableFields(true);
          manageRecordButtonPanel.getEditButton().setEnabled(false);
          manageRecordButtonPanel.getAddButton().setEnabled(true);
          manageRecordButtonPanel.getSaveButton().setEnabled(true);
          manageRecordButtonPanel.getCancelButton().setEnabled(true);
      }

      /**
       * DOCUMENT ME!
       */
      public void deleteButtonClicked()
      {
          final int recId = Integer.parseInt(manageRecordDataPanel.getData(true)[0]);

          try
          {
              client.delete(recId);
              manageRecordDataPanel.enableFields(false);
          }
          catch (Exception e)
          {
              handleException(e);
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#addButtonClicked()
       */
      public void addButtonClicked()
      {
          final String[] data = manageRecordDataPanel.getData(false); //without rec id

          try
          {
              DataValidator.validate(data);

              final int recId = client.create(data);
              manageRecordDataPanel.enableFields(false);
              manageRecordDataPanel.setData(recId, client.read(recId));
          }
          catch (Exception e)
          {
              handleException(e);
          }

          System.out.println("Persisting...Done");
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#savePreferencesButtonClicked()
       */
      public void savePreferencesButtonClicked()
      {
          try
          {
              preferencesPanel.persistProperties();
              handleInformation(
                  "Preferences Saved.\nPlease restart the client for the settings to take effect.");
              preferencesDialog.dispose();
          }
          catch (IOException e)
          {
              handleException(e);
              e.printStackTrace();
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#searchButtonClicked()
       */
      public void searchButtonClicked()
      {
          String[] criteria = null;

          criteria = searchRecordDataPanel.getData(); //no rec id supported
          searchResultTableModel.clear();

          int[] ids = null;

          try
          {
              ids = client.find(criteria);

              for (int a = 0;a < ids.length;a++)
              {
                  searchResultTableModel.addData(ids[a], client.read(ids[a]));
              }
          }
          catch (Exception e)
          {
              searchResultTableModel.clear();
              handleException(e);
          }
          finally
          {
              searchResultTableModel.fireTableDataChanged();
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#bookButtonClicked()
       */
      public void bookButtonClicked()
      {
          try
          {
              final int rowId = searchResultTable.getSelectedRow();

              if (rowId < 0)
              {
                  throw new ValidationException("Cannot Book. Please select a record first.");
              }

              final int recId = searchResultTableModel.getRecordId(rowId);

              confirmBookingDialog = new ConfirmBookingDialog(this, recId);
          }
          catch (Exception e)
          {
              handleException(e);
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#confirmReservationButtonClicked()
       */
      public void confirmReservationButtonClicked()
      {
          boolean confirmed = false;

          try
          {
              confirmed = client.book(getBookingDataPanel().getRecordId(),
                      getBookingDataPanel().getMaxOccupancy(), getBookingDataPanel().getCustomerId());
          }
          catch (Exception e)
          {
              confirmed = false;
              handleException(e);
          }

          if (confirmed)
          {
              ExceptionOptionPane.showInformation(getMainWindow(), "Booking Confirmed.");
          }
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#confirmCloseReservationButtonClicked()
       */
      public void confirmCloseReservationButtonClicked()
      {
          confirmBookingDialog.setVisible(false);
          confirmBookingDialog.dispose();
          searchButtonClicked();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#workOnline()
       */
      public void workOnline()
      {
          client = new DatabaseClientFactory().getDatabaseClient(Mode.ONLINE);
		if (null!=getMainWindow() && null!=modeMenuItem){
			getMainWindow().setTitle(true);
			modeMenuItem.setOnline(true);
		}
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#workOffline()
       */
      public void workOffline()
      {
          client = new DatabaseClientFactory().getDatabaseClient(Mode.OFFLINE);
		  if (null!=getMainWindow() && null!= modeMenuItem){
		  	getMainWindow().setTitle(false);
		  	modeMenuItem.setOnline(false);
		  }
      }

      /**
       * Gets a database client.
       * The client can be either a network client or a local client depending on the mode of the application.
       * @return The database client.
       */
      public DatabaseClient getClient()
      {
          return client;
      }

      /**
       * Sets the database client.
       * The client can be either a network client or a local client depending on the mode of the application.
       * @param client The database client to set.
       */
      public void setClient(DatabaseClient client)
      {
          this.client = client;
      }

      /**
       * Getter for the booking data panel.
       * @return The booking data panel.
       */
      public BookingDataPanel getBookingDataPanel()
      {
          return bookingDataPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setBookingDataPanel
       * (com.rohitsood.urlybird.client.gui.widget.panel.BookingDataPanel)
       */
      public void setBookingDataPanel(BookingDataPanel bookingDataPanel)
      {
          this.bookingDataPanel = bookingDataPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setConfirmBookingDialog
       * (com.rohitsood.urlybird.client.gui.widget.dialog.ConfirmBookingDialog)
       */
      public void setConfirmBookingDialog(ConfirmBookingDialog confirmBookingDialog)
      {
          this.confirmBookingDialog = confirmBookingDialog;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setManageRecordButtonPanel
       * (com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordButtonPanel)
       */
      public void setManageRecordButtonPanel(ManageRecordButtonPanel manageRecordButtonPanel)
      {
          this.manageRecordButtonPanel = manageRecordButtonPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#getDesktopPane()
       */
      public JDesktopPane getDesktopPane()
      {
          return desktopPane;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setDesktopPane(javax.swing.JDesktopPane)
       */
      public void setDesktopPane(JDesktopPane desktopPane)
      {
          this.desktopPane = desktopPane;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setExitMenuItem
       * (com.rohitsood.urlybird.client.gui.widget.menu.item.ExitMenuItem)
       */
      public void setExitMenuItem(ExitMenuItem exitMenuItem)
      {
          this.exitMenuItem = exitMenuItem;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setMainWindow(com.rohitsood.urlybird.client.gui.MainWindow)
       */
      public void setMainWindow(MainWindow window)
      {
          this.window = window;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setManageRecordDataPanel
       * (com.rohitsood.urlybird.client.gui.widget.panel.ManageRecordDataPanel)
       */
      public void setManageRecordDataPanel(ManageRecordDataPanel manageRecordDataPanel)
      {
          this.manageRecordDataPanel = manageRecordDataPanel;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setPreferencesDialog
       * (com.rohitsood.urlybird.client.gui.widget.dialog.PreferencesDialog)
       */
      public void setPreferencesDialog(PreferencesDialog preferencesDialog)
      {
          this.preferencesDialog = preferencesDialog;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setSearchResultTable
       * (com.rohitsood.urlybird.client.gui.widget.table.SearchResultTable)
       */
      public void setSearchResultTable(SearchResultTable searchResultTable)
      {
          this.searchResultTable = searchResultTable;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.gui.GuiManager#setSearchResultTableModel
       * (com.rohitsood.urlybird.client.gui.widget.table.SearchResultTableModel)
       */
      public void setSearchResultTableModel(SearchResultTableModel searchResultTableModel)
      {
          this.searchResultTableModel = searchResultTableModel;
      }
  }
