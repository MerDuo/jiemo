package cn.edu.cdut.jiemo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.mainBean.scheduleBean;
import cn.edu.cdut.jiemo.mainAdapter;
import cn.edu.cdut.jiemo.schedule.editplan;
import cn.edu.cdut.jiemo.schedule.scheduleAdapter;
import cn.edu.cdut.jiemo.schedule.sqLite;

import static android.content.Context.MODE_PRIVATE;

public class fragment_main extends Fragment {
    private List<scheduleBean> schduleList=new ArrayList<>();
    Boolean isLogin;
    private sqLite mySQLiteOpenHelper;
    int uid;
    private String dateToday;//用于记录今天的日期
    ListView slist;
    String splan;
    String stime;
    String day;
    Boolean ischecked;
    private SQLiteDatabase myDatabase;
    SharedPreferences pref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View calendarView = inflater.inflate(R.layout.activity_calender,null,false);
        mySQLiteOpenHelper = new sqLite(getContext());
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        pref=getActivity().getSharedPreferences("loginInfo",MODE_PRIVATE);
        isLogin=pref.getBoolean("isLogin",false);
        uid = pref.getInt("userId",0);
        slist = calendarView.findViewById(R.id.slist);
        //显示今天的日程
        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH)+1;//注意要+1，0表示1月份
        int day = time.get(Calendar.DAY_OF_MONTH);
        dateToday = year+"-"+month+"-"+day;
        slist = calendarView.findViewById(R.id.slist);
        initSchedule(dateToday);
//        initSchedule();
        mainAdapter mainAdapter=new mainAdapter(getContext(),R.layout.main_item,schduleList);
        View mainPageview = inflater.inflate(R.layout.activity_mainpage, container, false);
        ListView listview=mainPageview.findViewById(R.id.main_list_view);
        listview.setAdapter(mainAdapter);

        return mainPageview;
    }

    private void initSchedule(final String data) {
//        scheduleBean schedule1=new scheduleBean("Apple","hhh");
//        schduleList.add(schedule1);
//        scheduleBean schedule2=new scheduleBean("Apple","hhh");
//        schduleList.add(schedule2);
        schduleList = new ArrayList<>();
        mainAdapter mainAdapter = new mainAdapter(getContext(),schduleList);
        slist.setAdapter(mainAdapter);
        final Cursor cursor;
        //     Log.i("数据：",data);
        if(isLogin){
            cursor = mySQLiteOpenHelper.getLogOneday(data,uid);
        }else {
            cursor = mySQLiteOpenHelper.getOneday(data);
        }
    ;
//        mainAdapter.notifyDataSetChanged();
        schduleList.clear();
        while(cursor.moveToNext()){
            cn.edu.cdut.jiemo.mainBean.scheduleBean  scheduleBean= new scheduleBean(stime,splan);
            day=cursor.getString(cursor.getColumnIndex("day"));
            splan = cursor.getString(cursor.getColumnIndex("schedule"));
            stime = cursor.getString(cursor.getColumnIndex("time"));
            Log.i("sss",splan);
            schduleList.add(scheduleBean);

        }

        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int select;
                cursor.moveToPosition(i);
                select = cursor.getInt(0);

                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+select);

                String oplan = schduleList.get(i).getStitle();
//                String oday = schduleList.get(i).day;
//                String ocheck = schduleList.get(i).check;
                String otime = schduleList.get(i).getStime();
                Intent intent = new Intent(getContext(), editplan.class);
                Bundle bundle = new Bundle();
                bundle.putString("oplan", oplan);
                bundle.putString("oday", data);
                bundle.putString("otime",otime);
//                bundle.putString("ocheck",ocheck);
                bundle.putInt("dbid",select);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
//        mainAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
