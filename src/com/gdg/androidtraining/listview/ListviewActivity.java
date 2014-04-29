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
		
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        // Defined Array values to show in ListView
        String[] values = new String[] { "Apple", 
                                         "Google",
                                         "Microsoft",
                                         "Amazon", 
                                         "Air bnb", 
                                         "Twitter", 
                                         "Facebook", 
                                         "buffer" 
                                        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
      
      
              // Assign adapter to ListView
              listView.setAdapter(adapter); 
              
              // ListView Item Click Listener
              listView.setOnItemClickListener(new OnItemClickListener() {
   
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                       int position, long id) {
                     String  itemValue    = (String) listView.getItemAtPosition(position);
                      Toast.makeText(getApplicationContext(),
                        "Position :" + position + "  ListItem : " + itemValue , Toast.LENGTH_LONG)
                        .show();
                   
                    }
      
               }); 
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listview, menu);
		return true;
	}

}
