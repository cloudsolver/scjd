package com.rohitsood.urlybird.gui.manager;

import com.rohitsood.urlybird.gui.AbstractMainWindow;
import com.rohitsood.urlybird.gui.mode.ModeMenuItem;
import com.rohitsood.urlybird.gui.about.AboutDialog;
import com.rohitsood.urlybird.gui.record.GoPanel;
import com.rohitsood.urlybird.gui.record.ManageRecordButtonPanel;
import com.rohitsood.urlybird.gui.record.ManageRecordDataPanel;
import com.rohitsood.urlybird.gui.preferences.PreferencesDialog;

import com.rohitsood.urlybird.gui.reservation.ConfirmBookingDialog;
import com.rohitsood.urlybird.gui.search.SearchRecordDataPanel;
import com.rohitsood.urlybird.gui.search.SearchResultTable;
import com.rohitsood.urlybird.gui.search.SearchResultTableModel;
import com.rohitsood.urlybird.gui.server.ServerModeMenuItem;
import com.rohitsood.urlybird.mode.Mode;

import javax.swing.JDesktopPane;


/**
 * Mediator for all UI components in the system. All components are registered
 * with the manager, when events are fired, the call is delegated to the
 * manager. The manager decouples individual widgets and centralizes all
 * controls and changes to the UI. The manager is also responsible for
 * selecting the mode of the client. This can be switched dynamically.
 *
 * @author Rohit Sood
 * @version 1.8
 */
public interface GuiManager extends Mode {
    /**
     * Sets the mode of the gui manager.  Appropriate clients are instatiated
     * and used based on the mode.
     *
     * @param mode The mode to run the <tt>GuiManager</tt> in.
     */
    void setMode(int mode);

    /**
     * Sets the about dialog that displays application information.
     *
     * @param aboutDialog The dialog to register with the manager.
     */
    void setAboutDialog(AboutDialog aboutDialog);

    /**
     * Returns the main window of the application.
     *
     * @return The window of the main application.
     */
    AbstractMainWindow getMainWindow();

    /**
     * Sets the panel that handles record management. This is registered with
     * the manager for later call delegation.
     *
     * @param manageRecordDataPanel The user interface component to register
     *        with the manager.
     */
    void setManageRecordDataPanel(ManageRecordDataPanel manageRecordDataPanel);

    /**
     * Sets the panel that handles search requests and results. This is
     * registered with the manager for later call delegation.
     *
     * @param searchRecordDataPanel The user interface component to register
     *        with the manager.
     */
    void setSearchRecordDataPanel(SearchRecordDataPanel searchRecordDataPanel);

    /**
     * Sets the desktop pane which holds the internal frames. This is
     * registered with the manager for later call delegation.
     *
     * @param desktop The user interface component to register with the
     *        manager.
     */
    void setDesktopPane(JDesktopPane desktop);

    /**
     * Sets the dialog that works on the with the
     * <tt>ConfigurationProperties</tt> object. This is registered with the
     * manager for later call delegation.
     *
     * @param preferencesDialog The user interface component to register with
     *        the manager.
     */
    void setPreferencesDialog(PreferencesDialog preferencesDialog);

    /**
     * Sets the main window which is the main <tt>JFrame</tt> that holds all UI
     * components. This is registered with the manager for later call
     * delegation.
     *
     * @param window The user interface component to register with the manager.
     */
    void setMainWindow(AbstractMainWindow window);

    /**
     * Sets the table which holds search results. This is registered with the
     * manager for later call delegation.
     *
     * @param searchResultTable The user interface component to register with
     *        the manager.
     */
    void setSearchResultTable(SearchResultTable searchResultTable);

    /**
     * Gets the desktop pane that holds all the <tt>JInternalFrame</tt>
     * objects.
     *
     * @return The desktop pane associated with the <tt>MainWindow</tt>.
     */
    JDesktopPane getDesktopPane();

    /**
     * Handle the request for opening the <tt>ManageRecordInternalFrame</tt>.
     */
    void openManageRecordInternalFrame();

    /**
     * Handle the request for opening the <tt>ServerConsoleInternalFrame</tt>.
     */
    void openServerConsoleInternalFrame();

    /**
     * Handles the request to exit the application.
     */
    void exitApplication();

    /**
     * Handles the request to cancel the preferences dialog.
     */
    void preferencesCancelButtonClicked();

    /**
     * Handles the request to take action when the preferences menu item is
     * selected.
     */
    void preferencesMenuItemClicked();

    /**
     * Handles the request to take action when the go button is clicked.
     */
    void goButtonClicked();

    /**
     * Set the panel which allows the user to bring up a record for its
     * management. This is registered with the manager for later call
     * delegation.
     *
     * @param goPanel The user interface component to register with the
     *        manager.
     */
    void setGoPanel(GoPanel goPanel);

    /**
     * Handles the request to open a search record internal frame.
     */
    void openSearchRecordInternalFrame();

    /**
     * Handles the request to take action when the add button is clicked on the
     * manage record panel.
     */
    void addButtonClicked();

    /**
     * Handles the request to take action when the update button is clicked on
     * the manage record panel.
     */
    void updateButtonClicked();

    /**
     * Handles the request to take action when the cancel  button is clicked on
     * the manage record panel,
     */
    void cancelButtonClicked();

    /**
     * Handles the request to take action when the delete button is clicked on
     * the manage record panel,
     */
    void deleteButtonClicked();

    /**
     * Handles the request to take action when the edit button is clicked on
     * the manage record panel,
     */
    void editButtonClicked();

    /**
     * Sets the button panel which interact with the data in the manage record
     * data panel. This is registered with the manager for later call
     * delegation.
     *
     * @param buttonPanel The user interface component to register with the
     *        manager.
     */
    void setManageRecordButtonPanel(ManageRecordButtonPanel buttonPanel);

    /**
     * Handles the request to take action when the preferences save button is
     * clicked.
     */
    void savePreferencesButtonClicked();

    /**
     * Sets the model of the <tt>SearchResultTable</tt>. This is registered
     * with the manager for later call delegation.
     *
     * @param searchTableModel The user interface component to register with
     *        the manager.
     */
    void setSearchResultTableModel(SearchResultTableModel searchTableModel);

    /**
     * Handles the request to take action when the search button is clicked on
     * the search record panel.
     */
    void searchButtonClicked();

    /**
     * Handles the request to take action when the book button is clicked on
     * the search record panel.
     */
    void bookButtonClicked();

    /**
     * Sets the confirmation booking dialog which holds the booking data panel.
     * This is registered with the manager for later call delegation.
     *
     * @param confirmBookingDialog The user interface component to register
     *        with the manager.
     */
    void setConfirmBookingDialog(ConfirmBookingDialog confirmBookingDialog);

    /**
     * Handles the request to take action when the confirm reservation button
     * is clicked.
     */
    void confirmReservationButtonClicked();

    /**
     * Handles the request to take action when the confirm close button is
     * clicked .
     */
    void confirmCloseReservationButtonClicked();



    /**
     * Sets the menu item which allows the selection of working online or
     * offline. This is registered with the manager for later call delegation.
     *
     * @param modeMenuItem The user interface component to register with the
     *        manager.
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

    /**
     * Closes the about dialog box. This is called when the close button is
     * clicked on the dialog.
     */
    void closeAboutDialog();

    /**
     * Opens the about dialog box that displays information about the
     * application.
     */
    void openAboutDialog();

    /**
     * Handles the request to handle an exception. An exception option pane is
     * displayed with the message contained in the exception.
     *
     * @param e The exception to handle.
     */
    void handleException(Exception e);

    /**
     * Handles the request to handle an exception.
     *
     * @param message The detailed message to display.
     * @param e The exception to handle.
     */
    void handleException(String message, Exception e);

    /**
     * Appends messages to the server gui console.
     *
     * @param message The message to append to the text area.
     */
    void appendToConsole(String message);

    /**
     * Clears the server text area.
     */
    void clearServerConsole();

    /**
     * Sets the <tt>ServerModeMenuItem</tt> for later invocation to toggle the
     * text.
     *
     * @param serverModeMenuItem The <tt>ServerModeMenuItem</tt> to register
     *        with the manager.
     */
    void setServerModeMenuItem(ServerModeMenuItem serverModeMenuItem);

    /**
     * Makes calls to appropriate object(s) which starts the remote server.
     */
    void startServer();

    /**
     * Makes calls to appropriate object(s) which stops the remote server.
     */
    void stopServer();

    /**
     * Clears the console.
     */
    void closeServerConsole();
}
