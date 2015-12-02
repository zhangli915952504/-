package com.zhangli.Dao;



import java.util.ArrayList;
import java.util.List;
import com.zhangli.db.Person;
import com.zhangli.shujuku.PersonSQlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Persondao2 {
	private PersonSQlite person;

	public Persondao2(Context context) {
		person = new PersonSQlite(context);
	}

	// 添加
	public long add(String name, String number) {
		SQLiteDatabase db = person.getWritableDatabase();
		//db.execSQL("insert into person(name,number) values (?,?)", new Object[] { name, number });
		ContentValues values=new ContentValues();
		values.put("name", name);
		values.put("number", number);
		long id=db.insert("person", null, values);
		db.close();
		return id;
	}

	// 查询
	public boolean find(String name) {
		SQLiteDatabase db = person.getReadableDatabase();
		//Cursor cursor = db.rawQuery("select * from person where name=?", new String[] { name });
		Cursor cursor =db.query("person", null, "name=?", new String[] { name }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		return result;
	}

	// 更改
	public int update(String name, String newnumber) {
		SQLiteDatabase db = person.getReadableDatabase();
		//db.execSQL("update person set number=? where name=?", new Object[] { newnumber, name });
		ContentValues values=new ContentValues();
		values.put("number", newnumber);
		int number=db.update("person", values, "name=?", new String[]{newnumber});
		db.close();
		return number;
	}

	// 删除
	public int  delete(String name) {
		SQLiteDatabase db = person.getReadableDatabase();
		//db.execSQL("delete person  where name=?", new Object[] { name });
		int number=db.delete("person",  "name=?", new String[]{name});
		db.close();
		return number;
	}

	// 查找全部
	public List<Person> findall() {
		SQLiteDatabase db = person.getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();

		//Cursor cursor = db.rawQuery("select * from person", null);
		Cursor cursor = db.query("person", new String[]{"name","id","number"},null, null, null,null,null);
		
		
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Person p = new Person(name, number, id);
			persons.add(p);
		}
		cursor.close();
		db.close();
		return persons;

	}

}