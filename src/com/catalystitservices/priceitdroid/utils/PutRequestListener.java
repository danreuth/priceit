package com.catalystitservices.priceitdroid.utils;

import java.io.IOException;

import org.springframework.web.client.HttpServerErrorException;

import android.media.MediaPlayer;
import android.widget.Toast;

import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
//Object caught is null
public class PutRequestListener implements RequestListener<Void> {
	private SpiceManagerFragment currentContext;
	public PutRequestListener(SpiceManagerFragment currentContext){
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
		  Toast.makeText( currentContext.getActivity(), "Network success", Toast.LENGTH_SHORT ).show();

	  }

}
