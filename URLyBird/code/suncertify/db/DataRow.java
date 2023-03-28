  package suncertify.db;

  import java.io.Serializable;


  /**
   * Specifies constants for the data row and element positions in the data array. Provides the basic
   * structure and names of the data and the data array.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public interface DataRow extends Serializable, MetaData
  {
      /** Array position of hotel. */
      int HOTEL_ARRAY_POSITION = 0;

      /** Array position of city. */
      int CITY_ARRAY_POSITION = 1;

      /** Array position of max occupancy. */
      int MAX_OCCUPANCY_ARRAY_POSITION = 2;

      /** Array position of smoking flag. */
      int SMOKING_ARRAY_POSITION = 3;

      /** Array position of  price. */
      int PRICE_ARRAY_POSITION = 4;

      /** Array position of date. */
      int DATE_ARRAY_POSITION = 5;

      /** Array position of customer. */
      int CUSTOMER_ARRAY_POSITION = 6;
  }
