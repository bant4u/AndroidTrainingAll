package com.at.androidtraining.webservices;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.at.androidtraining.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class JsonParserActivity extends ListActivity {

    private static String url = "http://docs.blackberry.com/sampledata.json";
 
    private static final String VTYPE = "vehicleType";
    private static final String VCOLOR = "vehicleColor";
    private static final String FUEL = "fuel";
    private static final String TREAD = "treadType";


    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
    
    ListView lv ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_parser);
		
		new ProgressTask(JsonParserActivity.this).execute();
		
	}
	
	 private class ProgressTask extends AsyncTask<String, Void, Boolean> {
	        private ProgressDialog dialog;
	 
//	        private ListActivity activity;
	 
	        // private List<Message> messages;
	        public ProgressTask(ListActivity activity) {
//	            this.activity = activity;
	            context = activity;
	            dialog = new ProgressDialog(context);
	        }
	 
	        private Context context;
	 
	        protected void onPreExecute() {
	            this.dialog.setMessage("Progress start");
	            this.dialog.show();
	        }
	 
	        @Override
	        protected void onPostExecute(final Boolean success) {
	            if (dialog.isShowing()) {
	                dialog.dismiss();
	            }
	            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
	                    R.layout.list_item, new String[] { VTYPE, VCOLOR,
	                            FUEL, TREAD }, new int[] {
	                            R.id.vehicleType, R.id.vehicleColor, R.id.fuel,
	                            R.id.treadType });
	 
	            setListAdapter(adapter);
	 
	            // select single ListView item
	             lv = getListView();
	        }
	 
	        protected Boolean doInBackground(final String... args) {
	 
	            JSONParser jParser = new JSONParser();
	 
	            // get JSON data from URL
	            JSONArray json = jParser.getJSONFromUrl(url);
	 
	            for (int i = 0; i < json.length(); i++) {
	 
	                try {
	                    JSONObject c = json.getJSONObject(i);
	                    String vtype = c.getString(VTYPE);
	 
	                    String vcolor = c.getString(VCOLOR);
	                    String vfuel = c.getString(FUEL);
	                    String vtread = c.getString(TREAD);
	 
	                    HashMap<String, String> map = new HashMap<String, String>();
	 
	                    // Add child node to HashMap key & value
	                    map.put(VTYPE, vtype);
	                    map.put(VCOLOR, vcolor);
	                    map.put(FUEL, vfuel);
	                    map.put(TREAD, vtread);
	                    jsonlist.add(map);
	                }
	                catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            }
	            return null;
	        }
	    }

}
