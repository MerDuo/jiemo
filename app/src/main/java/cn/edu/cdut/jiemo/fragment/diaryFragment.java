package cn.edu.cdut.jiemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.diary.TimeLineAdapter;
import cn.edu.cdut.jiemo.diary.TimeLineBean;

public class diaryFragment extends ListFragment {

    private List<TimeLineBean> timelineList = new ArrayList<>();
    ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 日志界面时间轴
//        View diaryPageview = getLayoutInflater().inflate(R.layout.diarypage,null);
//        View diaryPageview = inflater.inflate(R.layout.diarypage,container,false);
//       ListView listView = diaryPageview.findViewById(R.id.list);
//        ListView listView = findViewById(R.id.timeline);
        initData();
        TimeLineAdapter timelineAdapter = new TimeLineAdapter(getActivity(), R.layout.timeline_item, timelineList);
        //listView.setAdapter(timelineAdapter);
        setListAdapter(timelineAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View diaryPageview = inflater.inflate(R.layout.diary_page, container, false);
        listView = diaryPageview.findViewById(android.R.id.list);
        //initData();
        return inflater.inflate(R.layout.diary_page, container, false);
    }

    private void initData() {
        timelineList.add(new TimeLineBean("2019-12-05", R.drawable.pic1, "test"));
        timelineList.add(new TimeLineBean("2019-12-01", R.drawable.pic2, "this is a text"));
        timelineList.add(new TimeLineBean("2019-11-25", R.drawable.pic3, "this is a text could be wrong"));
        timelineList.add(new TimeLineBean("2019-11-15", R.drawable.pic4, "this is a diary"));
        timelineList.add(new TimeLineBean("2019-11-05", R.drawable.pic1, "this is a text which may bo too long to show and if it's showed it will be great !"));
    }
}
