package com.zhangli.Dao;



import java.util.ArrayList;
import java.util.List;
import com.zhangli.db.Person;
import com.zhangli.shujuku.PersonSQlite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDao {
	private PersonSQlite person;

	public PersonDao(Context context) {
		person = new PersonSQlite(context);
	}

	// ���
	public void add(String name, String number) {
		SQLiteDatabase db = person.getWritableDatabase();

		db.execSQL("insert into person(name,number) values (?,?)", new Object[] { name, number });
		db.close();
	}

	// ��ѯ
	public boolean find(String name) {
		SQLiteDatabase db = person.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where name=?", new String[] { name });
		boolean result = cursor.moveToNext();
		cursor.close();
		return result;
	}

	// ����
	public void update(String name, String newnumber) {
		SQLiteDatabase db = person.getReadableDatabase();
		db.execSQL("update person set number=? where name=?", new Object[] { newnumber, name });
		db.close();
	}

	// ɾ��
	public void delete(String name) {
		SQLiteDatabase db = person.getReadableDatabase();
		db.execSQL("delete person  where name=?", new Object[] { name });
		db.close();
	}

	// ����ȫ��
	public List<Person> findall() {
		SQLiteDatabase db = person.getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();

		Cursor cursor = db.rawQuery("select * from person", null);
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