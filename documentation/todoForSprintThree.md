##Oauth

* login controller implementation

* third-party network call to Google

* database response handling

* token handling

* user registration on database

##CRUD

* update validation

* more specific error responses

prioritize:

1. Product
2. Manufacturer
3. User
4. Pricepoint
5. Store

###Creates

* product create needs database manaufacturer list

* finish Product, Store, User creates (mostly API here)

###Reads/Gits/Gets

* search the database by the model's composite key

* make a layout to display returns (list layout)

* read-only fields

* entries in the layout can be clicked and opened in an edit activity/fragment

###Updates/Edits

* do a get for that model or carry it over from a previous get

* use create fragments with logic to prepopulate the fields, maybe change submission to PUT instead of POST

##Testing

* robotium

* integrate testing project into main project

##Backburner

* get some good icons from http://thenounproject.com/

* build an about/credits page so we can attribute icons

* Begin adding landscape layouts to current functional views

* Business Owner's Voice: landing after login

* BOV: sound

* google maps, maybe like yelp's mapping style (sometime down the line)

* gradle