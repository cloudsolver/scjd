package suncertify.db;

import java.io.Serializable;


/**
 * Specifies constants for the data row and element positions in the data array. Provides the basic structure and names
 * of the data and the data array.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public interface DataRow extends Serializable {
    /**Field name for hotel*/
    String HOTEL = "name";

    /**Field name for city.*/
    String CITY = "location";

    /**Field name for maximum occupancy.*/
    String MAX_OCCUPANCY = "size";

    /**Field name for smoking flag.*/
    String SMOKING = "smoking";

    /**Field name for price of room.*/
    String PRICE = "rate";

    /**Field name of available date.*/
    String AVAILABLE_DATE = "date";

    /** Field name for customer. */
    String CUSTOMER = "owner";

    /** The magic cookie value expected in this database. */
    int MAGIC_COOKIE_VALUE = 257;
}
