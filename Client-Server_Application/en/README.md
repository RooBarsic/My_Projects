<h2> Client-Server application using Swing Java GUI </h2>

The application was developed consistently.

<h4> Stage One: Creating an object model - based on a fragment of the story. </h4>

The object model, from the story, includes - a university, a university library, a book, a book in the library, people, a person’s memory (memorized words and book titles).

In general, we had universities, libraries and people. In some universities there were libraries. People could borrow books from libraries, but for this they needed to know the key to the book (password from the book). People could bring their own books to libraries, and if books didn’t have a secret key, the library itself could give the book a key. Also, people could read and remember the contents of the book, the title of the book, the key to the book (for a while, as with human memory).

<h4> Stage Two: Handling artificial situations. </h4>

In this stage, we looked at various exceptions and processed them.

<h4> Stage Three: Console Application Using Collections. </h4>

At this stage, we create a console layout that stores books in the HashMap collection and performs various operations with the collection (the format for setting objects in commands is json). At the beginning of work, the program reads data about books from a file in CSV format, and at the end of work it saves the resulting collection to a file.

1) HashMap was used as a collection, and the objects stored in the collection were books.
2) The books had such parameters as: name, genre, maximum number of stored words, and the text of the book itself.

The application supports the following commands:

  1) clear: clear collection
  2) load: re-read the collection from file
  3) add_if_min {element}: add a new element to the collection if its value is less than the smallest element of this collection
  4) remove {String key}: remove item from collection by its key
  5) remove_lower {element}: remove all elements smaller than the specified one from the collection.
  6) save: save collection to file
  7) remove_greater {element}: remove from the collection all elements that exceed the specified
  
  <h4> Stage Four: Console client-server application. </h4>
  
  At this stage, we have divided the program from the third stage into client and server modules.
  
  The server module implements all functions of managing the collection online, except for displaying text in accordance with the plot of the subject area.
  
  The client module queries the server for the current state of the collection, sends requests to the server to execute various user commands, shows the results of the requests, can save books from the server to itself (locally) and load the saved (local) books to the server.
  
  Notes:

   1) The processing of objects in the collection is implemented using the Stream API using lambda expressions.
   2) Objects between the client and server are transmitted in serialized form.
   3) After receiving the request, the server creates a separate stream that generates and send a response to the client.
   4) The client handles server temporary unavailability.
   5) Data exchange between the client and the server is carried out via the UDP protocol.
   6) On the server side, datagrams are used and on the client side, a network channel.
   7) The used HashMap collection has been replaced by ConcurrentHashMap.

<h4> Stage Five: Creating a GUI. </h4>

At this stage, I wrote a graphical interface for the client and server parts.

The graphical interface for the server part - in the central part of the window displays the elements of the collection using JTree, implements the functions of adding, editing and deleting an object, as well as all other functions for managing the collection from previous work. And also the user is authorized with a password; operations of reading and saving a collection of objects are implemented as menu items; Object controls are located at the top of the window.

Graphic interface for the client part — displays in the window objects of the collection in the form of rectangles of the corresponding color, arranged according to their coordinates.

Notes for the client part:

   1) when you hover over an object, a tooltip appears with the name of the object.
   2) implemented filters for each characteristic of objects.
   3) when you click on the "Start" button, the objects whose characteristics correspond to the current values ​​of the filters disappear for 2 seconds, then return to their original state within 5 seconds.
   4) When you click on the "Stop" button, the animation stops.

Graphical interfaces are implemented using the Swing library.

<h4> Stage Six: Connecting the Database to Store Collection Objects </h4>

At this stage, PostgreSQL is used to store books instead of a collection. And also added features such as support in the client part - Russian, Dutch, Catalan and Spanish (Honduras) languages/locale. Provide correct display of numbers, date and time in accordance with the locale. Language switching occurs without restarting the application.

(our application interacts with PostgreSQL using JDBS drives )



