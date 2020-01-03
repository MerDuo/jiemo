package cn.edu.cdut.jiemo.diary;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.cdut.jiemo.R;

public class Top3View extends RelativeLayout {
    private TextView tv;
    private TextView tv_r;
    private String tv_text;
    private int tv_color;
    private String tv_text_r;
    private int tv_color_r;
    public Top3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top3view,this,true);
        tv = (TextView) findViewById(R.id.top3_text);
        tv_r = (TextView) findViewById(R.id.top3_text_r);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Top3View);
        tv_text = typedArray.getString(R.styleable.Top3View_top3_text);
        tv_color = typedArray.getColor(R.styleable.Top3View_top3_color, 0x778899);
        tv_text_r = typedArray.getString(R.styleable.Top3View_top3_text_r);
        tv_color_r = typedArray.getColor(R.styleable.Top3View_top3_color_r, 0x778899);

        tv.setText(tv_text);
        tv.setTextColor(tv_color);

        tv_r.setText(tv_text_r);
        tv_r.setTextColor(tv_color_r);

        typedArray.recycle();
        ImageView return_btn = findViewById(R.id.returnbtn);
        return_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"close",Toast.LENGTH_SHORT).show();
                ((Activity)getContext()).finish();
            }
        });
    }
    public void setText(String text){
        tv.setText(text);
    }

    private void setColor(int color){
        tv.setTextColor(color);
    }
}
