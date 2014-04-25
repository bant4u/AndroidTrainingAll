package com.at.androidtraining.database;

import java.util.List;

import com.at.androidtraining.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class DatabaseActivity extends Activity {

	DataBaseHandler dh;
	BaseAdapter adapter;
	List<Student> studentList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		
		dh = new DataBaseHandler(getApplicationContext());

		studentList = dh.getAllStudents();
		adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, studentList);
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		
	}
	
	public void addUser(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		Student stud = dh.addStudent(text.getText().toString());

		studentList.add(stud);
		adapter.notifyDataSetChanged();
	}

	public void deleteFirstUser(View view) {
		if (studentList.size() > 0) {
			Student st = studentList.get(0);
			dh.deleteStudent(st);
			adapter.notifyDataSetChanged();
		}

	}
}
