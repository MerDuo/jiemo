package cn.edu.cdut.jiemo.fragment;

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

public class Top2View extends RelativeLayout{
    private TextView tv;
    private String tv_text;
    private int tv_color;
    public Top2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top2_view,this,true);
        tv = (TextView) findViewById(R.id.top2_text);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Top2View);
        tv_text = typedArray.getString(R.styleable.Top2View_top2_text);
        tv_color = typedArray.getColor(R.styleable.Top2View_top2_color, 0x778899);

        tv.setText(tv_text);
        tv.setTextColor(tv_color);

        typedArray.recycle();
    }
    public void setText(String text){
        tv.setText(text);
    }

    private void setColor(int color){
        tv.setTextColor(color);
    }

}
