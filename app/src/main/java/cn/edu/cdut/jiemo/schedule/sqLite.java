package cn.edu.cdut.jiemo.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import cn.edu.cdut.jiemo.diary.DiaryBean;
import cn.edu.cdut.jiemo.userBean.userBean;
public class sqLite extends SQLiteOpenHelper {
    private static final String db_name = "MyDatabase.db";//自定义的数据库名；
    private static final int version = 1;//版本号
    SQLiteDatabase database = getWritableDatabase();
    public sqLite(Context context) {
        super(context, db_name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //开启外键约束
        super.onOpen(sqLiteDatabase);
        if(sqLiteDatabase.isReadOnly()){
            sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
        }

        //创建个人信息表
        String sql1="CREATE TABLE users(uid integer primary key autoincrement,"+
                "account text not null,"+
                "password text not null," +
                "avatar blob,"+
                "name text,"+
                "sex text,"+
                "age integer,"+
                "theme text,"+
                "safeps text,"+
                "signature text)";
        sqLiteDatabase.execSQL(sql1);

        //创建手账表
        String sql2="CREATE TABLE diary(did integer primary key autoincrement,"+
                "title text not null," +
                "diarytext text not null," +
                "fontcolor varchar(30)," +
                "fontsize real," +
                "diarydate varchar(30)," +
                "diarytime varchar(30)," +
                "diarycategory varchar(30)," +
                "background integer,"+
                "uname varchar(30),"+
                "foreign key(uname) references users(account) on delete cascade on update cascade)";
        //                "uid integer,"+
//                "foreign key(uid) references users(uid) on delete cascade on update cascade)";
        sqLiteDatabase.execSQL(sql2);

        //创建行程表
        String  sql3 ="create table schedules(" +
                "id Integer primary key autoincrement," +     //id自增,只支持integer不支持int
                "checkd varchar(10)," +
                "schedule varchar(50)," +
                "day varchar(30)," +
                "time varchar(30),"+
                "uid integer,"+
                "foreign key(uid) references users(uid) on delete cascade on update cascade)";
        sqLiteDatabase.execSQL(sql3);

        //创建安全密码表
        String sql4="CREATE TABLE securitypwd(sid integer primary key autoincrement,"+
                "securitypwd text," +
                "state text not null," +
                "uid integer not null,"+
                "foreign key(uid) references users(uid) on delete cascade on update cascade)";
        sqLiteDatabase.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(scheduleBean scheduleBean){
        //SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();//键值对的集合
        cv.put("checkd" ,scheduleBean.check);
        cv.put("schedule",scheduleBean.plan);
        cv.put("day",scheduleBean.day);
        cv.put("time",scheduleBean.time);
        database.insert("schedules",null,cv);
        Log.i("成功插入数据","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    public Cursor getAll(){
        //SQLiteDatabase database = getWritableDatabase();
        return database.query("schedules",null,null,null,null,null,null);
    }
    public Cursor getOneday(String day){
        //SQLiteDatabase database = getWritableDatabase();
        Log.i("查询某天日程","bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        return database.query("schedules",null,"day=?",new String[]{day},null,null,null);

    }
    public void deleteAll(){
        //SQLiteDatabase database = getWritableDatabase();
        database.delete("schedules",null,null);
    }
    public void update(int i,scheduleBean scheduleBean){
        //SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();//键值对的集合
        cv.put("checkd" ,scheduleBean.check);
        cv.put("schedule",scheduleBean.plan);
        cv.put("day",scheduleBean.day);
        cv.put("time",scheduleBean.time);
        long ret = -1;
        do {
            ret = database.update("schedules",cv,"id=?",new String[]{ String.valueOf(i)});
        }while (ret<0);
        Log.i("更新数据：",scheduleBean.plan+i);
    }
    public long deleteSchedele(int i){
        return database.delete("schedules","id="+i,null);
    }
    public Boolean addUser(userBean user){//注册
        if(!hasUser(user.userName)) {
            ContentValues cv = new ContentValues();//键值对的集合
            cv.put("account", user.userName);
            cv.put("password", user.password);
            database.insert("users", null, cv);
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean hasUser(String name){//判断是否有该用户名
        Cursor cursor = database.query("users",null,"account=?",new String[]{name},null,null,null);
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }
    public Boolean login(String name,String password){
        String sql = "select * from users where account=? and password=?";
        Cursor cursor = database.rawQuery(sql, new String[] {name, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    // 手账
//    public void insertDiary(DiaryBean diaryBean){
//        //SQLiteDatabase database = getWritableDatabase();
//        ContentValues cv = new ContentValues();//键值对的集合
//        cv.put("title" , diaryBean.title);
//        cv.put("diarytext",diaryBean.diarytext);
//        cv.put("fontcolor",diaryBean.fontcolor);
//        cv.put("fontsize",diaryBean.fontsize);
//        cv.put("diarydate",diaryBean.diarydate);
//        cv.put("diarytime",diaryBean.diarytime);
//        cv.put("diarycategory",diaryBean.diarycategory);
//        cv.put("background",diaryBean.background);
//
//        database.insert("diary",null,cv);
//        Log.i("成功插入数据","diaryinsert");
//    }
//    public Cursor getAllDiary(){
//        return database.query("diary",null,null,null,null,null,null);
//    }
//
//    public Cursor getDiary(String title){
//        return database.query("diary",null,"title=?",new String[]{title},null,null,null);
//    }
//
//    public long deleteDiary(String title){
//        return database.delete("diary","title='"+title+"'",null);
//    }
    public void insertDiary(DiaryBean diaryBean){
        //SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();//键值对的集合
        cv.put("title" , diaryBean.title);
        cv.put("diarytext",diaryBean.diarytext);
        cv.put("fontcolor",diaryBean.fontcolor);
        cv.put("fontsize",diaryBean.fontsize);
        cv.put("diarydate",diaryBean.diarydate);
        cv.put("diarytime",diaryBean.diarytime);
        cv.put("diarycategory",diaryBean.diarycategory);
        cv.put("background",diaryBean.background);
        cv.put("uname",diaryBean.uname);

        database.insert("diary",null,cv);
        Log.i("成功插入数据","diaryinsert");
    }
    public Cursor getAllDiary(String username){
        return database.query("diary",null,"uname=?",new String[]{username},null,null,null);
    }

    public Cursor getDiary(String title,String username){
        return database.query("diary",null,"title=? and uname=?",new String[]{title,username},null,null,null);
    }

    public long deleteDiary(String title,String username){
        return database.delete("diary","title='"+title+"' and uname='"+username+"'",null);
    }

}
