package cn.edu.cdut.jiemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.changeskin.SkinManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.edu.cdut.jiemo.R;

public class TopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//不用
//        SkinManager.getInstance().register(this.getActivity());
//        View calendarView = inflater.inflate(R.layout.activity_calender,null,false);
        return inflater.inflate(R.layout.top,container,false);
    }
}