package com.at.androidtraining.phonecall;

import com.at.androidtraining.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CallActivity extends Activity {
	private EditText et1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		et1 = (EditText) findViewById(R.id.editText1);

	}

	public void btnClick(View view) {
		String phoneNumber = et1.getText().toString();
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phoneNumber));
		startActivity(intent);
	}

}
