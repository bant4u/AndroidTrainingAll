package com.gdg.androidtraining.rssreader;

import java.util.List;

import com.gdg.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Main application activity.
 * 
 * @author ITCuties
 *
 */
public class RssReaderActivity extends Activity {
	ArrayAdapter<RssItem> adapter;
	/** 
	 * This method creates main application view
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_listview);

		RssReader rssReader = new RssReader("http://hemshrestha.com.np/feed");
		try {
			// Create RSS reader
//			RssReader rssReader = new RssReader("http://www.itcuties.com/feed/");
			// Get a ListView from main view
			Log.i("RssActiviity", "initialized");
			ListView itcItems = (ListView) findViewById(R.id.list);
			
			List<RssItem> rssItem = rssReader.getItems();
			// Create a list adapter
			adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssItem);
			// Set list adapter for the ListView
			itcItems.setAdapter(adapter);
			
			// Set list view item click listener
			itcItems.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					RssItem item = adapter.getItem(arg2);
							
					Toast.makeText(getApplicationContext(), "Title is " + item.getTitle(), Toast.LENGTH_SHORT).show();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(item.getLink()));
					RssReaderActivity.this.startActivity(i);
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}