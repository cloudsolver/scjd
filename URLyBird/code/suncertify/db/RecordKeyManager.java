  package suncertify.db;

  import java.util.HashMap;
  import java.util.Map;


  /**
   * Provides the same record key object and reuses the key. Caches the record id as an <tt>Integer</tt>
   * and reuses the same instance.
   *
   * @author Rohit Sood
   * @version 1.3
   */
  public final class RecordKeyManager
  {
      //	integer pairs, where the second integer is reused for thread locking
      static Map m = new HashMap();

      /**
       * Creates a new KeyUtil object.
       */
      private RecordKeyManager()
      {
      }

      /**
       * Returns a cached key object given the record number. If a cached key is not available then a new
       * one is created and returned. The first hit also adds the key to the cache of keys.
       *
       * @param recNo The record number to lookup.
       *
       * @return The record key.
       */
      public static Integer getKey(int recNo)
      {
          Integer key = (Integer) m.get(new Integer(recNo));

          if (key == null)
          {
              key = new Integer(recNo);
              m.put(key, key);
          }

          return key;
      }
  }
