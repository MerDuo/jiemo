package cn.edu.cdut.jiemo.mine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.edu.cdut.jiemo.schedule.sqLite;

/**
 * Created by aaa on 2019/12/31.
 * 单例模式存储当前登录的用户信息
 */

public class mineUserBean {
    private String userName;
    private String password;
    private String sex;
    private int age;
    private String theme;
    private String safeps;
    sqLite helper;
    SQLiteDatabase db;

    private static mineUserBean user=null;

    private mineUserBean(){}

    //获得user 没登录时返回空
    public static mineUserBean getUser(){
        return user;
    }

    //初始化helper,database
    public void setdb(Context context){
        helper=new sqLite(context);
        db=helper.getWritableDatabase();
    }

    //初始化用户
    public boolean initUser(Context context,String account){
        //初始化database
        helper=new sqLite(context);
        db=helper.getWritableDatabase();

        //初始化name
        user.setUserName(account);

        //通过account查询
        Cursor cours=db.query("users",null,"account=?",new String[]{account},null,null,null);
        if(cours.getCount()>1){
            Log.e("aaa","用户名重复");
            return false;
        }
        String uname=cours.getString(cours.getColumnIndex("name"));
        String usex=cours.getString(cours.getColumnIndex("sex"));;
        int uage=cours.getInt(cours.getColumnIndex("age"));
        String utheme=cours.getString(cours.getColumnIndex("theme"));
        String safeps=cours.getString(cours.getColumnIndex("safeps"));
        String signature=cours.getString(cours.getColumnIndex("signature"));
        return true;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSafeps() {
        return safeps;
    }

    public void setSafeps(String safeps) {
        this.safeps = safeps;
    }
}
