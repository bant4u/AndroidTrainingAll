package com.gdg.androidtraining.database;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.gdg.androidtraining.R;
import com.gdg.androidtraining.Student;

public class DatabaseActivity extends Activity {

	DataBaseHandler dh;
	BaseAdapter adapter;
	List<String> studentList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		
		dh = new DataBaseHandler(getApplicationContext());

		studentList = dh.getAllStudentsName();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentList);
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		
	}
	
	public void addUser(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View custom = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
		builder.setView(custom)
				.setPositiveButton("Save", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText name = (EditText) custom.findViewById(R.id.txt_name);
						EditText phone = (EditText) custom.findViewById(R.id.txt_phone);
						EditText address = (EditText) custom.findViewById(R.id.txt_address);
						Student st = new Student(0, name.getText().toString(), null, phone.getText().toString(), address.getText().toString());
						Student stud = dh.addStudent(st);
						studentList.add(stud.getName());
						adapter.notifyDataSetChanged();
					}
				})
				.setNegativeButton("Cancel", null)
				.create().show();
	}

	public void deleteFirstUser(View view) {
		if (studentList.size() > 0) {
			dh.deleteStudentAt(0);
			studentList.remove(0);
			adapter.notifyDataSetChanged();
		}

	}
}
