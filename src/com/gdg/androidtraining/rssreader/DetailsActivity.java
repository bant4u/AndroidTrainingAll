package com.gdg.androidtraining.rssreader;

import java.net.URLDecoder;

import com.gdg.androidtraining.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailsActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		try{
			String title = getIntent().getExtras().getString("title");
			String date = getIntent().getExtras().getString("pubDate");
			String desc = getIntent().getExtras().getString("content:encoded");
			TextView tv_title = (TextView) findViewById(R.id.title);
			TextView tv_date = (TextView) findViewById(R.id.date);
			//TextView tv_desc = (TextView) findViewById(R.id.desc);
			tv_title.setText(title);
			tv_date.setText(date);
			//tv_desc.setText(Html.fromHtml(desc));
			WebView myWebView = (WebView) findViewById(R.id.webview);
			myWebView.loadData(URLDecoder.decode(desc,"utf-8"), "text/html", "utf-8");
		}catch(Exception e){}	
	}
	
}
///// hemshres hereshem123$ 