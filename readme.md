#PriceIT Android

*The new shopping.*

PriceIT Android will allow users to share, track, and compare prices across various stores.

Planned features include creating shopping lists, saving favorite products, sharing lists with other users, and finding the best prices for a product nearest to the user's location.

PriceIT will use crowdsourcing to create an up-to-date database of prices. Using the bundled barcode scanner, and the planned geolocation services, PriceIT users will store a product, its location, and its price in the system as a Price Point.

##Project Structure

**Project has no build automation tools. THIS IS MOSTLY OUR FAULT AND WE RECOMMEND GRADLE.**

We built the project with a mix of the standalone Android Developer Tools and the Eclipse Android plugin. We make no guarantees that our project won't blow up in other IDEs.

* [Android Developer Tools website](http://developer.android.com/sdk/index.html)

###GenyMotion

* Make an account and download the client from [their site](http://www.genymotion.com/)

* If GenyMotion is slow, get virtualbox [directly](https://www.virtualbox.org/) from Oracle.

*TO USE: Start genymotion and create a new virtual device. The device will be read as an external device on the machine. You can start your app from Eclipse ADT by running the project as an Android Application and selecting the correct device.*

###External Libraries

**AGAIN, PROJECT HAS NO BUILD AUTOMATION TOOLS. No external libraries are stored in the repository. Download and place all JARS directly into the lib folder of the project.**

####ZXing

* Used for the scanner feature to read bar codes.

* Use in PriceIT requires the ZXing Team's [barcode app](https://play.google.com/store/apps/details?id=com.google.zxing.client.android&hl=en)

* Project Github: https://github.com/zxing/zxing

####Robospice

There are no tutorials for Robospice, and documentation is far and few between. Read up on code examples and do what you can with our source.

* General Information: https://github.com/octo-online/robospice

* Jars: https://github.com/octo-online/robospice/wiki/Setup-for-Non-Maven-users

* You'll need the Robospice Core jars as well as the Android Spring module jars to get the project going.

* Used for HTTP requests GET, POST, PUT, and DELETE.

####Google Play Services

* Google Playserices is what allows Oauth to run and do its magic. Without it, the project will fill with errors.

* [Get started here.](https://developers.google.com/+/mobile/android/getting-started)

* [Get GenyMotion to work with Google Play Services](http://www.tushroy.com/2013/12/installing-google-play-services-on-genymotion-2-0.html)

##Contributing
###Android Team Five
* Curtis *Pink Freud* McAllister
* Shane *Seahawk* Stuart
* Dat *Mr. Tea* Nguyen
* Dan *Mario Selleck* Reuther
* Christopher *Zoidberg* Layton
* Michael *Sith Lord* Miller

<img src="./theteam.png" alt="Android Team Five" title="Don't fall into the phone gap." style="width:100%;" />