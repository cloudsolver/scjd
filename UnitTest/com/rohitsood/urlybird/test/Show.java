  package com.rohitsood.urlybird.test;

  


  /**
   * @author Rohit
   */
  public final class Show
  {
      /**
       * Creates a new Show object.
       */
      private Show()
      {
      }

      /**
       * DOCUMENT ME!
       *
       * @param data DOCUMENT ME!
       */
      public static void printRecord(String[] data)
      {
          final StringBuffer buffer = new StringBuffer();
			
			for(int a=0;a<data.length;a++){
				buffer.append(data[a]+"\n");
			}
//          buffer.append("\nHotel: " + data[0]);
//          buffer.append("\nCity: " + data[1]);
//          buffer.append("\nMax Occ: " + data[DataRow.MAX_OCCUPANCY_ARRAY_POSITION]);
//          buffer.append("\nSmoking: " + data[DataRow.SMOKING_ARRAY_POSITION]);
//          buffer.append("\nPrice: " + data[DataRow.PRICE_ARRAY_POSITION]);
//          buffer.append("\nDate: " + data[DataRow.DATE_ARRAY_POSITION]);
//          buffer.append("\nCust id: " + data[DataRow.CUSTOMER_ARRAY_POSITION]);

          System.out.println(buffer.toString());
      }

      /**
       * DOCUMENT ME!
       *
       * @param data DOCUMENT ME!
       */
      public static void show(String[] data)
      {
          for (int a = 0;a < data.length;a++)
          {
              System.out.println(a + ": " + data[a]);
          }
      }
  }
