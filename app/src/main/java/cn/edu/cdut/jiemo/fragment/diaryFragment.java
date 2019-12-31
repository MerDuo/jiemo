package cn.edu.cdut.jiemo.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.diary.DiaryBean;
import cn.edu.cdut.jiemo.diary.TimeLineAdapter;
import cn.edu.cdut.jiemo.diary.TimeLineBean;
import cn.edu.cdut.jiemo.schedule.sqLite;

//public class diaryFragment extends ListFragment {
public class diaryFragment extends Fragment {

    private sqLite mySQLiteOpenHelper;
    private SQLiteDatabase myDatabase;
    private List<DiaryBean> msBeanList = new ArrayList<>();

    private List<TimeLineBean> timelineList = new ArrayList<>();
    ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 日志界面时间轴
//        View diaryPageview = getLayoutInflater().inflate(R.layout.diarypage,null);
//        View diaryPageview = inflater.inflate(R.layout.diarypage,container,false);
//       ListView listView = diaryPageview.findViewById(R.id.list);
//        ListView listView = findViewById(R.id.timeline);
//        initData();
//        TimeLineAdapter timelineAdapter = new TimeLineAdapter(getActivity(), R.layout.timeline_item, timelineList);
        //listView.setAdapter(timelineAdapter);
        //setListAdapter(timelineAdapter);
//        initData();
//        TimeLineAdapter timelineAdapter = new TimeLineAdapter(getActivity(), R.layout.timeline_item, timelineList);
//        setListAdapter(timelineAdapter);
//        setListViewHeight(listView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View diaryPageview = inflater.inflate(R.layout.diary_page, container, false);
        listView = diaryPageview.findViewById(android.R.id.list);
        initData();
        TimeLineAdapter timelineAdapter = new TimeLineAdapter(getActivity(), R.layout.timeline_item, timelineList);
        //setListAdapter(timelineAdapter);
        listView.setAdapter(timelineAdapter);
        setListViewHeight(listView);
        //initData();
//        return inflater.inflate(R.layout.diary_page, container, false);
        return diaryPageview;
    }

    public void initData() {
        mySQLiteOpenHelper = new sqLite(getContext());
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();

        final Cursor cursor = mySQLiteOpenHelper.getAllDiary();
        //adapter.notifyDataSetChanged();
        msBeanList.clear();
        while(cursor.moveToNext()){
            Log.e("data：",cursor.getString(cursor.getColumnIndex("title")));
            DiaryBean diaryBean = new DiaryBean();
            diaryBean.title = cursor.getString(cursor.getColumnIndex("title"));
            diaryBean.diarycategory = cursor.getString(cursor.getColumnIndex("diarycategory"));
            diaryBean.diarydate = cursor.getString(cursor.getColumnIndex("diarydate"));
            diaryBean.diarytime = cursor.getString(cursor.getColumnIndex("diarytime"));
            msBeanList.add(diaryBean);
        }

        for (int i=0;i<msBeanList.size();i++) {
            int n = msBeanList.size() - i-1;
            cursor.moveToPosition(n);
            //select = cursor.getInt(1);
            String title = msBeanList.get(n).title;
            String diarycategory = msBeanList.get(n).diarycategory;
            String diarydate = msBeanList.get(n).diarydate;
            String diarytime = msBeanList.get(n).diarytime;
            //Log.e("cursor:",diarycategory);
            timelineList.add(new TimeLineBean(n, diarydate, diarytime, title, diarycategory));
        }

////            String oplan = msBeanList.get(i).plan;
////            String oday = msBeanList.get(i).day;
////            String ocheck = msBeanList.get(i).check;
////            String otime = msBeanList.get(i).time;
//            Intent intent = new Intent(calender.this, editplan.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("oplan", oplan);
//            bundle.putString("oday", oday);
//            bundle.putString("otime", otime);
//            bundle.putString("ocheck", ocheck);
//            bundle.putInt("dbid", select);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        }
//
////        timelineList.add(new TimeLineBean("2019-12-05", R.drawable.pic1, "test","diary"));
//        timelineList.add(new TimeLineBean(1,"2019-12-05", "个人日志","diary"));
//        timelineList.add(new TimeLineBean(2,"2019-12-01", "小记事","memo"));
//        timelineList.add(new TimeLineBean(3,"2019-11-25", "一些感想","essay"));
//        timelineList.add(new TimeLineBean(4,"2019-11-15", "杂七杂八的东西","others"));
//        timelineList.add(new TimeLineBean(5,"2019-11-05", "不知道是什么","diary"));
    }

    public void setListViewHeight(ListView listview){
        ListAdapter listAdapter = listview.getAdapter();
        if(listAdapter == null){
            return;
        }
        int listHeight = 0;
        for(int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i,listview,null);
            listItem.measure(0,0);
            listHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = listHeight + listview.getDividerHeight() * (listview.getCount()-1);
        listview.setLayoutParams(params);

    }

}
