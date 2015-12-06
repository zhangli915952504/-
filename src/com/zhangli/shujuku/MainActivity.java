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

		// 控制ListView里面总共有多少个条目
		@Override
		public int getCount() {
			return persons.size();// 条目个数==集合的size
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
			// 得到某个位置对应的person对象
			Person person = persons.get(position);
			View view=View.inflate(MainActivity.this, R.layout.list_activity, null);
			//一定要在view对象里面寻找孩子的id
			TextView tv_id=(TextView) view.findViewById(R.id.tv_id);
			tv_id.setText("id:"+person.getId());
			
			TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
			tv_name.setText("姓名:"+person.getName());
			
			TextView tv_number=(TextView) view.findViewById(R.id.tv_phone);
			tv_number.setText("电话:"+person.getNumber());
			
			return view;
		}

	}

}
