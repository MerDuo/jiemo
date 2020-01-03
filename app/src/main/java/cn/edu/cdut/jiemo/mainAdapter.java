package cn.edu.cdut.jiemo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import cn.edu.cdut.jiemo.mainBean.scheduleBean;

//public class mainAdapter extends ArrayAdapter<scheduleBean> {
//    private int resourceId;
//
//    public mainAdapter(@NonNull Context context, int resource, List<scheduleBean> schdule) {
//        super(context, resource,schdule);
//        resourceId=resource;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        scheduleBean schedule=getItem(position);   //获取当前项的实例
//        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        TextView listTitle=view.findViewById(R.id.list_title);
//        TextView listTime=view.findViewById(R.id.list_time);
//        listTitle.setText(schedule.getStime());
//        listTime.setText(schedule.getStitle());
//        return view;
//    }
//}

public class mainAdapter extends BaseAdapter {
    Context context;
    private List<cn.edu.cdut.jiemo.mainBean.scheduleBean> scheduleBeanList;
    private LayoutInflater mlayoutInflater;
    private Context mcontext;
    public mainAdapter(Context context, int main_item, List<scheduleBean> schduleList) {
        scheduleBeanList = schduleList;
        mcontext = context;
        mlayoutInflater = LayoutInflater.from(context);
    }

    public mainAdapter(Context context, List<scheduleBean> schduleList) {
        this.mcontext=context;
        this.scheduleBeanList=schduleList;
    }

    @Override
    public int getCount() {
        return scheduleBeanList.size();
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
        final cn.edu.cdut.jiemo.mainAdapter.viewHolder viewHolder;
        if(view == null){
            viewHolder = new cn.edu.cdut.jiemo.mainAdapter.viewHolder();
            view = mlayoutInflater.inflate(R.layout.main_item,null);
            viewHolder.stime = (TextView)view.findViewById(R.id.list_time);
            viewHolder.stitle = (TextView)view.findViewById(R.id.list_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (cn.edu.cdut.jiemo.mainAdapter.viewHolder)view.getTag();
        }
        cn.edu.cdut.jiemo.mainBean.scheduleBean bean = scheduleBeanList.get(i);
        viewHolder.stime.setText(bean.getStime());
        viewHolder.stitle.setText(bean.getStitle());

        return view;
    }

    //    public synchronized void refreshAdapter(List<scheduleBean> list){
//        mlist =
//        notifyDataSetChanged();
//    }
    private static class viewHolder{
        public TextView stime;
        public TextView stitle;
    }
}
