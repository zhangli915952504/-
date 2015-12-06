package com.zhangli.shujuku;

import java.util.List;
import com.zhangli.Dao.PersonDao;
import com.zhangli.db.Person;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private List<Person> persons;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PersonDao dao = new PersonDao(this);
		persons = dao.findall();

		lv = (ListView) findViewById(R.id.lv);

		lv.setAdapter(new myAdapter());
	}

	private class myAdapter extends BaseAdapter {

		// ����ListView�����ܹ��ж��ٸ���Ŀ
		@Override
		public int getCount() {
			return persons.size();// ��Ŀ����==���ϵ�size
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// �õ�ĳ��λ�ö�Ӧ��person����
			Person person = persons.get(position);
			View view=View.inflate(MainActivity.this, R.layout.list_activity, null);
			//һ��Ҫ��view��������Ѱ�Һ��ӵ�id
			TextView tv_id=(TextView) view.findViewById(R.id.tv_id);
			tv_id.setText("id:"+person.getId());
			
			TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
			tv_name.setText("����:"+person.getName());
			
			TextView tv_number=(TextView) view.findViewById(R.id.tv_phone);
			tv_number.setText("�绰:"+person.getNumber());
			
			return view;
		}

	}

}
