  package suncertify.db;

  /**
   * This holds meta data information about the database. Pointer positions and default lengths are stored
   * in this interface.
   *
   * @author Rohit Sood
   * @version 1.0
   */
  public interface MetaData
  {
      /** The pointer where data starts. */
      int DATA_SECTION_POINTER = 74;

      /** The length of a data record. */
      int DATA_SECTION_INTERVAL = 160;

      /** The relative position of the deleted flag. */
      int DELETED_FLAG_POSITION = 0;

      /** The relative position of the hotel name. */
      int HOTEL_NAME_POSITION = 1;

      /** The relative position of the city name. */
      int CITY_NAME_POSITION = 65;

      /** The relative position of the maximum occupancy. */
      int MAX_OCCUPANCY_POSITION = 129;

      /** The relative position of the smoking flag. */
      int SMOKING_FLAG_POSITION = 133;

      /** The relative position of the price. */
      int PRICE_POSITION = 134;

      /** The relative position of the date available. */
      int DATE_AVAILABLE_POSITION = 142;

      /** The relative position of the customer id. */
      int CUSTOMER_RECORD_POSITION = 152;

      /** The total number of columns in the database. */
      int NUMBER_OF_COLUMNS = 7;

      /** The length of the deleted flag. */
      int DELETED_FLAG_LENGTH = 1;

      /** The length of the hotel name. */
      int HOTEL_NAME_LENGTH = 64;

      /** The length of the city name. */
      int CITY_NAME_LENGTH = 64;

      /** The length of the maximum occupancy. */
      int MAX_OCCUPANCY_LENGTH = 4;

      /** The length of the smoking flag. */
      int SMOKING_FLAG_LENGTH = 1;

      /** The  length of price. */
      int PRICE_LENGTH = 8;

      /** The  length of available date. */
      int DATE_AVAILABLE_LENGTH = 10;

      /** The length of the customer id. */
      int CUSTOMER_RECORD_LENGTH = 8;

      /** Meta data containing meta data */
      int[] META_DATA = { HOTEL_NAME_LENGTH, CITY_NAME_LENGTH,
      	MAX_OCCUPANCY_LENGTH, SMOKING_FLAG_LENGTH, PRICE_LENGTH, DATE_AVAILABLE_LENGTH, CUSTOMER_RECORD_LENGTH };
  }
