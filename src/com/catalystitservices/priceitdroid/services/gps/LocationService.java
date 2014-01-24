package com.catalystitservices.priceitdroid.services.gps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.provider.Settings;

import android.util.Log;

public class LocationService implements LocationListener {
	// Tag for logging
	private static final String TAG = "LocationService";
	
	// Constant parameters for location updates 
	private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10.0f; // meters
	private static final int MIN_TIME_BETWEEN_UPDATES = 3000; // 30 seconds
	
	// Constant time span for evaluating better location fixes
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	
	private Context mContext;
	private LocationManager mLocationManager;
	private Location mLocation;
	
	// Location hardware availability flags
	private boolean isGPSEnabled = false;
	private boolean isNetworkEnabled = false;
	private boolean canGetLocation = false;
		
	public LocationService(Context context) {
		mContext = context;
		mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
	}
	
	// Gets the first location and starts listening for updates. This should typically be called 
	// in the onStart method of the activity or fragment
	public void startLocationService() {
		canGetLocation = false;
		isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		if(!isGPSEnabled && !isNetworkEnabled) {
			// Neither location service is enabled so do nothing
		} else {
			// Sets up listeners on any provider that is enabled and gets the last know location
			// This location could be very stale, best to not use it and get an update.
			canGetLocation = true;
			if(isNetworkEnabled) {
				mLocationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BETWEEN_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
				Log.d(TAG, "Network Provider Enabled");
				mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
			if(isGPSEnabled) {
				mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BETWEEN_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
				Log.d(TAG, "GPS Provider Enabled");
				mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}
		}
	}
	
	// Stops listening for update.  Important to call this or location services will not shut down
	// and it will cause battery drain.
	public void stopLocationService() {
		mLocationManager.removeUpdates(this);
	}
	
	// Check to see if location services are active. 
	public boolean canGetLocation() {
		return canGetLocation;
	}

	// Gets the current location.  Call canGetLocation before you call this to make sure the system is active.
	// This location is updated automatically by the listeners.
	public Location getLocation() {
		return mLocation;
	}

	// Displays a dialog asking to turn on location services and takes user to preferences.  
	public void showSettingsAlert(){
		Log.d(TAG, "In Show settings");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
      
        
        alertDialog.setTitle("GPS is settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
  
        
  
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
  
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
    }
	
	// Compares two locations returning true if the first is "better" than the second
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
	    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}

	//Checks whether two providers are the same 
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}
	
	// Is called when the location updates
	@Override
	public void onLocationChanged(Location location) {
		if(isBetterLocation(location, mLocation)) {
			mLocation = location;
		}
	}
	
	// These are not used.  Required methods from implementing LocationListenr
	@Override
	public void onProviderDisabled(String provider) {
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
}
