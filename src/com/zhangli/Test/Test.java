package com.zhangli.Test;

import java.util.List;

import com.zhangli.Dao.Persondao2;
import com.zhangli.db.Person;
import com.zhangli.shujuku.PersonSQlite;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase{
	public void test() throws Exception{
		PersonSQlite person=new PersonSQlite(getContext());
		SQLiteDatabase db=person.getWritableDatabase();
	}
	
	//添加
	public void TestAdd() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.add("ligang", "159");
	}
	
	//查找
	public void TestFind() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		boolean result=dao.find("ligang");
		assertEquals(true, result);
	}
	
	//更改
	public void TestUpdate() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.update("ligang","321");
	}
	
	//删除
	public void TestDelete() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.delete("ligang");
	}
	
	//查找全部
	public void TestFindall() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		List<Person> persons=dao.findall();
		for(Person p:persons){
			System.out.print(p.toString());
		}
	}
}