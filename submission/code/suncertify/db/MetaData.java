package suncertify.db;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.util.RandomAccessFileUtility;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * This singleton is responsible for reading the meta data of the database file. Determines valid database files and
 * throws appropriate exceptions.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public final class MetaData implements DataRow {
    /** The singleton instance of <tt>MetaData</tt> */
    private static MetaData meta;

    /** The properties from which to read and configure this. */
    private ConfigurationProperties properties;

    /** The magic cookie value of the database file. */
    private int magicCookie;

    /** The total length of a record. */
    private int totalRecordLength;

    /** The total number of fields per record. */
    private short numberOfFieldsPerRecord;

    /** Data section interval. The position from which data begins. */
    private int dataSectionInterval = 1; //initialize for a boolean

    /** The data section pointer. */
    private long dataSectionPointer;

    /** The field names. */
    private String[] fieldNames;

    /** The lengths of fields. */
    private int[] fieldLengths;

    /** Used for debugging the application. */
    private boolean debug = false;

    /**
     * Creates a new MetaData object.
     */
    private MetaData() throws IOException {
        init();
    }

    /**
     * Accessor for the magic cookie value.
     *
     * @return The magic cookie value for the database file.
     */
    public int getMagicCookie() {
        return magicCookie;
    }

    /**
     * Mutators the magic cookie value that is read from the database file.
     *
     * @param magicCookie The magic cookie value read from the database file.
     */
    public void setMagicCookie(int magicCookie) {
        this.magicCookie = magicCookie;
    }

    /**
     * Returns the total length of the record including the boolean flag space.
     *
     * @return The total length of the record.
     */
    public int getTotalRecordLength() {
        return totalRecordLength + 1;
    }

    /**
     * @param totalRecordLength
     */
    public void setTotalRecordLength(int totalRecordLength) {
        this.totalRecordLength = totalRecordLength;
    }

    /**
     * Gets the instance of the meta data. Maintains only one connection.
     *
     * @return The meta data instance.
     */
    public static MetaData getInstance() {
        if (null == meta) {
            try {
                meta = new MetaData();
            } catch (IOException e) {
                meta = null;
                throw new RuntimeException("There was a problem reading meta-data: " + e.getMessage());
            }
        }

        return meta;
    }

    /**
     * A simple debug implementation to print to the console.
     *
     * @param msg The message to print.
     */
    private void debug(String msg) {
        if (debug) {
            System.out.print(msg);
        }
    }

    /**
     * Returns the array containing the length of fields.
     *
     * @return The array containing the lengths of the fields.
     */
    public int[] getFieldLengths() {
        return fieldLengths;
    }

    /**
     * The interval that separates each record. The total length of each record.
     *
     * @return The record interval.
     */
    public int getDataSectionInterval() {
        return dataSectionInterval;
    }

    /**
     * Returns the point at which the data sections.
     *
     * @return The data section starting point.
     */
    public long getDataSectionPointer() {
        return dataSectionPointer;
    }

    /**
     * Returns the total number of fields per record.  This is the column count.  Does not include the implicit deleted
     * flag field.
     *
     * @return The total number of columns in a record.
     */
    public short getNumberOfFieldsPerRecord() {
        return numberOfFieldsPerRecord;
    }

    /**
     * Returns the field position by name of the field. The position represents the array.  Does not include the
     * implicit deleted flag field.
     *
     * @param fieldName The name of the field for which to get the position.
     *
     * @return The position of the field.
     */
    public int getFieldPosition(String fieldName) {
        int returnValue = -1;

        for (int a = 0; a < getNumberOfFieldsPerRecord(); a++) {
            if (fieldNames[a].equalsIgnoreCase(fieldName)) {
                returnValue = a;
            }
        }

        if (returnValue < 0) {
            throw new IllegalArgumentException("No field with that name found: " + fieldName);
        }

        return returnValue;
    }

    /**
     * Returns the length of the field. This is the maximum length the field can hold. Given the name of the field it
     * provides the maximum length of the field.
     *
     * @param fieldName The name of the field.
     *
     * @return The maximum length the field can hold.
     */
    public int getFieldLength(String fieldName) {
        int fieldLength = -1;
        int fieldPosition = getFieldPosition(fieldName);
        fieldLength = getFieldLengths()[fieldPosition];

        return fieldLength;
    }

    /**
     * Sets the number of fields per record.
     *
     * @param numberOfFieldsPerRecord The number of fields in the record.
     */
    private void setNumberOfFieldsPerRecord(short numberOfFieldsPerRecord) {
        this.numberOfFieldsPerRecord = numberOfFieldsPerRecord;
    }

    /**
     * Initializes the meta data.
     */
    private void init() throws IOException {
        debug("enter isValid()");
        properties = new ConfigurationProperties();

        int magicCookie;
        int totalRecordLength;
        RandomAccessFile file = null;

        try {
            file = getFile("r");
            debug("\nReading magic cookie");

            magicCookie = file.readInt();
            setMagicCookie(magicCookie);
            debug("\nMagic cookie value=" + magicCookie);

            if (getMagicCookie() != Integer.parseInt(properties.getMagicCookie())) {
                debug("Invalid magic cookie value.");
                throw new RuntimeException("Invalid database file. Please select the correct database.");
            }

            totalRecordLength = file.readInt();
            setNumberOfFieldsPerRecord(file.readShort());

            fieldLengths = new int[getNumberOfFieldsPerRecord()];
            fieldNames = new String[getNumberOfFieldsPerRecord()];

            for (int a = 0; a < getNumberOfFieldsPerRecord(); a++) {
                short nameLength = file.readShort();
                String name = RandomAccessFileUtility.readString(file, nameLength);
                short fieldLength = file.readShort();
                dataSectionInterval += fieldLength;
                fieldNames[a] = name;
                fieldLengths[a] = fieldLength;

                debug(name + " is " + fieldLength);
            }

            debug("\nMagic cookie: " + magicCookie + ", Total Len: " + totalRecordLength + ", No. of Fields: " + getNumberOfFieldsPerRecord());

            long dt = file.getFilePointer();
            debug("\ndata starts at" + dt);
            dataSectionPointer = dt;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if(file!=null){
                	file.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * Gets an instance of <tt>RandomAccessFile</tt>.
     *
     * @param mode The mode in which to create the instancec.
     *
     * @return The <tt>RandomAccessFile</tt>.
     *
     * @throws IOException If there is a problem.
     */
    private RandomAccessFile getFile(String mode) throws IOException {
        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(properties.getDbLocation(), mode);
        } catch (Exception e) {
            throw new IOException("Database file could not be located");
        }

        return file;
    }
}
