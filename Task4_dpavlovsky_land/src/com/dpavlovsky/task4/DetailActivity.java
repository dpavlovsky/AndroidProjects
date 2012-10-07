package com.dpavlovsky.task4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	private TextView date;
	private TextView fuelType;
	private EditText descrLong;
	private TextView volume;
	private TextView fullText;	
		
	private void initComp() {
		date = (TextView) findViewById(R.id.date);
		fuelType = (TextView) findViewById(R.id.fuelType);
		descrLong = (EditText) findViewById(R.id.descrLong);
		volume = (TextView) findViewById(R.id.volume);
		fullText = (TextView) findViewById(R.id.full);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		initComp();		
			
		FuelRec pointsExtra =  getIntent().getParcelableExtra("1");
		
		String arg[] = new String[5]; 
		arg = pointsExtra.get();
		
		date.setText(arg[0]);
		if(arg[1].equals("F")) {
			fullText.setText(getString(R.string.fullFilled));			
		} else if(arg[1].equals("N")) {
			fullText.setText(getString(R.string.notFullFilled));
		}
		if(arg[2].equals("D")) {			
			fuelType.setText(getString(R.string.diesel));			
		} else if(arg[2].equals("B")) {
			fuelType.setText(getString(R.string.benzin));
		}
		descrLong.setText(arg[3]);
		volume.setText(arg[4]);
				
		
	}
}