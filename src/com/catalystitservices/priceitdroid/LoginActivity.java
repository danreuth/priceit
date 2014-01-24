package com.catalystitservices.priceitdroid;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


import com.catalystitservices.priceitdroid.utils.Constants;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;

public class LoginActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, OnClickListener {

	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private ProgressDialog mConnectionProgressDialog;
    private PlusClient mPlusClient;
    private ConnectionResult mConnectionResult;
    private Button mLogin;
	//private Button mRegister;
	private String mToken;
	    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_login);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
        mPlusClient = new PlusClient.Builder(this, this, this)
                .setActions("http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity")
                .setScopes(Scopes.PLUS_LOGIN)  // recommended login scope for social features
                // .setScopes("profile")       // alternative basic login scope
                .build();
        
        findViewById(R.id.sign_in_button).setOnClickListener(this);
		        
        mLogin = (Button) findViewById(R.id.login_submit_button);
        mLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainMenu = new Intent(getApplicationContext(), MainMenuActivity.class);
				startActivity(mainMenu);
			}
		});
        
      
        // Progress bar to be displayed if the connection failure is not resolved.
        mConnectionProgressDialog = new ProgressDialog(this);
        mConnectionProgressDialog.setMessage("Signing in...");
	}
	
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.sign_in_button && !mPlusClient.isConnected()) {
	        if (mConnectionResult == null) {
	            mConnectionProgressDialog.show();
	        } else {
	            try {
	                mConnectionResult.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
	            } catch (SendIntentException e) {
	                // Try connecting again.
	                mConnectionResult = null;
	                mPlusClient.connect();
	            }
	        }
	    }
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mPlusClient.connect();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mPlusClient.disconnect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		 if (mConnectionProgressDialog.isShowing()) {
             // The user clicked the sign-in button already. Start to resolve
             // connection errors. Wait until onConnected() to dismiss the
             // connection dialog.
             if (result.hasResolution()) {
                     try {
                             result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
                     } catch (SendIntentException e) {
                             mPlusClient.connect();
                     }
             }
     }

     // Save the intent so that we can start an activity when the user clicks
     // the sign-in button.
     mConnectionResult = result;
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// We've resolved any connection errors.
		  mConnectionProgressDialog.dismiss();
		String accountName = mPlusClient.getAccountName();
		
		getAndUseAuthTokenInAsyncTask();
		
        Toast.makeText(this, accountName + " is connected.", Toast.LENGTH_LONG).show();
		
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent data) {
		if (requestCode == REQUEST_CODE_RESOLVE_ERR && responseCode == RESULT_OK) {
	        mConnectionResult = null;
	        mPlusClient.connect();
	    }
	}

	 void getAndUseAuthTokenInAsyncTask() {
	       AsyncTask task = new AsyncTask() {

			@Override
			protected Object doInBackground(Object... arg0) {
				mToken = getAuthToken();
				return null;
			}

			@Override
			protected void onPostExecute(Object result) {
				Intent register = new Intent(getApplicationContext(), CreateUserActivity.class);
				// TODO  Add extra
				register.putExtra(Constants.AUTH_TOKEN, mToken);
				startActivity(register);
			}
	         
	       };
	       task.execute((Void)null);
	   }
	 String getAuthToken() {
		 String scopes = "oauth2: " + Scopes.PLUS_LOGIN;
			String code = null;
			try {
			  code = GoogleAuthUtil.getToken(
			    this,                             // Context context
			    mPlusClient.getAccountName(),     // String accountName
			    scopes);
			  
			} catch (IOException transientEx) {
			  // network or server error, the call is expected to succeed if you try again later.
			  // Don't attempt to call again immediately - the request is likely to
			  // fail, you'll hit quotas or back-off.
				
			  return null;
			} catch (GoogleAuthException authEx) {
			  // Failure. The call is not expected to ever succeed so it should not be
			  // retried.
				
			  return null;
			} catch (Exception e) {
				
			  //throw new RuntimeException(e);
			}
			return code;
	 }
}
