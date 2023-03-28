package com.rohitsood.urlybird.gui.widget.panel;

import com.rohitsood.urlybird.gui.widget.textfield.CommonTextField;
import com.rohitsood.urlybird.util.SpringLayoutUtilities;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;


/**
 * This is a standard form consisting of labels and corresponding text fields. Provides the basic structure and
 * behavior of a data panel. This is the super class of all data panels in the user interface. Panels that represent
 * entire data set or a sub-set of the data must extend from this class.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public abstract class AbstractDataPanel extends JPanel {
    /** Array index of the record id. */
    protected static final int RECORD_INDEX = 0;

    /** Array index of the hotel record. */
    protected static final int HOTEL_INDEX = 1;

    /** Array index of the city record. */
    protected static final int CITY_INDEX = 2;

    /** Array index of the max occupancy record. */
    protected static final int MAX_INDEX = 3;

    /** Array index of the smoking record. */
    protected static final int SMOKING_INDEX = 4;

    /** Array index of the price record. */
    protected static final int PRICE_INDEX = 5;

    /** Array index of the available date record. */
    protected static final int DATE_INDEX = 6;

    /** Array index of the customer id. */
    protected static final int CUSTOMER_INDEX = 7;

    /** Text Field length */
    private static final int TEXT_FIELD_LENGTH = 10;

    /** The number of text fields in the user interface. */
    protected int fieldCount;

    /** Array to hold the text fields in the user interface. */
    protected CommonTextField[] fields;

    /** Array to hold the labels in the user interface. */
    protected JLabel[] labels;

    /** Text field for the record id. */
    protected CommonTextField recordTextField = new CommonTextField(TEXT_FIELD_LENGTH);

    /** Text field for the hotel. */
    protected CommonTextField hotelTextField = new CommonTextField();

    /** Text field for the city. */
    protected CommonTextField cityTextField = new CommonTextField();

    /** Text field for the maximum occupancy. */
    protected CommonTextField maxTextField = new CommonTextField();

    /** Text field for the smoking flag. */
    protected CommonTextField smokingTextField = new CommonTextField();

    /** Text field for the price. */
    protected CommonTextField priceTextField = new CommonTextField();

    /** Text field for the available date. */
    protected CommonTextField dateTextField = new CommonTextField();

    /** Text field for the customer id. */
    protected CommonTextField customerTextField = new CommonTextField();

    /** Label for the record id. */
    protected JLabel recordTextLabel = new JLabel("Record ID:");

    /** Label for the hotel. */
    protected JLabel hotelTextLabel = new JLabel("Hotel :");

    /** Label for the city. */
    protected JLabel cityTextLabel = new JLabel("City :");

    /** Label for the  maximum occupancy. */
    protected JLabel maxTextLabel = new JLabel("Max. Occupancy :");

    /** Label for the smoking flag. */
    protected JLabel smokingTextLabel = new JLabel("Smoking :");

    /** Label for the  price. */
    protected JLabel priceTextLabel = new JLabel("Price :");

    /** Label for the available date. */
    protected JLabel dateTextLabel = new JLabel("Date Available :");

    /** Label for the customer id. */
    protected JLabel customerTextLabel = new JLabel("Customer ID :");

    /** Orientation for the data panel. */
    protected DataPanelOrientation orientation;

    /**
     * Default constructor.
     */
    public AbstractDataPanel() {
    }

    /**
     * Initializes the panel for the user iterface. Lays out the user interface, sets the preferred size and adds
     * widgets.
     */
    protected void init() {
        fields = new CommonTextField[getFieldCount()];
        labels = new JLabel[getFieldCount()];
        createWidgets(); //create

        final SpringLayout layout = new SpringLayout();
        setLayout(layout);
        addWidgets(); //add
        setBorder(LineBorder.createBlackLineBorder());
        SpringLayoutUtilities.makeSpringGrid(this, orientation.getRowCount(), orientation.getColumnCount(), SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD, SpringLayoutUtilities.SPREAD);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    /**
     * Adds the widgets to the panel.
     */
    protected void addWidgets() {
        for (int a = 0; a < fields.length; a++) {
            add(labels[a]);
            add(fields[a]);
        }
    }

    /**
     * Hook for creating widgets.
     */
    protected abstract void createWidgets();

    /**
     * Collects data from all the text fields in an array and returns it.
     *
     * @return The data in the fields.
     */
    public String[] getData() {
        final String[] data = new String[getFieldCount()];

        for (int a = 0; a < data.length; a++) {
            String fieldData = fields[a].getText();
            data[a] = fieldData;
        }

        return data;
    }

    /**
     * Collects data from all the text fields in an array except the first.
     *
     * @param withRecId The data in the fields except the first one.
     *
     * @return The data record.
     */
    public String[] getData(boolean withRecId) {
        if (withRecId) {
            return getData();
        }

        return stripRecordId(getData());
    }

    /**
     * Collects data in the records skipping the first one.
     *
     * @param data The data from which the first record needs to be skipped.
     *
     * @return The data without the first record in the original.
     */
    private String[] stripRecordId(String[] data) {
        final String[] d = new String[getFieldCount() - 1];

        for (int a = 1; a < getFieldCount(); a++) {
            d[a - 1] = data[a];
        }

        return d;
    }

    /**
     * Loads data into the text fields.
     *
     * @param recId The record id.
     * @param data The data array.
     */
    protected void loadTextFields(int recId, String[] data) {
        fields[0].setText("" + recId);

        for (int a = 1; a < (data.length + 1); a++) {
            fields[a].setText(data[(a - 1)]);
        }
    }

    /**
     * Enables or disables all fields based on the boolean.
     *
     * @param enable Boolean which determines whether to enable or not.
     */
    public void enableFields(boolean enable) {
        for (int a = 0; a < fields.length; a++) {
            if (enable) {
                fields[a].setEnabled(true);
            } else {
                fields[a].setEnabled(false);
            }
        }
    }

    /**
     * Clears all text fields of all data. Iterates through all the <tt>CommonTextField</tt>s and sets the text to a
     * zero-length string.
     */
    public void clearFields() {
        for (int a = 0; a < fields.length; a++) {
            fields[a].setText("");
        }
    }

    /**
     * Returns the record id from the record text field.
     *
     * @return The record id.
     */
    public String getRecordId() {
        final String text = recordTextField.getText();

        return text;
    }

    /**
     * Returns the hotel name from the hotel text field.
     *
     * @return The hotel name.
     */
    public String getHotelName() {
        final String hotelName = hotelTextField.getText();

        return hotelName;
    }

    /**
     * Returns the city name from the city text field.
     *
     * @return The city name.
     */
    public String getCityName() {
        final String cityName = cityTextField.getText();

        return cityName;
    }

    /**
     * Returns the max occupancy from the max occupancy field.
     *
     * @return The max occupancy.
     */
    public String getMaxOccupancy() {
        final String text = maxTextField.getText();

        return text;
    }

    /**
     * Returns the smoking flag from the smoking field.
     *
     * @return The smoking flag.
     */
    public String getSmoking() {
        final String text = smokingTextField.getText();

        return text;
    }

    /**
     * Returns the price from the price field.
     *
     * @return The price for the room.
     */
    public String getPrice() {
        final String text = priceTextField.getText();

        return text;
    }

    /**
     * Returns the date available from the date available field.
     *
     * @return The date available.
     */
    public String getAvailableDate() {
        final String text = dateTextField.getText();

        return text;
    }

    /**
     * Returns the customer id from the customer id field.
     *
     * @return The customer id.
     */
    public String getCustomerId() {
        final String text = customerTextField.getText();

        return text;
    }

    /**
     * Accessor for the field count of the panel.
     *
     * @return The field count.
     */
    public int getFieldCount() {
        return fieldCount;
    }

    /**
     * Mutator for the field count for the panel.
     *
     * @param fieldCount
     */
    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    /**
     * Accessor for the <tt>CommonTextField</tt> array.
     *
     * @return The array containing <tt>CommonTextField</tt> objects.
     */
    public CommonTextField[] getFields() {
        return fields;
    }

    /**
     * Mutator for the <tt>CommonTextField</tt> array.
     *
     * @param fields The array containing <tt>CommonTextField</tt> objects.
     */
    public void setFields(CommonTextField[] fields) {
        this.fields = fields;
    }

    /**
     * Accessor for the array containing all label objects
     *
     * @return The array of label objects.
     */
    public JLabel[] getLabels() {
        return labels;
    }

    /**
     * Mutator for the array containing all label objects.
     *
     * @param labels The array of label objects.
     */
    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }
}
