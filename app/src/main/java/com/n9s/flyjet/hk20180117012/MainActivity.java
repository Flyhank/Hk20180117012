package com.n9s.flyjet.hk20180117012;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.n9s.flyjet.hk20180117012.data.DBType;
import com.n9s.flyjet.hk20180117012.data.Student;
import com.n9s.flyjet.hk20180117012.data.StudentDAO;
import com.n9s.flyjet.hk20180117012.data.StudentDAOFactory;
import com.n9s.flyjet.hk20180117012.data.StudentFileDAO;
import com.n9s.flyjet.hk20180117012.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //public static StudentFileDAO dao;
    //以(靜態物件)StudentScoreDAO class宣告變數dao
    public static StudentDAO dao;
    ListView lv;
    //int dbType; //(db: database)
    DBType dbType;  //enum宣告

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dao = new StudentFileDAO(this);
        //dbType =1; // 1:記憶體 2:檔案
        dbType = DBType.FILE;
        dao = StudentDAOFactory.getDAOInstance(this, dbType);
    }

    @Override
    protected void onResume()   //新增資料時,新頁面會蓋住listView頁面進入stop and hale; 新增資料回來後,
    //listView頁面會resume
    {
        super.onResume();
        lv = (ListView) findViewById(R.id.listView);
        ArrayList<String> studentNames = new ArrayList<>();

        for (Student s : dao.getList()) //getList:整個資料庫資料
        {
            studentNames.add(s.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, studentNames);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)   //i是position
            //******點擊任一筆資料時根據ViewList上的位置,而非任何資料 *********
            {
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("id", dao.getList().get(i).id);     //取出位置內的id值
                startActivity(it);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)   //在main_menu下建立選單,且為第一畫面
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) //建立選單
    {
        if (item.getItemId() == R.id.menu_add)  //按下main_menu頁上的+按鍵, 跳至新增內容AddActivity頁面
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            //將AddActiviy放入MainActivity等待(在記憶體中的物件)
            startActivity(it);  //在一個Activity中可以使用startActivity方法，將一個intent物件發送至Android系統中，
            // 由Android系統判別，判別後由系統將我們的ResultActivity顯示在畫面上，
        }
        return super.onOptionsItemSelected(item);
    }
}
