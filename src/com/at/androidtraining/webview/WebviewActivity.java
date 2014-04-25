package com.at.androidtraining.webview;

import com.at.androidtraining.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends Activity {

	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		webView = (WebView) findViewById(R.id.webView);

		
		 webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
	     webView.loadUrl("http://google.com");
		
		
		//webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://google.com/");

		//String customHtml = "<html><body><h2>Greetings from Android Trainers</h2></body></html>";
		//webView.loadData(customHtml, "text/html", "UTF-8");
		
	}

	private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }
	
}
