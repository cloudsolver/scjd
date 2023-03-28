  package com.rohitsood.urlybird.client;

  import com.rohitsood.urlybird.biz.validator.DataValidator;

  import suncertify.db.RecordNotFoundException;
  import suncertify.db.ValidationException;

  import java.rmi.dgc.VMID;


  /**
   * Responsible for creating a unique VMID. Sub-classes will get
   *
   * @author Rohit Sood
   */
  abstract class AbstractDatabaseClient implements DatabaseClient
  {
      private static final VMID CLIENT_VMID = new VMID();

      /**
       * Gets the VMID associated with this virtual machine.
       * The VMID is created once by the classloader and is cached.
       * @return The unique VMID.
       */
      public VMID getVMID()
      {
          return CLIENT_VMID;
      }

      /* (non-Javadoc)
	 * @see com.rohitsood.urlybird.client.DatabaseClient#book(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean book(String recId, String maxOccupancy, String customerId)
          throws RecordNotFoundException, ValidationException
      {
          boolean valid = false;
          DataValidator.validateRecordNumber(recId);
          DataValidator.validateMaxOccupancy(maxOccupancy);
          DataValidator.validateCustomerId(customerId);
          valid = true;

          return valid;
      }
  }
