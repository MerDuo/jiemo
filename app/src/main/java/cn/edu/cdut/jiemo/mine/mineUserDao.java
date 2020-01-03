package cn.edu.cdut.jiemo.mine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.edu.cdut.jiemo.schedule.sqLite;

/**
 * Created by aaa on 2019/12/31.
 * 单例模式存储当前登录的用户信息
 * 以及更新操作
 */

public class mineUserDao {
    private String userName;
    private String password;
    private String sex;
    private int age;
    private String theme;
    private String safeps;
    private  String signature;
    private String userImage;

    sqLite helper;
    SQLiteDatabase db;

    private static mineUserDao user=new mineUserDao();

    private mineUserDao(){}

    //获得user 没登录时返回空
    public static mineUserDao getUser(){
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
            Log.d("aaa","用户名重复");
            return false;
        }
        cours.moveToFirst();
        String usex=cours.getString(cours.getColumnIndex("sex"));
        int uage=cours.getInt(cours.getColumnIndex("age"));
        String utheme=cours.getString(cours.getColumnIndex("theme"));
        String usafeps=cours.getString(cours.getColumnIndex("safeps"));
        String usignature=cours.getString(cours.getColumnIndex("signature"));
        String userImage=cours.getString(cours.getColumnIndex("userimage"));
        user.setAge(uage);
        user.setSex(usex);
        user.setTheme(utheme);
        user.setSafeps(usafeps);
        user.setSignature(usignature);
        user.setUserImage(userImage);
        return true;
    }
    //清除数据
    public void clearUser(){
        user.setUserName(null);
        user.setAge(0);
        user.setPassword(null);
        user.setSafeps(null);
        user.setSex(null);
        user.setSignature(null);
        user.setUserImage(null);
        user.setTheme(null);
    }

    //改变用户名
    public Boolean changeAccount(String newName){
        if(helper.hasUser(newName)){
            return false;
        }else {

            Log.d("aaa", "userbean+changeAccount: getusername"+user.getUserName());
            ContentValues values=new ContentValues();
            values.put("account",newName);
            //更改数据库的内容
            db.update("users",values,"account=?",new String[]{user.getUserName()});
            //更改userbean的内容
            user.setUserName(newName);
            return true;
        }
    }

    //改变年龄
    public Boolean changeAge(int newAge){
        user.setAge(newAge);
        ContentValues values=new ContentValues();
        values.put("age",newAge);
        db.update("users",values,"account=?",new String[]{user.getUserName()});
        return true;
    }
    //改性别
    public Boolean changeSex(String newSex){
        user.setSex(newSex);
        ContentValues values=new ContentValues();
        values.put("sex",newSex);
        db.update("users",values,"account=?",new String[]{user.getUserName()});
        return true;
    }
    //改主题
    public Boolean changeTheme(String newTheme){
        user.setTheme(newTheme);
        ContentValues values=new ContentValues();
        values.put("Theme",newTheme);
        db.update("users",values,"account=?",new String[]{user.getUserName()});
        return true;
    }
//    //改安全密码
//    public Boolean changeSafePs(String newSafePs){
//        user.setSafeps(newSafePs);
//        ContentValues values=new ContentValues();
//        values.put("safeps",newSafePs);
//        db.update("users",values,"account=?",new String[]{user.getUserName()});
//        return true;
//    }
    //改签名
    public Boolean changeSignature(String newSignature){
        user.setSignature(newSignature);
        ContentValues values=new ContentValues();
        values.put("signature",newSignature);
        db.update("users",values,"account=?",new String[]{user.getUserName()});
        return true;
    }
    //改头像
    public Boolean changeUserImage(String newUserImage){
        user.setUserImage(newUserImage);
        ContentValues values=new ContentValues();
        values.put("userimage",newUserImage);
        db.update("users",values,"account=?",new String[]{user.getUserName()});
        return true;
    }


//setter和getter
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
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}
