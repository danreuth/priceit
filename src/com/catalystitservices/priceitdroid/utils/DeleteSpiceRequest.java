package com.catalystitservices.priceitdroid.utils;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class DeleteSpiceRequest extends SpringAndroidSpiceRequest<Void> 
{
    /**
     * The jackson json parser
     */
    private static Object mObject;
    private static String mUrl;
 
    public DeleteSpiceRequest(Object mObject, String mUrl){
       super(Void.class);
        this.mObject = mObject;
        this.mUrl = mUrl;
    }
    @Override
    public final Void loadDataFromNetwork() throws Exception{
	    getRestTemplate().delete(mUrl);
		return null;
    }
}