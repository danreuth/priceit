package com.catalystitservices.priceitdroid;



import com.catalystitservices.priceitdroid.fragments.display.DisplayProductsFragment;




import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;



public class EditProductActivity extends  AbstractSingleFragmentActivity {

	FragmentManager fm;
	@Override  	
	public void onCreate(Bundle onSavedInstanceState){
		super.onCreate(onSavedInstanceState);
		
		setContentView(R.layout.activity_abstract_single_fragment_holder);
		if(fm == null){
			fm = getFragmentManager();
			Fragment fragmentHolder = fm.findFragmentById(R.id.fragmentHolder);
			
			if(fragmentHolder == null){
				fragmentHolder = createFragment();
				fm.beginTransaction()
				.add(R.id.fragmentHolder, fragmentHolder)			
				.commit();
				
			}
		}
		
	
		
	}
	@Override
	protected Fragment createFragment() {
		return new DisplayProductsFragment();
	}


}
