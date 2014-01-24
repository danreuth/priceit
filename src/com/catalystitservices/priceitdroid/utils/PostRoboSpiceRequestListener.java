package com.catalystitservices.priceitdroid.utils;


import org.springframework.web.client.HttpServerErrorException;

import android.widget.Toast;

import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
//hey oh
//this is a test oh
public class PostRoboSpiceRequestListener implements RequestListener<Object> {
	private SpiceManagerFragment currentContext;
	public PostRoboSpiceRequestListener(SpiceManagerFragment currentContext){
		this.currentContext = currentContext;
	}
	  @Override
	  public void onRequestFailure(SpiceException spiceException){
		  if(spiceException.getCause() instanceof HttpServerErrorException){
			  HttpServerErrorException exception = (HttpServerErrorException)spiceException.getCause();
			  String errorCode = exception.getResponseBodyAsString();
			  errorCode = errorCode.substring(6);
			  currentContext.errorSomeThang(errorCode);
		  }
		  else {
			  Toast.makeText( currentContext.getActivity(), spiceException.getCause().toString(), Toast.LENGTH_SHORT ).show();
		  }
	  }
	//more changes here.. not!
	  //do somethang Curtis you %^#@*%#@&@&&%@&!
	  @Override
	  public void onRequestSuccess(Object result){
		  Toast.makeText( currentContext.getActivity(), "Network success", Toast.LENGTH_SHORT ).show();
		  currentContext.feedbackSomeThang(result);
	  }

}
