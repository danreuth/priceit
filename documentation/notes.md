#misc notes

notes:


classes

each major object type (manufacturer, product, list, etc) should have its own concrete class implementation.

those concrete classes

Interfaces > Abstracts > Concretes

#Create

Create View asks for a Create Fragment.

Fragment has text fields, with restrictions based on what kind of information they're taking in.

Some sort of magic happens when that fragment is submitted or posted to the server:

First, a Bundle Controller takes in the text fields and stores them in a datatype that is compatible with Java's JSON library.

Second, data, with it's field type (email, name, etc) from the bundle is then passed to a Pre Checker. The pre checker does our form checking and whatever, and then returns a boolen about that field, either it passed True, or it did not False. We're NOT making database queries from the prechecker

Next, if the data has passed the pre-checker, we send that data to a JSON factory or library or whatever that builds the JSON object/string and returns it to the bundle controller.

Then the bundle controller takes that JSON string and passes it to a Router.

That router then attempts to connect to the known address of the database. It also handles responses and passes those back to the controller.

***

fields have both text views and hints