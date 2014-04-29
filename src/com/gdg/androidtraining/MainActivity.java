package com.gdg.androidtraining;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gdg.androidtraining.database.DatabaseActivity;
import com.gdg.androidtraining.listview.ListviewActivity;
import com.gdg.androidtraining.phonecall.CallActivity;
import com.gdg.androidtraining.rssreader.RssReaderActivity;
import com.gdg.androidtraining.sharedpreferences.SharedPrefsActivity;
import com.gdg.androidtraining.styling.StylingActivity;
import com.gdg.androidtraining.webservices.JsonParserActivity;
import com.gdg.androidtraining.webview.WebviewActivity;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnListview, btnStyling, btnDatabase, btnSharedPrefs, btnWebview, btnWebServices, btnCall, btnRss;
	
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
		btnRss = (Button) findViewById(R.id.btn_rss);
		
		btnListview.setOnClickListener(this);
		btnStyling.setOnClickListener(this);
		btnDatabase.setOnClickListener(this);
		btnSharedPrefs.setOnClickListener(this);
		btnWebview.setOnClickListener(this);
		btnWebServices.setOnClickListener(this);
		btnCall.setOnClickListener(this);
		btnRss.setOnClickListener(this);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String text = sharedPreferences.getString("storedName", "GDG Android Training");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(text);
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
//			startActivity(new Intent(getApplicationContext(), CustomListViewActivity.class));
			
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
			
		case R.id.btn_rss:
			
			//Toast.makeText(getApplicationContext(), "WEB SERVICES clicked", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(), RssReaderActivity.class));
			
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
