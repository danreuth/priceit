package com.catalystitservices.priceitdroid.utils;

import android.text.TextUtils;
import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import java.io.IOException;

import modellists.GetModelList;

/**
 * Created with Intellij with Android, BIZZBY product. See licencing for usage
 * of this code.
 * <p/>
 * User: chris Date: 10/01/2013 Time: 15:12
 */

public class GetSpiceRequest extends SpringAndroidSpiceRequest<GetModelList> {

	/**
	 * The jackson json parser
	 */
	private static Object mObject;
	private static String mUrl;

	public GetSpiceRequest(Object mObject, String mUrl) {

		super(GetModelList.class);

		this.mObject = mObject;
		this.mUrl = mUrl;
	}

	@Override
	public final GetModelList loadDataFromNetwork() throws Exception {
		return getRestTemplate().getForObject(mUrl, GetModelList.class);

	}

}