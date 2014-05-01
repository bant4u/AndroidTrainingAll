package com.gdg.androidtraining.database;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.gdg.androidtraining.R;
import com.gdg.androidtraining.Student;

public class DatabaseActivity extends Activity {

	DataBaseHandler dh;
	BaseAdapter adapter;
	List<Student> studentList;
	boolean showPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		
		dh = new DataBaseHandler(getApplicationContext());

		studentList = dh.getAllStudents();
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		showPhone = sharedPreferences.getBoolean("CheckBox_Value", false);
		

		adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, studentList){
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
		
	}
	
	protected void showOptions(final Student student) {
		final String options[] = new String[]{"Delete"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(student.getName())
				.setItems(options, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						toast(options[which] + " clicked");
						Student stud = studentList.remove(which);
						dh.deleteStudent(stud);
						adapter.notifyDataSetChanged();
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
						if(st.getName().length() > 0){
							Student stud = dh.addStudent(st);
							studentList.add(stud);
							adapter.notifyDataSetChanged();
							toast("Saved data");
						}
						else{
							toast("Name empty");
						}
					}
				})
				.setNegativeButton("Cancel", null)
				.create().show();
	}

	public void deleteFirstUser(View view) {
		if (studentList.size() > 0) {
			Student st = studentList.remove(0);
			dh.deleteStudent(st);
			adapter.notifyDataSetChanged();
			toast("First data deleted");
		}

	}
}
