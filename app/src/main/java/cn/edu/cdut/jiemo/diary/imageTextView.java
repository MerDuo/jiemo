package cn.edu.cdut.jiemo.diary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.cdut.jiemo.R;

public class imageTextView extends LinearLayout {
    private ImageView img;
    private TextView tv;

    int img_src;
    String tv_text;
    int tv_color;
    float tv_size;

    public imageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.image_text_view,this,true);
        img= (ImageView) findViewById(R.id.img);
        tv= (TextView) findViewById(R.id.text);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.imageTextView);
        tv_text = typedArray.getString(R.styleable.imageTextView_text);
        tv_size = typedArray.getDimension(R.styleable.imageTextView_text_size,0);
        tv_color = typedArray.getColor(R.styleable.imageTextView_text_color_nav,0x000000);
        img_src = typedArray.getResourceId(R.styleable.imageTextView_img_src,0);

        tv.setText(tv_text);
        tv.setTextColor(tv_color);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, tv_size);
        img.setImageResource(img_src);

        typedArray.recycle();
    }
    public void setImg(int res){
        img.setImageResource(res);
    }

    public  void setText(String text){
        tv.setText(text);
    }

    public void setTextColor(int color){
        tv.setTextColor(color);
    }

}
