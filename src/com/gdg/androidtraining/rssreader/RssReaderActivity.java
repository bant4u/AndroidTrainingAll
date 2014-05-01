package com.gdg.androidtraining.rssreader;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.gdg.androidtraining.R;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class RssReaderActivity extends ListActivity {

	// Flag for current page
	int current_page = 1;
	static final String KEY_ITEM = "item"; // parent node
	//static final String KEY_ITEM = "entry"; // parent node
	//static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	//static final String KEY_LINK = "link";
	//static final String KEY_DATE = "published";
	static final String KEY_DATE = "pubDate";
	//static final String KEY_NAME = "name";
	//static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";
	//static final String KEY_DESC = "content";
	static final String KEY_CONTENT = "content:encoded";
	//Button btnLoadMore;
	//private final String url = Constants.URL_LIST_POSTS;
	ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

	//ArrayList<HashMap<String, String>> myList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.listplaceholder);
		if(isNetworkConnected()){
			//Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();
			//retrieveNewsPosts();
			new AsyncRequest().execute();
		}
		else{
			Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
		}
		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String title = menuItems.get(position).get(KEY_TITLE);
				//String date = menuItems.get(position).get(KEY_DATE);
				String date = menuItems.get(position).get(KEY_DATE);
				String description = menuItems.get(position).get(KEY_CONTENT);
				//String title = menuItems.get(position).get(KEY_TITLE);
				//String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				//String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
				//String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();
				
				//Starting new intent
				Intent in = new Intent(getApplicationContext(), DetailsActivity.class);
				in.putExtra(KEY_TITLE, title);
				in.putExtra(KEY_DATE, date);
				in.putExtra(KEY_CONTENT, description);
				startActivity(in);

			}
		});
	}
	public boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
	// retrieve News Post lists
	@Override
	public Dialog onCreateDialog(int id) {
	            ProgressDialog dialog = new ProgressDialog(this);
	            dialog.setMessage("Please wait...");
	            dialog.setIndeterminate(true);
	            //dialog.setCancelable(false);
	            return dialog;
	    
	}
	public class AsyncRequest extends AsyncTask<Void, Void, String>{
		XMLParser parser = new XMLParser();
		@SuppressWarnings("deprecation")
		@Override
	    protected void onPreExecute() {
			showDialog(2);
	    }
		@Override
		protected String doInBackground(Void... arg0) {
			return parser.getXmlFromUrl("http://blog.ktmgtug.org/feeds/posts/default?alt=rss"); // getting XML
//			return parser.getXmlFromUrl("http://hereshem.blogspot.com/feeds/posts/default?alt=rss"); // getting XML
		}
		@SuppressWarnings("deprecation")
		@Override
	    protected void onPostExecute(String xml) {
			dismissDialog(2);
			Log.d("Feed",xml);
			
			Document doc = parser.getDomElement(xml); // getting DOM element

			NodeList nl = doc.getElementsByTagName(KEY_ITEM);
			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				//map.put(KEY_ID, parser.getValue(e, KEY_ID));
				//map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
				//map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
				map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
				//map.put(KEY_LINK, parser.getValue(e, KEY_LINK));
				map.put(KEY_DATE, parser.getValue(e, KEY_DATE).toString().substring(0, 26));
				//map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
				try{
					map.put(KEY_DESC, Html.fromHtml(parser.getValue(e, KEY_DESC)).toString().substring(0, 50)+ "...");
				}catch(Exception ex){}
				map.put(KEY_CONTENT, parser.getValue(e, KEY_DESC));
				// adding HashList to ArrayList
				menuItems.add(map);
			}

			// Adding menuItems to ListView
			ListAdapter adapter = new SimpleAdapter(RssReaderActivity.this, menuItems,
					R.layout.list_item,
					new String[] { KEY_TITLE, KEY_DATE, KEY_DESC}, new int[] {
							R.id.title, R.id.date, R.id.desc });

			setListAdapter(adapter);
		}
	}	
}
