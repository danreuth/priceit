package com.catalystitservices.priceitdroid.fragments;



import modellists.GetModelList;


import com.catalystitservices.priceitdroid.services.networking.SpiceService;

import modellists.ManufacturerList;




import com.octo.android.robospice.SpiceManager;

import android.app.Fragment;
import android.os.Bundle;




import com.octo.android.robospice.JacksonSpringAndroidSpiceService;

public abstract  class SpiceManagerFragment extends Fragment{
	
    protected SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
	public void onStart() {
        spiceManager.start(getActivity());
        super.onStart();
    }

    @Override
	public void onStop() {
    	if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }
    
    protected SpiceManager getSpiceManager(){
		return spiceManager;
    }


    public  void doSomeThang(Object result){
    	
    }
    
    /** Error codes coming back from a NetworkThreader will call this method, 
     *  passing in their error codes. Pop some toast or something on the error string.
     * 
     * @param errorCode
     */

    public abstract void errorSomeThang(String errorCode);

   // protected abstract void performRequest(Object ob);

   

	


    
    /** When the NetworkThreader receives a 200 from the server for a GET it will call this method.
     *  The result is an extension of ArrayList and contains a LinkedHashMap of serialized objects 
     *  from the server. Typically we're using this to grab objects from the server, and then 
     *  deserializing them.
     * 
     * @param result
     */
	public abstract void doSomeThang(GetModelList result);
	
	/** The network response for a successful POST will call this method. The result is just
	 *  a generic object for now until we know what we'll be getting from the server after a 
	 *  successful POST.
	 * 
	 * @param result
	 */
	public abstract void feedbackSomeThang(Object result);



	public void refreshPage(){};

    
//    protected abstract class RoboSpiceFragmentRequestListener implements RequestListener<Object> {
//        @Override
//        public abstract void onRequestFailure(SpiceException spiceException); 
//
//        @Override
//        public abstract void onRequestSuccess(Object result);
//    }

}
