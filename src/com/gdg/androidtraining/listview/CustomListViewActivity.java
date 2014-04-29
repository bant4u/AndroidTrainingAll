package com.gdg.androidtraining.listview;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdg.androidtraining.R;
import com.gdg.androidtraining.Student;
import com.gdg.androidtraining.database.DataBaseHandler;

public class CustomListViewActivity extends Activity {

	DataBaseHandler dh;
	List<Student> studentList;
	boolean showPhone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		dh = new DataBaseHandler(getApplicationContext());
		studentList = dh.getAllStudents();
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		showPhone = sharedPreferences.getBoolean("CheckBox_Value", false);
		

		BaseAdapter adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, studentList){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.row_student, null);
				}
				Student s = studentList.get(position);
				TextView name = (TextView) convertView.findViewById(R.id.txt_name);
				name.setText(s.getName());
				TextView address = (TextView) convertView.findViewById(R.id.txt_address);
				address.setText(s.getAddress());
				if(showPhone){
					TextView phone = (TextView) convertView.findViewById(R.id.txt_phone);
					phone.setVisibility(View.VISIBLE);
					phone.setText(s.getPhone());
				}
				return convertView;
			}
		};
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				showDetails(studentList.get(position));
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				showOptions(studentList.get(position));
				return true;
			}
		});
		
		if(studentList.size() == 0){
			toast("No data found. Please add data from database menu.");
		}
	}

	protected void showOptions(Student student) {
		final String options[] = new String[]{"Call","Duplicate", "Edit", "Delete"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(student.getName())
				.setItems(options, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						toast(options[which] + " clicked");
					}
				})
				.setPositiveButton("Dismiss", null)
				.create().show();
	}

	protected void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}

	protected void showDetails(Student student) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(student.getName())
				.setMessage("Address : " + student.getAddress() + "\nPhone : " + student.getPhone())
				.setPositiveButton("Dismiss", null)
				.create().show();
	}
}
