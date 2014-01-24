package com.catalystitservices.priceitdroid.utils;

import org.springframework.web.client.HttpServerErrorException;

import android.widget.Toast;

import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class DeleteRequestListener implements RequestListener<Void> {
	private SpiceManagerFragment currentContext;
	public DeleteRequestListener(SpiceManagerFragment currentContext){
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
	  //result is void!
	  @Override
	  public void onRequestSuccess(Void result){
		  Toast.makeText( currentContext.getActivity(), "Deletion success", Toast.LENGTH_SHORT ).show();
		  currentContext.refreshPage();
	  }

}
