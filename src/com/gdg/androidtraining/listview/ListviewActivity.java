package com.gdg.androidtraining.listview;

import com.gdg.androidtraining.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListviewActivity extends Activity {

	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
        listView = (ListView) findViewById(R.id.list);
        String[] values = new String[] { "Semicolon Developers", 
                                         "AppsJhola",
                                         "Online Baghchal",
                                         "Picovico", 
                                         "Birthday Forest", 
                                         "Yellow Nepal", 
                                         "JokeSanjal"
                                        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter); 
        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		String  itemValue = (String) listView.getItemAtPosition(position);
        		Toast.makeText(getApplicationContext(),"Position :" + position + "  ListItem : " + itemValue , Toast.LENGTH_LONG).show();
        	}
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listview, menu);
		return true;
	}
}
