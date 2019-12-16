package cn.edu.cdut.jiemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.schedule.addplan;

public class calenderFragment extends Fragment {
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_calender,null,false);
    }
}
