package com.zhangli.shujuku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQlite extends SQLiteOpenHelper {
	
	// ���ݿ�Ĺ��췽�� �����������ݿ������  ���ݿ��ѯ�Ľ����  ���ݿ�İ汾
	//@param context 
	public PersonSQlite(Context context) {
		super(context, "Person.db", null, 1);
		// TODO Auto-generated constructor stub
	}
	//���ݿ��һ�α�������ʱ����õķ�����
	//@param db�����������ݿ�
	@Override
	public void onCreate(SQLiteDatabase db) {
		//��ʼ�����ݿ�ı�ṹ
		db.execSQL("create table person(id integer primary key autoincrement,name varchar(20),number varchar(20))");
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}