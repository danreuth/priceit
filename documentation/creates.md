#Creates

Template:

1. Have a specific Create Controller for each create fragment (can only do one thing in a fragment)

2. The create controller sends the data off to an error checking class, the error checking class will parse the strings to their type-safe type

2. The Create Controller maintains a reference to its Create URL, which is stored as a map or enum or something somewhere

2. the create controller also maintains a reference to the model it's supposed to create

2. When the data returns valid from error checking, construct a new instance of the model and map the values into their fields

3. Take that java object and send it out to be serialized as a JSON object.

4. at this point you should have both the Java representation and the JSON in memory

5. The Create Controller will then send the location and payload to a network controller

#Network Controller

1. the network controller looks up the URL in the map/enum/whatever and finds what action to take, and does that action.

2. at some point there's a return object that needs some kind of action