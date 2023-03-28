  package com.rohitsood.urlybird.client.gui;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.gui.widget.dialog.ConfirmBookingDialog;
  import com.rohitsood.urlybird.client.gui.widget.dialog.PreferencesDialog;
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

  import javax.swing.JDesktopPane;


  /**
   *  Mediator for all UI components in the system.
   * All components are registered with the manager, when events are fired, the call is delegated to the manager.
   * The manager decouples individual widgets and centralizes all controls and changes to the UI.
   * The manager is also responsible for selecting the mode of the client. This can be switched dynamically.
   * @author Rohit Sood
   */
  public interface GuiManager extends Mode
  {
      /**
       * Sets the panel that handles preferences for the application.
       * This is registered with the manager for later call delegation.
       * @param preferencesPanel The user interface component to register with the manager.
       */
      void setPreferencesPanel(PreferencesPanel preferencesPanel);

      /**
       * Returns the main window of the application.
       *
       * @return The window of the main application.
       */
      MainWindow getMainWindow();

      /**
       * Sets the panel that handles record management.
       * This is registered with the manager for later call delegation.
       * @param manageRecordDataPanel The user interface component to register with the manager.
       */
      void setManageRecordDataPanel(ManageRecordDataPanel manageRecordDataPanel);

      /**
       * Sets the panel that handles search requests and results.
       * This is registered with the manager for later call delegation.
       * @param searchRecordDataPanel The user interface component to register with the manager.
       */
      void setSearchRecordDataPanel(SearchRecordDataPanel searchRecordDataPanel);

      /**
       * Sets the menu item which enables the user to open the search screen.
       * This is registered with the manager for later call delegation.
       * @param searchMenuItem The user interface component to register with the manager.
       */
      void setSearchMenuItem(SearchMenuItem searchMenuItem);

      /**
       * Sets the menu item which triggers the manage record user interface to open.
       * This is registered with the manager for later call delegation.
       * @param manageRecordMenuItem The user interface component to register with the manager.
       */
      void setManageRecordMenuItem(ManageRecordMenuItem manageRecordMenuItem);

      /**
       * Sets the desktop pane which holds the internal frames.
       * This is registered with the manager for later call delegation.
       * @param desktop The user interface component to register with the manager.
       */
      void setDesktopPane(JDesktopPane desktop);

      /**
       * Sets the exit menu item which lets the user exit from the client.
       * This is registered with the manager for later call delegation.
       * @param exitMenuItem The user interface component to register with the manager.
       */
      void setExitMenuItem(ExitMenuItem exitMenuItem);

      /**
       * Sets the dialog that works on the with the <tt>ConfigurationProperties</tt> object.
       * This is registered with the manager for later call delegation.
       * @param preferencesDialog The user interface component to register with the manager.
       */
      void setPreferencesDialog(PreferencesDialog preferencesDialog);

      /**
       * Sets the main window which is the main <tt>JFrame</tt> that holds all UI components.
       * This is registered with the manager for later call delegation.
       * @param window The user interface component to register with the manager.
       */
      void setMainWindow(MainWindow window);

      /**
       * Sets the table which holds search results.
       * This is registered with the manager for later call delegation.
       * @param searchResultTable The user interface component to register with the manager.
       */
      void setSearchResultTable(SearchResultTable searchResultTable);

      /**
       * Gets the desktop pane that holds all the <tt>JInternalFrame</tt> objects.
       *
       * @return The desktop pane associated with the <tt>MainWindow</tt>.
       */
      JDesktopPane getDesktopPane();

      /**
       * Handle the request for opening the <tt>ManageRecordInternalFrame</tt>.
       */
      void openManageRecordInternalFrame();

      /**
       * Handles the request to exit the application.
       */
      void exitApplication();

      /**
       * Handles the request to cancel the preferences dialog.
       */
      void preferencesCancelButtonClicked();

      /**
       * Handles the request to take action when the preferences menu item is selected.
       */
      void preferencesMenuItemClicked();

      /**
       * Handles the request to take action when the go button is clicked.
       */
      void goButtonClicked();

      /**
       * Set the panel which allows the user to bring up a record for its management.
       * This is registered with the manager for later call delegation.
       * @param goPanel The user interface component to register with the manager.
       */
      void setGoPanel(GoPanel goPanel);

      /**
       * Handles the request to open a search record internal frame.
       */
      void openSearchRecordInternalFrame();

      /**
       * Handles the request to take action when the add button is clicked on the manage record panel.
       */
      void addButtonClicked();

      /**
       * Handles the request to take action when the update button is clicked on the manage record panel.
       */
      void updateButtonClicked();

      /**
       * Handles the request to take action when the cancel  button is clicked on the manage record panel,
       */
      void cancelButtonClicked();

      /**
       * Handles the request to take action when the delete button is clicked on the manage record panel,
       */
      void deleteButtonClicked();

      /**
       * Handles the request to take action when the edit button is clicked on the manage record panel,
       */
      void editButtonClicked();

      /**
       * Sets the button panel which interact with the data in the manage record data panel.
       * This is registered with the manager for later call delegation.
       * @param buttonPanel The user interface component to register with the manager.
       */
      void setManageRecordButtonPanel(ManageRecordButtonPanel buttonPanel);

      /**
       * Handles the request to take action when the preferences save button is clicked.
       */
      void savePreferencesButtonClicked();

      /**
       * Sets the model of the <tt>SearchResultTable</tt>.
       * This is registered with the manager for later call delegation.
       * @param searchTableModel The user interface component to register with the manager.
       */
      void setSearchResultTableModel(SearchResultTableModel searchTableModel);

      /**
       * Handles the request to take action when the search button is clicked on the search record panel.
       */
      void searchButtonClicked();

      /**
       * Handles the request to take action when the book button is clicked on the search record panel.
       */
      void bookButtonClicked();

      /**
       * Sets the confirmation booking dialog which holds the booking data panel.
       * This is registered with the manager for later call delegation.
       * @param confirmBookingDialog The user interface component to register with the manager.
       */
      void setConfirmBookingDialog(ConfirmBookingDialog confirmBookingDialog);

      /**
       * Handles the request to take action when the confirm reservation button is clicked.
       */
      void confirmReservationButtonClicked();

      /**
       * Handles the request to take action when the confirm close button is clicked .
       */
      void confirmCloseReservationButtonClicked();

      /**
       * Sets the booking data panel which allows the booking to be done.
       * This is registered with the manager for later call delegation.
       * @param bookingDataPanel The user interface component to register with the manager.
       */
      void setBookingDataPanel(BookingDataPanel bookingDataPanel);

      /**
       * Sets the menu item which allows the selection of working online or offline.
       * This is registered with the manager for later call delegation.
       * @param modeMenuItem The user interface component to register with the manager.
       */
      void setModeMenuItem(ModeMenuItem modeMenuItem);

      /**
       * Handles the request to work in online mode.
       */
      void workOnline();

      /**
       * Handles the request to work in offline mode.
       */
      void workOffline();
  }
