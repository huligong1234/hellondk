package com.example.hellondk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView  textView01 = (TextView)findViewById(R.id.textView01);
		textView01.setText(NDKUtils.stringFromJNI());
	}
}
