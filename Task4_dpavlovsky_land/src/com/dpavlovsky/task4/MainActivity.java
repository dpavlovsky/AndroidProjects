package com.dpavlovsky.task4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private EditText descr;
	private Spinner spinner;
	private RadioButton rb_d;
	private RadioButton rb_b;
	private CheckBox cb;
	private Button addBut;
	private ListView mainListView;
	
	private FuelRec mFuelRec;
	private ArrayList<FuelRec> mFuelRecList;
	private ArrayList<String>  mFuelRecListStr;
	
	private ArrayAdapter<String> mAdFuelRec;
	
	private Animation myAnim;
	
	private Activity self;

	private void initComp() {
		descr = (EditText) findViewById(R.id.descr);
		spinner = (Spinner) findViewById(R.id.spinner);
		rb_d = (RadioButton) findViewById(R.id.radio_diesel);
		rb_b = (RadioButton) findViewById(R.id.radio_benzin);
		cb = (CheckBox) findViewById(R.id.cbFullTank);
		addBut = (Button) findViewById(R.id.add);
		mainListView = (ListView) findViewById(R.id.mainListView);
		
		mFuelRecList 	= new ArrayList<FuelRec>();
		mFuelRecListStr = new ArrayList<String>();
		mAdFuelRec 		= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mFuelRecListStr);
		mainListView.setAdapter(mAdFuelRec);	
		
		myAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		
		self = this;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		
		
//		if (savedInstanceState==null) {
			initComp();
//		}
			
		
		addBut.startAnimation(myAnim);
		
//		Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "123456789"));
//		startActivity(dialIntent);
		
//		PowerManager manager = (PowerManager) getSystemService(POWER_SERVICE);
//		Log.i("TEST", manager.isScreenOn() + "");
		
//		Uri address = Uri.parse("http://google.com");
//		Intent openlink = new Intent(Intent.ACTION_VIEW,  address);
//		startActivity(openlink);
			
		Resources res = getResources();
		String songsFound = res.getQuantityString(R.plurals.my_plural, 1, 1);
		
		descr.setText(songsFound);
				
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.volume, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		addBut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				switch (v.getId()) {
				case R.id.add:
					mFuelRec = new FuelRec();

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy.MM.dd HH:mm:ss");
					String currentDateandTime = sdf.format(new Date());

					String typ = "";
					if (rb_d.isChecked()) {
						typ = "D";
					} else if (rb_b.isChecked()) {
						typ = "B";
					}
					
					String full = "";
					
					if (cb.isChecked()) {
						full = "F";
					} else {
						full = "N";
					}

					mFuelRec.set(currentDateandTime, full, typ, descr
							.getText().toString(), spinner.getSelectedItem()
							.toString());

					mFuelRecListStr.add(0, mFuelRec.toString());
					mFuelRecList.add(0, mFuelRec);					

					mAdFuelRec.notifyDataSetChanged();

					break;
				}

			}
		});

		OnItemClickListener itemListener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				
				mFuelRec = mFuelRecList.get(position);
				
//				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//				intent.putExtra("1", mFuelRec);
//				
//				startActivity(intent);
				
				Intent intent = new Intent("com.dpavlovsky.task4.test1");
				intent.putExtra("1", mFuelRec);
				
				self.startActivity(intent);
				
//				startActivity(intent);
			}
		};
		
		mainListView.setOnItemClickListener(itemListener);
		
	}
	
//	@Override
//	protected void onSaveInstanceState(Bundle savedInstanceState) {
//		
//		super.onSaveInstanceState(savedInstanceState);	
//		
//		savedInstanceState.putParcelableArrayList("2", mFuelRecList);
//		savedInstanceState.putStringArrayList("3", mFuelRecListStr);
//		
//		
//	}
	
//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState) {
//		super.onRestoreInstanceState(savedInstanceState);
//		
//		if (savedInstanceState!=null && savedInstanceState.containsKey("2") && savedInstanceState.containsKey("3")) {
//			mFuelRecList 		= savedInstanceState.getParcelableArrayList("2");
//			mFuelRecListStr 	= savedInstanceState.getStringArrayList("3");
//			
////			mAdFuelRec.notifyDataSetChanged();
//		}
//	}
}