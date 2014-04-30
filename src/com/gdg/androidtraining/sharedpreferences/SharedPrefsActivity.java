package com.gdg.androidtraining.sharedpreferences;

import com.gdg.androidtraining.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SharedPrefsActivity extends Activity implements OnClickListener{

	CheckBox checkBox;
	EditText editText;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_prefs);
		
		checkBox = (CheckBox) findViewById(R.id.checkBox1);
		editText = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
		loadSavedPreferences();
		
	}

	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", false);
		checkBox.setChecked(checkBoxValue);
		String name = sharedPreferences.getString("storedName", "GDG Android Training");
		editText.setText(name);
	}

	private void savePreferences(String key, boolean value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		savePreferences("CheckBox_Value", checkBox.isChecked());
		savePreferences("storedName", editText.getText().toString());
		finish();
	}
}
