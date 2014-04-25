package com.at.androidtraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.at.androidtraining.database.DatabaseActivity;
import com.at.androidtraining.listview.ListviewActivity;
import com.at.androidtraining.phonecall.CallActivity;
import com.at.androidtraining.sharedpreferences.SharedPrefsActivity;
import com.at.androidtraining.styling.StylingActivity;
import com.at.androidtraining.webservices.JsonParserActivity;
import com.at.androidtraining.webview.WebviewActivity;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnListview, btnStyling, btnDatabase, btnSharedPrefs, btnWebview, btnWebServices, btnCall;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnListview = (Button) findViewById(R.id.btn_listview);
		btnStyling = (Button) findViewById(R.id.btn_styling);
		btnDatabase = (Button) findViewById(R.id.btn_database);
		btnSharedPrefs = (Button) findViewById(R.id.btn_shared_prefs);
		btnWebview = (Button) findViewById(R.id.btn_webview);
		btnWebServices = (Button) findViewById(R.id.btn_web_services);
		btnCall = (Button) findViewById(R.id.btn_call);
		
		btnListview.setOnClickListener(this);
		btnStyling.setOnClickListener(this);
		btnDatabase.setOnClickListener(this);
		btnSharedPrefs.setOnClickListener(this);
		btnWebview.setOnClickListener(this);
		btnWebServices.setOnClickListener(this);
		btnCall.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_listview:
			
			//Toast.makeText(getApplicationContext(), "LISTVIEW clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), ListviewActivity.class));
			
			break;
			
		case R.id.btn_styling:
			
			//Toast.makeText(getApplicationContext(), "STYLING clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), StylingActivity.class));
			
			break;
			
		case R.id.btn_database:
	
			//Toast.makeText(getApplicationContext(), "DATABASE clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), DatabaseActivity.class));
			
			break;
	
		case R.id.btn_shared_prefs:
			
			//Toast.makeText(getApplicationContext(), "SHARED PREFERENCES clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), SharedPrefsActivity.class));
			
			break;
			
		case R.id.btn_webview:
			
			//Toast.makeText(getApplicationContext(), "WEBVIEW clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), WebviewActivity.class));
			
			break;
			
		case R.id.btn_web_services:
			
			//Toast.makeText(getApplicationContext(), "WEB SERVICES clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), JsonParserActivity.class));
			
			break;
			
		case R.id.btn_call:
			
			//Toast.makeText(getApplicationContext(), "WEB SERVICES clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), CallActivity.class));
			
			break;

		default:
			break;
		}
		
	}

}
