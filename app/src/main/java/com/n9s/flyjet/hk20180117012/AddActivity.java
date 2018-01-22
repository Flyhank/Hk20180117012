package com.n9s.flyjet.hk20180117012;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.n9s.flyjet.hk20180117012.data.Student;

public class AddActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void clickAdd(View v)    //按下Add鍵後開始執行抓取資料
    {
        EditText ed1 = (EditText) findViewById(R.id.editText);
        EditText ed2 = (EditText) findViewById(R.id.editText2);
        EditText ed3 = (EditText) findViewById(R.id.editText3);
        int id = Integer.valueOf(ed1.getText().toString());     //ed1: int(id); String->int
        String name = ed2.getText().toString();             //ed2: String(name)
        int score = Integer.valueOf(ed3.getText().toString());  //ed3:number(score)
        MainActivity.dao.add(new Student(id, name, score)); //單筆完整Student資料放入加入MainActivity.dao內
        finish();
    }
}
