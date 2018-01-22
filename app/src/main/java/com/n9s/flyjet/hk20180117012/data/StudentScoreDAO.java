package com.n9s.flyjet.hk20180117012.data;

/**
 * Created by Student on 2018/1/18.
 */

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class StudentScoreDAO  implements StudentDAO  //建構式
{
    public ArrayList<Student> mylist;

    public StudentScoreDAO()
    {
        mylist = new ArrayList<>();
    }

    public boolean add(Student s)   //新增資料, s:變數
    {
        mylist.add(s);
        return true;
    }
    public ArrayList<Student> getList() //取出整筆資料
    {
        return mylist;
    }

    public Student getStudent(int id)   //取出id資料
    {
        for (Student s : mylist)  //for_each, 從ARRAY內拉出每筆資料
        {
            if (s.id == id)         //用id比對,再傳回整組資料
            {
                return s;
            }
        }
        return null;
    }

    public boolean update(Student s)
    {
        for (Student t : mylist)
        {
            if(t.id == s.id)    //用id比對,再做update
            {
                t.name = s.name;
                t.score = s.score;
                return true;    //如果動作完成,回傳true
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        for (int i=0; i<mylist.size(); i++)
        {
            if (mylist.get(i).id ==id)  //用id比對,再做delete
            {
                mylist.remove(i);
                return true;
            }
        }
        return false;
    }
}
