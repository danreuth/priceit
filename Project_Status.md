PriceIT Android Project Status
===

###Project Specs
Project specs are located in documentation directory in PriceIT_v1.8.docx file.
This project relies on a back end api that is also an in house project.

###Jira boards    
#####PriceIT Android
https://catalystit.atlassian.net/browse/CLASSDROID

PriceIT Android Project Status
===

###Project Specs
Project specs are located in documentation directory in PriceIT_v1.8.docx file.
This project relies on a back end api that is also an in house project.

###Jira boards    
#####PriceIT Android
https://catalystit.atlassian.net/browse/CLASSDROID

#####PriceIT API
https://catalystit.atlassian.net/browse/CLASSPAPI

###Working Features

* Create Product
* Create Store
* Create Manufacturer
* Edit Manufacturer
* Networking framework for gets, posts, puts and deletes 
* Edit Product
* Scanning UPC
* Delete Manufacturer 

###Partially Complete Features

* Oauth
  * Initial connection to Google is implemented.  A user can authorize their Google account. Retrieving the one time access token is implemented.  This token must be passed to the back end service and then a second token will be returned.  This token will be stored locally for future authentication.  This token exchanged has not been implemented.

* GPS
 * A basic location service class was implemented that is able to fetch the GPS coords from the phones hardware.  None of the creates actually use this GPS at this time.

* Edit Store
  * Selecting a store to edit and editing fields is implemented.  There is a business owner concern on whether editing stores should even be a feature, so it was shelved pending that decision. 

* Images
  * Either taking a picture with phones camera or extracting a picture from the file system is implemented.   The API had not defined how to handle images so no images are actually uploaded to the API.  

###Features to Implement

* Create PricePoint
* Create Shopping Lists
* Create Favorites List
* Implement authentication on api calls
* Handle landscape layouts and rotation.
* Optimize for different screen sizes
* Implement a more uniform style for the UI
* Radius search for products
* Find nearby products from same manufacturer
