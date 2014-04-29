package com.gdg.androidtraining.database;

import java.util.ArrayList;
import java.util.List;

import com.gdg.androidtraining.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper{

	public static final String TABLE_STUDENTS = "Students";
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "_name";
	public static final String KEY_IMAGE = "_image";
	public static final String KEY_PHONE = "_phone";
	public static final String KEY_ADDRESS = "_address";

	private static final String DATABASE_NAME = "Students.db";
	private static final int DATABASE_VERSION = 1;

	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String DATABASE_CREATE = 
				"CREATE TABLE IF NOT EXISTS " + TABLE_STUDENTS + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_NAME + " TEXT, " + KEY_IMAGE + " TEXT, " + KEY_PHONE + " TEXT, " 
				+ KEY_ADDRESS + " TEXT );";
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
		onCreate(db);
	}
	
	public Student addStudent(Student s) {
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, s.getName());
		values.put(KEY_IMAGE, s.getImage());
		values.put(KEY_PHONE, s.getPhone());
		values.put(KEY_ADDRESS, s.getAddress());
		SQLiteDatabase db = this.getWritableDatabase();
		long studId = db.insert(TABLE_STUDENTS, null, values);

		s.setId((int) studId);
		/*// now that the student is created return it ...
		Cursor cursor = db.query(DataBaseHandler.TABLE_STUDENTS,
				null, DataBaseHandler.KEY_ID + " = " + studId, null, null, null, null);

		Student ret = null;
		if(cursor.moveToFirst()){
			ret = new Student(cursor.getInt(0), cursor.getString(1));
		}
		cursor.close();*/
		db.close();
		return s;
	}

	public void deleteStudent(Student comment) {
		long id = comment.getId();
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_STUDENTS, DataBaseHandler.KEY_ID + " = " + id, null);
		db.close();
	}
	public void deleteStudentAt(int position) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " LIMIT " + position + ", 1" , null);
		if(cursor.moveToFirst()){
			int id = cursor.getInt(0);
			db.delete(TABLE_STUDENTS, DataBaseHandler.KEY_ID + " = " + id, null);
		}
		db.close();
	}
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_STUDENTS, null, null, null, null, null, null);

		if(cursor.moveToFirst()){
			do {
				Student s = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
				students.add(s);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return students;
	}
	
	public List<String> getAllStudentsName() {
		List<String> students = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_STUDENTS, null, null, null, null, null, null);

		if(cursor.moveToFirst()){
			do {
				students.add(cursor.getString(1));
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return students;
	}

}
