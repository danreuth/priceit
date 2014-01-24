package com.catalystitservices.priceitdroid.fragments.display;

import java.util.ArrayList;


import com.catalystitservices.priceitdroid.R;



import android.app.ListFragment;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DisplayAddressListFragment extends ListFragment {

	public static final String EXTRA_ADDRESS_LIST = "com.catalystitservices.priceitdroid.address_list";
	
	private ArrayList<Address> mList;
	
	public static DisplayAddressListFragment newInstance(ArrayList<Address> list) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_ADDRESS_LIST, list);
		
		DisplayAddressListFragment fragment = new DisplayAddressListFragment();
		fragment.setArguments(bundle);
		return fragment;
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mList = (ArrayList<Address>)getArguments().getSerializable(EXTRA_ADDRESS_LIST);
		AddressAdapter adapter = new AddressAdapter(mList);
		setListAdapter(adapter);
	}
	
	private class AddressAdapter extends ArrayAdapter<Address> {
		
		public AddressAdapter(ArrayList<Address> addresses) {
			super(getActivity(),0, addresses);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_address, null);
			}
			
			TextView streetTextView = (TextView)convertView.findViewById(R.id.address_list_street);
			streetTextView.setText(getItem(position).getAddressLine(0));
			return convertView;
		}
		
		
	}
}
