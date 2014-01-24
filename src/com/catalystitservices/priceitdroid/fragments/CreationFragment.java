package com.catalystitservices.priceitdroid.fragments;

import android.view.View;

public interface CreationFragment {

	public View onCreateView();
	
	public void onActivityResult();
	
	public boolean formIsValid();
	
}
