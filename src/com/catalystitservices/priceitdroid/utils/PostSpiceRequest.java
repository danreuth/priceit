package com.catalystitservices.priceitdroid.utils;

import android.text.TextUtils;



import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
 

 
/**
 * Created with Intellij with Android, BIZZBY product.
 * See licencing for usage of this code.
 * <p/>
 * User: chris
 * Date: 10/01/2013
 * Time: 15:12
 */
public class PostSpiceRequest extends SpringAndroidSpiceRequest<Object> 
{
 
    /**
     * The jackson json parser
     */
    private static Object mObject;
    private static String mUrl;
 
    public PostSpiceRequest(Object mObject, String mUrl){
        super(Object.class);
        this.mObject = mObject;
        this.mUrl = mUrl;
    }
 
    @Override
    public final Object loadDataFromNetwork() throws Exception{
	    return getRestTemplate().postForObject(mUrl, mObject,Object.class);
    }
 
}