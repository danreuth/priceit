##Conventions for Database Model Classes

* fields are **private**

* fields are of the type on the database, not strings like from the UI

* the generic constructor is private

* all incoming data for the constructors is pre-checked by the verifier class

* ~~one `public static` method is named like a constructor that takes in the associated JSON object as an argument/parameter, and returns the appropriate class object~~

* another constructor that takes in type safe fields, pre-parsed by the verifier class

* fields in a class (members) are prefixed with a lowercase `m`

* potentially no getters or setters, new objects are created for edits, creates, etc

