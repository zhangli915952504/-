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
	
	//���
	public void TestAdd() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.add("ligang", "159");
	}
	
	//����
	public void TestFind() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		boolean result=dao.find("ligang");
		assertEquals(true, result);
	}
	
	//����
	public void TestUpdate() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.update("ligang","321");
	}
	
	//ɾ��
	public void TestDelete() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.delete("ligang");
	}
	
	//����ȫ��
	public void TestFindall() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		List<Person> persons=dao.findall();
		for(Person p:persons){
			System.out.print(p.toString());
		}
	}
}