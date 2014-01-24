package com.catalystitservices.priceitdroid.fragments.display;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.utils.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment{
	
	public static final String DATE_PICKER_INITIAL_VALUE = "com.catalystitservices.priceitdroid.date_picker_initial";
	public static final String DATE_PICKER_STORED_VALUE = "com.catalystitservices.priceitdroid.date_picker_stored";

	private Date date;
	private int year;
	private int month;
	private int day;

	/** Use this method whenever you want a new date picker dialog.
	 *  You must include a date object to initialize the values of the spinners.
	*/
	public static DatePickerFragment newInstance(Date date) {
		 Bundle args = new Bundle();
		 args.putSerializable(DATE_PICKER_INITIAL_VALUE, date);
		 DatePickerFragment fragment = new DatePickerFragment();
		 fragment.setArguments(args);
		 return fragment;
	}
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	
    	View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_date_picker, null);
	
    	date = (Date) getArguments().getSerializable(DATE_PICKER_INITIAL_VALUE);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	year = calendar.get(Calendar.YEAR);
    	month = calendar.get(Calendar.MONTH);
    	day = calendar.get(Calendar.DAY_OF_MONTH);
    	
    	DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
    	
    	datePicker.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int month,
					int day) {
				// TODO Auto-generated method stub
				date = new GregorianCalendar(year, month, day).getTime();
				getArguments().putSerializable(DATE_PICKER_STORED_VALUE, date);
			}
		});
    	
    	
    	
    	return new AlertDialog.Builder(getActivity())
    		.setTitle(getTitle())
    		.setView(view)
    		.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int which) {
							sendResult(Activity.RESULT_OK);
    					}
    				})
    		.create();
    }

    /** This method will retrieve a string from resources to set as the dialog title,
     * based on the request code was added to the setTargetFragment().
     * @return int of an R-file string
     */
    private int getTitle() {
       	if (getTargetRequestCode() == Constants.SELECT_DATE_REQUEST_CODE){
    		return R.string.date_picker_title_purchase_date;
    	} else if (getTargetRequestCode() == Constants.SELECT_END_DATE_REQUEST_CODE){
    		return R.string.date_picker_title_purchase_date;
    	} else {
    		return android.R.string.untitled;
    	}
	}

	private void sendResult(int resultCode) {
    	 if (getTargetFragment() == null){
    		 return;
    	 }
    	 
    	 Intent intent = new Intent();
    	 intent.putExtra(DATE_PICKER_STORED_VALUE, date);
    	 getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    	 
	}
}
