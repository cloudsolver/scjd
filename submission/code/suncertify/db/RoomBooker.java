package suncertify.db;

/**
 * A helper class that validates and books rooms.
 * It makes use of <tt>DataValidator</tt> and <tt>DBMain<tt> to validate records and make bookings.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class RoomBooker {
    /**The database instance to use for updating records.*/
    private DBMain db;

    /**
     * Creates a new RoomBooker object.
     *
     * @param db The database instance to use for booking a room.
     */
    public RoomBooker(DBMain db) {
        this.db = db;
    }

    /**
     * Books a room given the record id, maximum occupancy and customer id.
     * Validates the record such that the room can be booked successfully.
     *
     * @param recId The record id of the room to make a booking for.
     * @param maxOccupancy The number of guests.
     * @param customerId The customer id against whom the bookong should be made.
     *
     * @throws RecordNotFoundException If the record could not be found to be booked.
     * @throws ValidationException If the booking information is not valid, or the room is not bookable.
     */
    public void book(String recId, String maxOccupancy, String customerId) throws RecordNotFoundException, ValidationException {
        DataValidator.validateRecordNumber(recId);

        final int recordId = Integer.parseInt(recId);
        final String[] data = db.read(recordId);
        DataValidator.isBookable(data, maxOccupancy, customerId);
        data[MetaData.getInstance().getFieldPosition(DataRow.CUSTOMER)] = "" + customerId;
        db.update(recordId, data);
    }
}
