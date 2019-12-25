package cn.edu.cdut.jiemo.diary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cn.edu.cdut.jiemo.MainActivity;
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
        final TimeLineBean tb = (TimeLineBean) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(imgId,parent,false);
        LinearLayout linearLayout = view.findViewById(R.id.timeline_view);
        TextView tv_time = view.findViewById(R.id.headtime);
        ImageView tv_img = view.findViewById(R.id.headimage);
        TextView tv_text = view.findViewById(R.id.headtext);

        assert tb != null;
        tv_time.setText(tb.getTime());
        switch (tb.getCategory()){
            case "diary":
                tv_img.setImageResource(R.drawable.diary);
                break;
            case "memo":
                tv_img.setImageResource(R.drawable.memo);
                break;
            case "essay":
                tv_img.setImageResource(R.drawable.essay);
                break;
            case "others":
                tv_img.setImageResource(R.drawable.others);
                break;
        }
//        tv_img.setImageResource(tb.getImgId());
        tv_text.setText(tb.getText());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"得到id："+tb.getId(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("id",tb.getId());
                Intent intent = new Intent(getContext(),diaryWrite.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

}
