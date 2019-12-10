package cn.edu.cdut.jiemo.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.edu.cdut.jiemo.R;

public class scheduleAdapter extends BaseAdapter {
    private List<scheduleBean> mList;
    private Context mcontext;
    private LayoutInflater mlayoutInflater;
    public scheduleAdapter(Context context,List<scheduleBean> list){
        mList = list;
        mcontext = context;
        mlayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final viewHolder viewHolder;
        if(view == null){
            viewHolder = new viewHolder();
            view = mlayoutInflater.inflate(R.layout.scheduleitem,null);
            viewHolder.mcheck = (CheckBox)view.findViewById(R.id.check);
            viewHolder.mtext = (TextView)view.findViewById(R.id.schedule);
            viewHolder.mtime = (TextView)view.findViewById(R.id.time);
            view.setTag(viewHolder);
        }else{
            viewHolder = (viewHolder)view.getTag();
        }
        scheduleBean bean = mList.get(i);
        if(bean.check == "true"){
            viewHolder.mcheck.setChecked(true);
        }else{
            viewHolder.mcheck.setChecked(false);
        }

        viewHolder.mtext.setText(bean.plan);
        viewHolder.mtime.setText(bean.time);

        return view;
    }

    //    public synchronized void refreshAdapter(List<scheduleBean> list){
//        mlist =
//        notifyDataSetChanged();
//    }
    private static class viewHolder{
        public CheckBox mcheck;
        public TextView mtext;
        public TextView mtime;
    }
}
