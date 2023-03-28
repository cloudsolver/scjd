  package com.rohitsood.urlybird.biz.validator;

  import suncertify.db.ValidationException;

  import java.text.DateFormat;
  import java.text.SimpleDateFormat;

  import java.util.Date;


  /**
   * Validates the data and throws exceptions as appropriate.   Used as a utility to validate the GUI
   * fields for correctness.  This is not expected to be sub-classes and is expected to be used as a
   * static validation utility.
   *
   * @author Rohit Sood
   * @version 1.2
   */
  public final class DataValidator
  {
      private static final long MAX_TIME_DIFFERENCE = 172800000;
      private static final int HOTEL_NAME_POS     = 0;
      private static final int CITY_NAME_POS      = 1;
      private static final int MAX_POS            = 2;
      private static final int SMOKING_POS        = 3;
      private static final int PRICE_POS          = 4;
      private static final int DATE_POS           = 5;
      private static final int CUSTOMER_POS       = 6;
      private static final int MAX_HOTEL_LEN      = 64;
      private static final int MIN_HOTEL_LEN      = 2;
      private static final int MAX_CITY_LEN       = 64;
      private static final int MIN_CITY_LEN       = 2;
      private static final int MIN_OCCUPANCY_LEN  = 1;
      private static final int MAX_OCCUPANCY      = 9999;
      private static final int MIN_PRICE_LEN      = 2;
      private static final int MAX_PRICE_LEN      = 8;
      private static final int MAX_DATE_LEN       = 10;
      private static final int MAX_CUSTOMER_ID_LEN = 99999999;
      private static final int MIN_CUSTOMER_ID_LEN = 10000000;

      private DataValidator()
      {
      }

      /**
       * Validates record number. Checks for null ids, zero length entries, spaces, and formats.
       *
       * @param recNo The record number to validate.
       *
       * @return <tt>true</tt> if record number is valid.
       *
       * @throws ValidationException thrown with a particular failure reason.
       */
      public static boolean validateRecordNumber(String recNo)
          throws ValidationException
      {
          if ((null == recNo) || (recNo.trim().length() < 1))
          {
              throw new ValidationException("Record Number  is invalid, it cannot be empty.");
          }
          else
          {
              try
              {
                  final int recId = Integer.parseInt(recNo);
                  validateRecordNumber(recId);
              }
              catch (NumberFormatException e)
              {
                  throw new ValidationException("Record Number can only be digits.");
              }
          }

          return true;
      }

      /**
       * Validates record number. Checks for negative record numbers.
       *
       * @param recNo the record number to validate
       *
       * @return <tt>true</tt> if the record number is valid.
       *
       * @throws ValidationException thrown with a particular failure reason
       */
      public static boolean validateRecordNumber(int recNo)
          throws ValidationException
      {
          if (recNo < 0)
          {
              throw new ValidationException("Record Number cannot be less than 0.");
          }

          return true;
      }

      /**
       * Validates the data contained in the string array passed in. Individually calls validation on
       * each data item contained in the array.
       *
       * @param data The array to be validated.
       *
       * @return <tt>true</tt> if all fields are valid.
       *
       * @throws ValidationException captures validation error messages
       */
      public static boolean validate(String[] data) throws ValidationException
      {
          boolean valid = false;
          valid = validateHotelName(data[HOTEL_NAME_POS]) && validateCityName(data[CITY_NAME_POS])
                  && validateMaxOccupancy(data[MAX_POS]) && validateSmoking(data[SMOKING_POS])
                  && validatePrice(data[PRICE_POS]) && validateDate(data[DATE_POS]);

          return valid;
      }

      /**
       * Validates hotel name. Checks for null and boundary lengths for hotel name. If a validation
       * fails, a <code>ValidationException</code> is thrown with an appropriate message.
       *
       * @param hotelName the name of the hotel to be validated
       *
       * @return <tt>true</tt>if hotel name is valid.
       *
       * @throws ValidationException captures validation error message.
       */
      public static boolean validateHotelName(String hotelName)
          throws ValidationException
      {
          if (null == hotelName)
          {
              throw new ValidationException("Hotel name cannot be null");
          }

          final int len = hotelName.length();

          if (len < MIN_HOTEL_LEN)
          {
              throw new ValidationException("Hotel name is too small, minimum length is "
                  + MIN_HOTEL_LEN);
          }

          if (len > MAX_HOTEL_LEN)
          {
              throw new ValidationException("Hotel name is too large, max length is "
                  + MAX_HOTEL_LEN);
          }

          return true;
      }

      /**
       * Validates City Name.  The city name is invalid if its null, less than the minimum length or
       * greater than the maximum specified field length.
       *
       * @param cityName The city name to validate.
       *
       * @return <tt>true</tt> is the city is valid.
       *
       * @throws ValidationException If a validation error occurs.
       */
      public static boolean validateCityName(String cityName)
          throws ValidationException
      {
          if (null == cityName)
          {
              throw new ValidationException("City name cannot be null");
          }

          final int len = cityName.length();

          if (len < MIN_CITY_LEN)
          {
              throw new ValidationException("City name is too small, minimum length is "
                  + MIN_CITY_LEN);
          }

          if (len > MAX_CITY_LEN)
          {
              throw new ValidationException("City name is too large, max length is " + MAX_CITY_LEN);
          }

          return true;
      }

      /**
       * Validates maximum occupancy of the room.  The Occupancy is valid if its less than the
       * minimum occupancy or greater than the maximum occupancy.
       *
       * @param maxOccupancy The occupancy to valiate.
       *
       * @return <tt>true</tt> id the occupancy is valid.
       *
       * @throws ValidationException If a validation exception is thrown.
       */
      public static boolean validateMaxOccupancy(int maxOccupancy)
          throws ValidationException
      {
          if (maxOccupancy > MAX_OCCUPANCY)
          {
              throw new ValidationException("Max occupancy cannot be greater than " + MAX_OCCUPANCY);
          }

          if (maxOccupancy < MIN_OCCUPANCY_LEN)
          {
              throw new ValidationException("Max occupancy can't be less than " + MIN_OCCUPANCY_LEN);
          }

          return true;
      }

      /**
       * Validates maximum occupancy of the room.  The Occupancy is valid if its less than the
       * minimum occupancy or greater than the maximum occupancy.
       *
       * @param maxOccupancy The occupancy to valiate.
       *
       * @return <tt>true</tt> id the occupancy is valid.
       *
       * @throws ValidationException If a validation exception is thrown.
       */
      public static boolean validateMaxOccupancy(String maxOccupancy)
          throws ValidationException
      {
          try
          {
              final int max = Integer.parseInt(maxOccupancy);
          }
          catch (NumberFormatException e)
          {
              throw new ValidationException("Max Occupancy field should only contain a number");
          }

          return true;
      }

      /**
       * Validates smoking. Smoking is valid if its either Y or N.
       *
       * @param smoking Value to be validated.
       *
       * @return <tt>true</tt> if its valid.
       *
       * @throws ValidationException If smoking is invalid.
       */
      public static boolean validateSmoking(String smoking)
          throws ValidationException
      {
          if (smoking == null)
          {
              throw new ValidationException("Smoking can't be null");
          }

          if (!smoking.equals("Y") && !smoking.equals("N"))
          {
              throw new ValidationException("Smoking can only be Y or N");
          }

          return true;
      }

      /**
       * Validates price.  Price is invalid if its null, less than minimum length or greater than
       * maximum length.
       *
       * @param price The price to validate.
       *
       * @return <tt>true</tt> if price is valid.
       *
       * @throws ValidationException If price is invalid.
       */
      public static boolean validatePrice(String price)
          throws ValidationException
      {
          if (price == null)
          {
              throw new ValidationException("Price cannot be empty or null");
          }

          if (price.length() < MIN_PRICE_LEN)
          {
              throw new ValidationException("Price is invalid");
          }

          if (price.length() > MAX_PRICE_LEN)
          {
              throw new ValidationException(
                  "Price is too long - must be limited to 8 characters including the currency symbol");
          }

          return true;
      }

      /**
       * Validates date. Date is invalid if it is null, not in the correct format, before the current
       * system date or any other data issue.
       *
       * @param date The date to validate.
       *
       * @return <tt>true</tt> if date is valid.
       *
       * @throws ValidationException Is thrown if date is invalid.
       */
      public static boolean validateDate(String date) throws ValidationException
      {
          if (date == null)
          {
              throw new ValidationException("Date cannot be null");
          }

          if (date.length() != MAX_DATE_LEN)
          {
              throw new ValidationException(
                  "Date is invalid, it should be of the format yyyy/mm/dd");
          }

          try
          {
              final DateFormat format   = new SimpleDateFormat("yyyy/MM/dd");
              final Date     test       = new Date(date);
              final String   dateString = format.format(test);

              if (!dateString.equals(date))
              {
                  throw new ValidationException("Date Format Error");
              }
          }
          catch (RuntimeException e)
          {
              throw new ValidationException(e.getMessage()
                  + "\nDate is invalid, it should be of the format yyyy/mm/dd");
          }

          final Date test = new Date(date);
          final Date today = new Date();

          if (test.before(today))
          {
              throw new ValidationException(
                  "Date cannot be in the past, i.e. cannot be before today: " + today);
          }

          return true;
      }

      /**
       * Validates customer id. A customer id is invalid if its null or not 8 digits.
       *
       * @param customerId The customer id to validate.
       *
       * @return <tt>true</tt> if customer id is valid.
       *
       * @throws ValidationException If customer id is invalid.
       */
      public static boolean validateCustomerId(int customerId)
          throws ValidationException
      {
          boolean valid = true;

          if ((customerId > MAX_CUSTOMER_ID_LEN) || (customerId < MIN_CUSTOMER_ID_LEN))
          {
              valid = false;
              throw new ValidationException("Customer id can only be 8 digits, it is currently "
                  + customerId);
          }

          return valid;
      }

      /**
       * Validates Customer ID. A customer id is invalid if it not numbers.
       * @param customerId The id to validate
       * @return <tt>true</tt> if the validation succeeds
       * @throws ValidationException If validation fails.
       */
      public static boolean validateCustomerId(String customerId)
          throws ValidationException
      {
          boolean valid = true;

          try
          {
              final int customer = Integer.parseInt(customerId);
              validateCustomerId(customer);
          }
          catch (NumberFormatException e)
          {
              valid = false;
              throw new ValidationException("Customer id can only consist of numbers.");
          }

          return valid;
      }

      /**
       * Validates a record for booking. A record is not bookable if customer id is invalid, or room
       * is more than 48 hours in the future from now, or the room is already booked, or the date of
       * availability is less than 48 hours from now.
       *
       * @param data The data record to validate.
       * @param maxCount The max occupancy of the booking. This should be less than the max occupancy
       *        of the room.
       * @param customerId The customer id against whom the bookin should be made.
       *
       * @return <tt>true</tt> if the record allowed to be booked by the customer, for the date with
       *         the given number of guests.
       *
       * @throws ValidationException If the record is not available.
       */
      public static boolean isBookable(String[] data, String maxCount, String customerId)
          throws ValidationException
      {
          validateMaxOccupancy(maxCount);
          validateCustomerId(customerId);
          validateDate(data[DATE_POS]);

          long now     = 0L;
          long available = 0L;

          now = System.currentTimeMillis();

          try
          {
              available = Date.parse(data[DATE_POS]);
          }
          catch (RuntimeException e)
          {
              throw new ValidationException("Date is invalid...cannot continue.");
          }

          if ((available - now) > MAX_TIME_DIFFERENCE)
          {
              throw new ValidationException(
                  "This room is not available since its not available less than 48 hours from now");
          }

          final String currentId       = data[CUSTOMER_POS];
          final int  currentMax        = Integer.parseInt(data[MAX_POS]);
          final int  maxCountRequested = Integer.parseInt(maxCount);

          if (maxCountRequested > currentMax)
          {
              throw new ValidationException("This room cannot be occupied by more than "
                  + currentMax + " individuals (not including infants)");
          }

          if ((currentId != null) && (currentId.trim().length() > 1))
          {
              throw new ValidationException("Room cannot be booked since its already booked by "
                  + currentId + ".");
          }

          if (currentId.equals("" + customerId))
          {
              throw new ValidationException(
                  "This room is already booked by the same customer with id " + customerId);
          }

          return true;
      }
  }
