 	package com.catalystitservices.priceitdroid.utils;




import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;

import com.octo.android.robospice.SpiceManager;


public class NetworkThreader {
	private SpiceManager spiceManager;
	private PostSpiceRequest postSpiceRequest;
	private SpiceManagerFragment currentContext;
	private Object mObject;
	private String mUrl;
	private GetSpiceRequest getSpiceRequest;
	private PutSpiceRequest putSpiceRequest;
	private DeleteSpiceRequest deleteSpiceRequest;
	
	public NetworkThreader(String url, SpiceManager spiceManager, SpiceManagerFragment currentContext, Object mObject){
		this.mUrl = url;
		this.mObject = mObject;
		this.spiceManager = spiceManager;
		this.currentContext = currentContext;
	}
	
	public void postObjectToServer(String key){
		postSpiceRequest = new PostSpiceRequest(mObject, mUrl);

		spiceManager.execute(postSpiceRequest, key, NetworkingConstants.ONE_MIN_IN_MILL, new PostRoboSpiceRequestListener(currentContext));

	}

	public void putObjectToServer(){
		putSpiceRequest = new PutSpiceRequest(mObject, mUrl);
		spiceManager.execute(putSpiceRequest, "put", NetworkingConstants.ONE_MIN_IN_MILL, new PutRequestListener(currentContext));
	}

	public void getObjectFromServer(String key){
		getSpiceRequest  = new GetSpiceRequest(mObject, mUrl);
		spiceManager.execute(getSpiceRequest, key, NetworkingConstants.ONE_MIN_IN_MILL, new GetRoboSpiceRequestListener(currentContext));
	}
	public void deleteObjectFromServer() {
		deleteSpiceRequest = new DeleteSpiceRequest(mObject, mUrl);
		spiceManager.execute(deleteSpiceRequest, "delete", NetworkingConstants.ONE_MIN_IN_MILL, new DeleteRequestListener(currentContext));
	}
}
