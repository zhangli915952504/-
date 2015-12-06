package com.zhangli.db;

import com.zhangli.shujuku.PersonSQlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonDBProvider extends ContentProvider {
	
	//����һ��uri��ƥ��������ƥ��uri,���·����������������-1��NO_MATCH��
	private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);

	private static final int INSERT=1;
	private static final int DELETE=2;
	private static final int UPDATE=3;
	private static final int QUERY=4;
	private static final int QUERYONE=5;
	private PersonSQlite personsq; 
	
	static{
		//���һ��ƥ�����
		matcher.addURI("com.zhangli.db.personprovider", "insert", INSERT);
		matcher.addURI("com.zhangli.db.personprovider", "delete", DELETE);
		matcher.addURI("com.zhangli.db.personprovider", "update", UPDATE);
		matcher.addURI("com.zhangli.db.personprovider", "query", QUERY);
		matcher.addURI("com.zhangli.db.personprovider", "query/#", QUERYONE);
	}
	
	
	
	//content://com.zhangli.db.personprovider/insert  ��ӵĲ���
	//content://com.zhangli.db.personprovider/delete  ɾ��
	//content://com.zhangli.db.personprovider/update  ����
	//content://com.zhangli.db.personprovider/query   ��ѯ
	
	
	
	//�������ṩ�߱�������ʱ������ʺ����ݵĳ�ʼ��
	@Override
	public boolean onCreate() {
		personsq =new PersonSQlite(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, 
			String selection, String[] selectionArgs, String sortOrder) {
		if(matcher.match(uri)==QUERY){
			//���ز�ѯ�Ľ����
			SQLiteDatabase db=personsq.getReadableDatabase();
			Cursor cursor=db.query("person", projection, selection,selectionArgs, null, null,sortOrder);
			return cursor;
		}
		else if(matcher.match(uri)==QUERYONE){
			long id=ContentUris.parseId(uri);
			SQLiteDatabase db=personsq.getReadableDatabase();
			Cursor cursor=db.query("person", projection, "id=?",new String[]{id+""}, null, null,sortOrder);
			return cursor;
			
		}
		else{
			throw new IllegalArgumentException("·����ƥ�䣬����ִ�в�ѯ����");
		}
	}

	@Override
	public String getType(Uri uri) {
		if(matcher.match(uri)==QUERY){
			//���ز�ѯ�Ľ����
			//���ض�������
			return "vnd.android.cursor.dir/persons" ;
		}
		else if(matcher.match(uri)==QUERYONE){
			//���ض�������
			return "vnd.android.cursor.item/persons";
			
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(matcher.match(uri)==INSERT){
			//������ӵĽ����
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.insert("person", null, values);
		}
		else{
			throw new IllegalArgumentException("·����ƥ�䣬����ִ����Ӳ���");
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if(matcher.match(uri)==DELETE){
			//����ɾ���Ľ����
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.delete("person", selection, selectionArgs);
		}
		else{
			throw new IllegalArgumentException("·����ƥ�䣬����ִ��ɾ������");
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		if(matcher.match(uri)==UPDATE){
			//�����޸ĵĽ����
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.update("person", values, selection, selectionArgs);
		}
		else{
			throw new IllegalArgumentException("·����ƥ�䣬����ִ���޸Ĳ���");
		}
		return 0;
	}
	

}
