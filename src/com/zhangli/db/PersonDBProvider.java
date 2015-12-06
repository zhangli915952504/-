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
	
	//定义一个uri的匹配器用于匹配uri,如果路径不满足条件返回-1（NO_MATCH）
	private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);

	private static final int INSERT=1;
	private static final int DELETE=2;
	private static final int UPDATE=3;
	private static final int QUERY=4;
	private static final int QUERYONE=5;
	private PersonSQlite personsq; 
	
	static{
		//添加一组匹配规则
		matcher.addURI("com.zhangli.db.personprovider", "insert", INSERT);
		matcher.addURI("com.zhangli.db.personprovider", "delete", DELETE);
		matcher.addURI("com.zhangli.db.personprovider", "update", UPDATE);
		matcher.addURI("com.zhangli.db.personprovider", "query", QUERY);
		matcher.addURI("com.zhangli.db.personprovider", "query/#", QUERYONE);
	}
	
	
	
	//content://com.zhangli.db.personprovider/insert  添加的操作
	//content://com.zhangli.db.personprovider/delete  删除
	//content://com.zhangli.db.personprovider/update  更新
	//content://com.zhangli.db.personprovider/query   查询
	
	
	
	//当内容提供者被创建的时候调用适合数据的初始化
	@Override
	public boolean onCreate() {
		personsq =new PersonSQlite(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, 
			String selection, String[] selectionArgs, String sortOrder) {
		if(matcher.match(uri)==QUERY){
			//返回查询的结果集
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
			throw new IllegalArgumentException("路径不匹配，不能执行查询操作");
		}
	}

	@Override
	public String getType(Uri uri) {
		if(matcher.match(uri)==QUERY){
			//返回查询的结果集
			//返回多条数据
			return "vnd.android.cursor.dir/persons" ;
		}
		else if(matcher.match(uri)==QUERYONE){
			//返回多条数据
			return "vnd.android.cursor.item/persons";
			
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(matcher.match(uri)==INSERT){
			//返回添加的结果集
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.insert("person", null, values);
		}
		else{
			throw new IllegalArgumentException("路径不匹配，不能执行添加操作");
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if(matcher.match(uri)==DELETE){
			//返回删除的结果集
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.delete("person", selection, selectionArgs);
		}
		else{
			throw new IllegalArgumentException("路径不匹配，不能执行删除操作");
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		if(matcher.match(uri)==UPDATE){
			//返回修改的结果集
			SQLiteDatabase db=personsq.getWritableDatabase();
			db.update("person", values, selection, selectionArgs);
		}
		else{
			throw new IllegalArgumentException("路径不匹配，不能执行修改操作");
		}
		return 0;
	}
	

}
