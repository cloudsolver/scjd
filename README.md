# Sun Certified Java Developer 2003

## Considerations 
1. RMI Vs Sockets

I made the choice of using RMI because it provided a clean abstraction for socket communication, provided a registry for remote objects. It was easier and more efficient than pure sockets. RMI made the distribution of objects easier because it works on remote references, since I used one server instance per client and remotely garbage collected the server instance on a disconnect it makes a lot of sense to use RMI since this is part of the RMI/JRMP architecture which I did not have to re-invent.


2. Did you modify or extend Data class and why ?

For accessing data I modified the data.java class and implemented all the data access logic. I abstracted the locking implementation and the key management implementation.
The crud methods would obtain a file object from a private method in the class with a given mode. I used a RandomAccessFile object and seeked to the correct position based on the record id. Seeks were calculated based on the length of a record including the delete flag multiplied by the record id plus the initial space for the meta data.


It would have been fairly simple to implement the locking implementation.

3. How did you handle local vs server mode ?

In the gui I provided a menu item with a "Work online" mode, if the application was started in "alone" mode, this menu item would read "Work Online" , clicking on this would trigger the workOnline() method in the GuiManager implementation class which would instantiate a DatabaseNetworkClient via the DatabaseClientFactory. All database action would go throught that....vice versa.

The Client layer was used to switch between the modes, I defined a DatabaseClient interface and two realizations -DatabaseNetworkClient and DatabaseDirectClient, the DatabaseClient interface extended from DBMain and additionally provided a signature for booking a room. The DatabaseClientfactory was responsible to instantiate an appropriate client implementation based on the mode. The GuiManager used the DatabaseClientFactory to instantiate a client and delegate all data access calls to it.


4. Explain your lock/unlock methods

The lock methods in the data class would delegate it to the LockManager which maintained a map of locked record and VMID (the unique client). Its main methods are lock, unlock, islocked and clearLocks


5. Explain your search criteria

The search criteria consists of all fields (except the record id). It is then matched with the all the records for a matches. The find method sequentially loads a record, and passed that and the criteria (which is an array of String objects) to a private method which returns a boolean. The matching algorithm lives in that private method. A match is determined like this:
If the criteria is null or has all null or empty strings the record will match.
If the criteria is not null or has atleast one non-null and non-empty string in the array - it will match records based on a startsWith semantics for that field.
Each recordId of a matched record is stored in an array and the method returns.
The manager loads all the records from the ids and addData into the table model.
If no search results were found an appropriate dialog informs the user and clears the previous results in the table if any.

If no records are found for a criteria 

6. How did you launch the application

java -jar runme.jar
java -jar runme.jar alone
java -jar runme.jar server

The Launcher interface, the LauncherFactory, AloneLauncher, ClientLauncher, ServerLauncher.


7. How did you use JTable

I used the JTable to show the results of the search. 


