package com.catalystitservices.priceitdroid.utils;



import modellists.GetModelList;

import org.springframework.web.client.HttpServerErrorException;


import modellists.GetModelList;


import android.widget.Toast;

import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class GetRoboSpiceRequestListener implements RequestListener<GetModelList> {
	private SpiceManagerFragment currentContext;
	public GetRoboSpiceRequestListener(SpiceManagerFragment currentContext){
		this.currentContext = currentContext;
	}

	  @Override
	  public void onRequestFailure(SpiceException spiceException){
		  if(spiceException.getCause() instanceof HttpServerErrorException){
			  HttpServerErrorException exception = (HttpServerErrorException)spiceException.getCause();
			  String errorCode = exception.getResponseBodyAsString();
			  currentContext.errorSomeThang(errorCode);
		  }
		  else {
			  Toast.makeText( currentContext.getActivity(), spiceException.getCause().toString(), Toast.LENGTH_SHORT ).show();
		  }
	  }
	
	  @Override
	  public void onRequestSuccess(GetModelList result){
		  Toast.makeText( currentContext.getActivity(), "Network success", Toast.LENGTH_SHORT ).show();
		  currentContext.doSomeThang(result);
	  }




}
