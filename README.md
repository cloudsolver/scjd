# Sun Certified Java Developer 2003

Choices


(I) Introduction

	This document outlines the design considerations for the requirement of the URLyBird project. It will outline uncertainties, issues, decisions and design considerations. For the purposes of this discussion, “alone mode” will be referred as "offline" and the “default network mode” will be referred as "online". This document is divided into three sections, this “Introduction”, “Uncertainties, Issues and Decisions” and “Design Considerations”. The last two sections are further divided into sub-sections. The next section outlines the high-level decisions that had to be made relative to uncertainties or issues that I saw. Each decision is justified by providing pros and cons for an alternative approach. Besides correctness, I kept extensibility and maintainability as primary concerns, scalability and performance as secondary concerns, security was not a concern (due to the architectural requirement constraints).


(II) Uncertainties, Issues and Decisions

	Given the requirements documentation, the following decisions had to be made:

Networking – RMI or Sockets?
Record Locking – Pessimistic or Optimistic? Support long-lived transactions?
GUI – what windows to design?
Meta Data – to make it dynamic or hard-code field names, lengths and positions?

1. Networking: there were two options for network communication – use network sockets or RMI over JRMP. Network sockets would have provided the ability for creating an application-specific communication protocol; however I did not see the need for that. I made the choice of using RMI instead, because it provides a clean abstraction to socket programming and provides the added benefit of a centralized object registry without having the need to write the middle ware code. In addition, I wanted to use distributed garbage collection when the client disconnected from the server, and this came free with RMI over JRMP.  Given the points above I chose RMI/JRMP for networking.

2. Record Locking: a choice had to be made to select between optimistic or pessimistic locking. If the find operation locks each record before reading it for matching criteria, the concurrent access of data would be greatly hampered in terms of performance with no real gains. For read-only find operations a record lock is not needed. This is because there is user think-time involved before the next action is executed on the data, i.e. a user will select a record from the search results to start the booking process. 

No records were locked for read-only operations including “find”. Checks are made for locked records, and records are subsequently locked just before the actual insert or update on disk. If the record is already locked then that thread gives up CPU cycles and waits until it is notified. I made this choice for simplicity and ease of use. 

No locks are held across any business process. In order to maintain first-come-first-serve semantics for booking a room the record is not locked by the client until the actual update occurs. It is entirely possible that a room can be booked by a second agent while the first is in the process of booking that room – in which case the scenario is handled by appropriate error messages. 

Another possible scenario is that the record may be deleted while the agent has started the process of booking a room. In this case, appropriate error handling is in place to handle such situations at the time of update. In a future release, it is also possible to check for any changes in the record between the time a record is read for the purposes of booking and an update is executed to book that room, on detection of a change in the underlying data an appropriate execution path may be followed. Currently, only deletes, and booked rooms are detected and handled with an appropriate error message.

Given the above and other issues with locking, it made more sense to optimistically lock a record, not lock records across business processes that involve user think-time. 

3. Graphical User Interface: The requirements for creating the user interface were very high-level and there were no concrete requirements for look and feel, windows etc. This led to questions regarding the creation of the GUI, on the other hand it gave me the freedom to create a user interface that I thought satisfied all business requirements (searching and booking a room). I also implemented an admin screen which allowed the administrator to add, update and delete records. I wanted to keep the types of widgets at a minimum and also keep the complex inter-dependencies of widgets under control. All this needed to be streamlined and a framework established. 

I created one main window – a “JFrame” with two internal frames – one internal frame that allows searching and consequently booking a room, the other internal frame allows the user to manage records and perform administrative functions. A simple menu bar and corresponding menu items drive the actions the user wants to execute; each internal frame would be a singleton for simplicity and ease of use. Only one instance of the internal frame can be open at a time in the frame. Advanced users still have the option to open up multiple instances of the graphical user interface. Preferences were made accessible through the help menu; this was the most logical place for it, since it was set-it-and-forget-it semantics. 

The framework consists of a main window, the GUI manager, one layout utility and common buttons and text fields.

4. Meta-data: Although detailed specification was provided in the requirements documentation, a choice had to be made whether to “hard-code” those values in source code or look up the values from the meta-data section of the database file. I made a choice to read the meta-data dynamically from the database file section including the magic cookie value. I made this choice such that it is easier to make changes to the database layer (adding/removing/updating fields) with little impact to the database access layer. I decided to use the magic cookie value for validation of the database. 

Validation would occur by simply comparing the magic cookie value of the database file to the expected value. The expected value is stored in the “suncertify.properties” file via the preferences dialog window of the GUI. This is so that different database files (with different magic cookie numbers) can be used for future extensions.


(III) Design Considerations

One of the main design goals was to comply with the architectural constraints and direction as stated in the requirements document. The requirements hinted at three layers the user interface, network database server and the data base implementation. Each layer communicates with the next layer and not the layer before it thereby avoiding cyclic dependencies.

	The application is layered as follows:

(1) Graphical User Interface	
(2) Database Client
(3) Database Server
(4) Data Access & Locking

Each layer had design decisions and considerations: the GUI needed reduced interdependencies between widgets and a simple framework needed to be established for extensibility of the system.

The database client completely decouples the gui from the data access layer and the network data server. The database server is remote and the concerns were that the “DBMain” interface did not support remote invocation, and hence could not be used in the server code hierarchy. The server has a gui, and clean shutdown capabilities. Dynamic downloading and policy files are not included and IIOP was not used per the requirements document, the application took advantage of JRMP. Each layer is discussed relative to design considerations in details below:

1. Graphical User Interface: The graphical user-interface can become a myriad of interacting and dependent objects. My design goal was to make the intra-package objects highly cohesive and inter-package objects loosely coupled. I decided to use the GoF mediator pattern to reduce the interdependencies of the components and loosely couple disparate objects. The mediator consists of the GuiManager interface and its implementation class, all significant components register with the manager and delegate calls to it, the manager is then responsible to interact with components that have registered with it to perform the necessary actions. This established a simple framework for the user interface layer.

Currently there is only one concrete manager GuiManagerImpl this handles all the complexities for the gui components, it is the nucleus of the GUI framework for future extensions. The one downside of using this pattern is the obvious “super-object” syndrome where the manager knows about a lot of objects and changes to those objects may impact the manager. 

In addition, I created a common set of widgets to have the same default behavior and look across the application. The MainMenuBar, AbstractDataPanel provide convenient super classes meant to be customized by sub-classes. The AbstractDataPanel provides a standard data panel which can hold a form and helps in formatting it based on the orientation required. I created a common button and text field with default behavior. The use of colors is unified across the application as well. This allows a developer to add more objects and reuse the current functionality to provide business value in future releases. 

2. Database Client:  The database client has the main responsibility to serve as the entry-point for all data access in either mode (Online or Offline). I defined a DatabaseClient interface which complied with DBMain and provided a signature to book a room. I used the factory method to obtain the correct instance based on the mode the application is running in.

The DatabaseClientFactory is responsible for creating an instance of a client. The DatabaseDirectClient and DatabaseNetworkClient are the two concrete types of clients that would run in the offline and online modes respectively. The database client layer decouples the network server and data access classes from the user interface. The data and server implementation can now change independently of the user interface and vice-versa. The DatabaseClient implements the provided DBMain interface and adds additional methods to disconnect and book a room.

The ability to book a room, disconnect from the remote database sever and the definition of type of connection needed to be defined such that the user could switch between online and offline modes at runtime. In addition, the data access classes had to be decoupled from the user interface. 

The network client delegates all calls to the network server. The direct client delegates all calls directly to the data access class and acts as a proxy for “Data.java”. Both clients provide a unique client id (VMID). Since this client id is used for locking, it was imperative that the ids be unique across virtual machines hence the choice of using VMID. I chose not to assign ids at the network server layer since the client had to be identified in stand-alone mode as well, and in stand-alone mode no network code is executed. 


3. Database Server: I had to make a choice between working with one server object instance per client, or one thread per client command on a single server instance. I chose to create a server factory and register it in the RMI registry. This remote server factory creates new remote server instances. The client gets a reference to the server factory and creates a new remote server instance for its session. Instead of multithreading clients into a single instance, each client has a light- weight server instance. Once the client disconnects that server instance is destroyed and remotely garbage collected. 

The client is expected to look up the server factory and then request an instance of the server from it. The server itself is not bound to the registry. The factory maintains a list of server references which it uses for cleaning up at the time of shutdown. The server is defined exactly like DBMain but does not extend from it because DBMain does not throw any RemoteExceptions from any of its methods. 

Currently server instance throttling or instance pooling is not in place. It is possible to peak the JVM server memory with unreasonable load. In a future release, instance throttling and pooling can be plugged in such that the server limits are never crossed.


	
4. Data Access & Locking: The primary concern with data access was record locking. There was uncertainty in requirements as to the exact process of booking a record and subsequently any impacts the business process had on temporary locks on a room while it was being booked. Questions I had: - what is the room booking process, and when the process is initiated - do other agents have the ability to update/book that room? I made a decision to *not* lock records temporarily, instead until the actual database insert or update was requested no locks were made on a record. If the record was found to be locked at the point of an update- the requestor thread will wait for that record to be unlocked. 

Locking is handled by 2 classes – the LockManager and the RecordKeyManager. The lock manager maps the record id to the client that locked it. The record id is stored as a java.lang.Integer object which is obtained from the RecordKeyManager. The RecordKeyManager is responsible for maintaining the record id object instances and reuses the record id instances for locking purposes (cookie). The key object obtained from the RecordKeyManager is used to synchronize access to them and lock the records appropriately. Waiters wait on the availability of the keys from the RecordKeyManager. 

Locking is particularly important when creating a new record and updating an existing one. If two concurrent threads wish to create a record, it is guaranteed that each record will get a new unique record id. It is possible that the record id is that of a previously deleted record. Each unique client has its own instance of the server and each server instance has a unique instance of the data access class “Data.java”. Only the static keys (lock cookies) and the static lock map need serialized access. No method has been synchronized as a whole, only object access is synchronized where needed.

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


