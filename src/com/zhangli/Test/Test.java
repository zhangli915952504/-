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
//		dao.add("", "159");
		
		long number=88888l;
		for(int i=0;i<50;i++){
			dao.add("�񱼵ĵ�"+i+"��ţ",Long.toString(number+i));
		}
	}
	
	//����
	public void TestFind() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		boolean result=dao.find("�񱼵���ţ");
		assertEquals(true, result);
	}
	
	//����
	public void TestUpdate() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		dao.update("�񱼵���ţ","321");
	}
	
	//ɾ��
	public void TestDelete() throws Exception{
		Persondao2 dao=new Persondao2(getContext());
		for(int i=0;i<50;i++){
			dao.delete("�񱼵���ţ"+i);
		}
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