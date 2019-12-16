package cn.edu.cdut.jiemo.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cn.edu.cdut.jiemo.R;

public class TimeLineAdapter extends ArrayAdapter {

    private final int imgId;

    public TimeLineAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        imgId = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TimeLineBean tb = (TimeLineBean) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(imgId,parent,false);
        LinearLayout linearLayout = view.findViewById(R.id.timeline_view);
        TextView tv_time = view.findViewById(R.id.headtime);
        ImageView tv_img = view.findViewById(R.id.headimage);
        TextView tv_text = view.findViewById(R.id.headtext);
        assert tb != null;
        tv_time.setText(tb.getTime());
        tv_img.setImageResource(tb.getImgId());
        tv_text.setText(tb.getText());
        return view;
    }

}
