package cn.edu.cdut.jiemo.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqLite extends SQLiteOpenHelper {
    private static final String db_name = "MySchedule";//自定义的数据库名；
    private static final int version = 1;//版本号

    public sqLite(Context context) {
        super(context, db_name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  sql ="create table schedules(" +
                "id Integer primary key autoincrement," +     //id自增,只支持integer不支持int
                "checkd varchar(10)," +
                "schedule varchar(50)," +
                "day varchar(30)," +
                "time varchar(30)"+
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(scheduleBean scheduleBean){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();//键值对的集合
        cv.put("checkd" ,scheduleBean.check);
        cv.put("schedule",scheduleBean.plan);
        cv.put("day",scheduleBean.day);
        cv.put("time",scheduleBean.time);
        database.insert("schedules",null,cv);
        Log.i("成功插入数据","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    public Cursor getAll(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("schedules",null,null,null,null,null,null);
    }
    public Cursor getOneday(String day){
        SQLiteDatabase database = getWritableDatabase();
        Log.i("查询某天日程","bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        return database.query("schedules",null,"day=?",new String[]{day},null,null,null);

    }
    public void deleteAll(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("schedules",null,null);
    }
    public void update(int i,scheduleBean scheduleBean){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();//键值对的集合
        cv.put("checkd" ,scheduleBean.check);
        cv.put("schedule",scheduleBean.plan);
        cv.put("day",scheduleBean.day);
        cv.put("time",scheduleBean.time);
        long ret = -1;
        do {
            ret = database.update("schedules",cv,"id=?",new String[]{ String.valueOf(i)});
        }while (ret<0);
        Log.i("更新数据：","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
