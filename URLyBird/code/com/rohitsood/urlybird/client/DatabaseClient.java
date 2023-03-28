  package com.rohitsood.urlybird.client;

  import com.rohitsood.urlybird.Mode;

import suncertify.db.DBMain;
  import suncertify.db.RecordNotFoundException;
  import suncertify.db.ValidationException;


  /**
   * The <tt>DatabaseClient</tt> interface is used by all types of client. Currently there is support
   * for Online and Offline modes, in the future all types of clients should extend this and provide
   * different functionality. The <tt>DatabaseClient</tt> defines all methods as defined by
   * <tt>DBMain</tt> interface. Also all clients that support booking a room and disconnecting from
   * the server or file system must implement this interface.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public interface DatabaseClient extends DBMain,Mode
  {


      /**
       * Books a room. Validates the availability of record id is available, max occupancy and
       * customer id is valid and the room is not already booked. Also date checks are made for a
       * valid booking to take place.
       *
       * @param recId The record id against which to make the booking. This is the logical record
       *        positoin.
       * @param maxOccupancy The maximum occupancy of the booking. This number should not exceed the
       *        room capacity. No further calculations are made or saved.
       * @param customerId The customer id against whom the booking is made. This is stored in the
       *        database and signifies that the room has been booked.
       *
       * @return <tt>true</tt> if the booking was successful.
       *
       * @throws RecordNotFoundException If a record was not found. This can happen when an admin
       *         deletes a record while another client is in the process of making the booking.
       * @throws ValidationException If a record is not bookable.
       */
      boolean book(String recId, String maxOccupancy, String customerId)
          throws RecordNotFoundException, ValidationException;

      /**
       * Disconnects from the database. For distributed clients and server objects are de-referenced
       * and garbage collected. For local clients, the file system is simply flushed and closed.
       */
      void disconnect();

      int getMode();
  }
