package cn.edu.cdut.jiemo.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.schedule.addplan;
import cn.edu.cdut.jiemo.schedule.calender;
import cn.edu.cdut.jiemo.schedule.editplan;
import cn.edu.cdut.jiemo.schedule.scheduleAdapter;

import cn.edu.cdut.jiemo.schedule.scheduleBean;
import cn.edu.cdut.jiemo.schedule.sqLite;

public class calenderFragment extends Fragment {
    FloatingActionButton fab;
    CalendarView calendar;
    private Context context;
    private sqLite mySQLiteOpenHelper;
    private SQLiteDatabase myDatabase;
    private List<scheduleBean> msBeanList;
    private ListView slist;
    private String dateToday;//用于记录今天的日期
    private String switchDay;//用于记录选择的日期

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        View calendarView = inflater.inflate(R.layout.activity_calender,null,false);
        FloatingActionButton fab = calendarView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),addplan.class);
//                Intent intent = new Intent(MainActivity.this,addplan.class);
                startActivity(intent);
            }
        });
        setHasOptionsMenu(true);
        context = getContext();
        calendar = calendarView.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(mySelectDate);
        slist = calendarView.findViewById(R.id.slist);
        //-----------开始
        initView();
//        return inflater.inflate(R.layout.activity_calender,null,false);
        return calendarView;

    }




    private void initView() {
        mySQLiteOpenHelper = new sqLite(getContext());
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();


//        slist = findViewById(R.id.slist);
//        msBeanList = new ArrayList<>();
//        msBeanList.clear();
//        slist.setAdapter(new scheduleAdapter(this,msBeanList));
        //显示今天的日程
        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH)+1;//注意要+1，0表示1月份
        int day = time.get(Calendar.DAY_OF_MONTH);
        dateToday = year+"-"+month+"-"+day;

//        Bundle receive = getActivity().getIntent().getExtras();
//
//        if(receive != null) {
//            String sche = receive.getString("scheduleDetail");
//            String t = receive.getString("day");
//            String time2 = receive.getString("time");
//            int switchday = receive.getInt("switchday");
//            ContentValues values = new ContentValues();
//            scheduleBean scheduleBean = new scheduleBean();
//            scheduleBean.plan = sche;
//            scheduleBean.check = "false";
//            scheduleBean.time = time2;
//            scheduleBean.day = t;
//            mySQLiteOpenHelper.insert(scheduleBean);
            //dateToday = t;

            //showPlan(t);
            //---------------------
            //添加确认添加后返回页面默认显示添加的日期

//        }

//        if (getActivity().getIntent().getStringExtra("day") != null){
//            Log.e("数据：","aaaaaaaa");
//            int ooid = getActivity().getIntent().getIntExtra("id",0);
//            String ooplan = getActivity().getIntent().getStringExtra("sche");
//            String ooday = getActivity().getIntent().getStringExtra("day");
//            String ootime = getActivity().getIntent().getStringExtra("time");
//            String oocheck = getActivity().getIntent().getStringExtra("check");
//            ContentValues values = new ContentValues();
//            scheduleBean scheduleBean = new scheduleBean();
//            scheduleBean.plan = ooplan;
//            scheduleBean.check = oocheck;
//            scheduleBean.time = ootime;
//            scheduleBean.day = ooday;
//            mySQLiteOpenHelper.update(ooid,scheduleBean);
//        }


        //-----------------------------
        //初始添加数据
//        for (int i=0;i<3;i++) {
//            scheduleBean bean = new scheduleBean();
//            bean.check="false";
//            bean.plan="科技与文化报告";
//            bean.day = "2019-12-4";
//            bean.time = "11:11";
//            mySQLiteOpenHelper.insert(bean);
//        }
        showPlan(dateToday);

        //测试数据是否加入数据库+获取某天的是否正确
        //---------------------------------
//        Cursor cursor = mySQLiteOpenHelper.getOneday(dateToday);
////        cursor.moveToNext();
////        Cursor cursor2 = mySQLiteOpenHelper.getAll();
////        cursor2.moveToNext();
////        Log.i("全部数据",cursor2.getString(cursor.getColumnIndex("schedule")));
////        Log.i("数据库",cursor.getString(cursor.getColumnIndex("schedule")));
        //-------------------------------------



    }
    private CalendarView.OnDateChangeListener mySelectDate = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            dateToday = year+"-"+(month+1)+"-"+dayOfMonth;
            Toast.makeText(context, "你选择了:"+dateToday, Toast.LENGTH_SHORT).show();
            showPlan(dateToday);


        }
    };

    public void showPlan(String data){


        msBeanList = new ArrayList<>();
        //context = getContext();
        scheduleAdapter adapter = new scheduleAdapter(getContext(),msBeanList);
        slist.setAdapter(adapter);

         Log.i("数据：",data);
        final Cursor cursor = mySQLiteOpenHelper.getOneday(data);
        adapter.notifyDataSetChanged();
        msBeanList.clear();
        while(cursor.moveToNext()){
            //         Log.i("数据：",cursor.getString(cursor.getColumnIndex("schedule")));
            scheduleBean scheduleBean = new scheduleBean();
            scheduleBean.plan = cursor.getString(cursor.getColumnIndex("schedule"));
            scheduleBean.check = cursor.getString(cursor.getColumnIndex("checkd"));
            scheduleBean.time = cursor.getString(cursor.getColumnIndex("time"));
//            Log.i("数据数据",scheduleBean.plan.toString());
            msBeanList.add(scheduleBean);
        }
        adapter.notifyDataSetChanged();
        //cursor.close();
        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int select;
                cursor.moveToPosition(i);
                select = cursor.getInt(1);
                String oplan = msBeanList.get(i).plan;
                String oday = msBeanList.get(i).day;
                String ocheck = msBeanList.get(i).check;
                String otime = msBeanList.get(i).time;
                Intent intent = new Intent(getContext(), editplan.class);
                Bundle bundle = new Bundle();
                bundle.putString("oplan", oplan);
                bundle.putString("oday", oday);
                bundle.putString("otime",otime);
                bundle.putString("ocheck",ocheck);
                bundle.putInt("dbid",select);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
       // cursor.close();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
