package com.catalystitservices.priceitdroid.services.networking;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;

public class SpiceService extends JacksonSpringAndroidSpiceService {
	
	@Override
	public int getThreadCount(){
		return 2;
	}

}
