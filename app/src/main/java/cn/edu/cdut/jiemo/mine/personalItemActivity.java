package cn.edu.cdut.jiemo.mine;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.jar.Attributes;

import cn.edu.cdut.jiemo.R;

public class personalItemActivity extends LinearLayout {

    public personalItemActivity(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public personalItemActivity(Context context, AttributeSet attrs) {
        super(context,attrs);
        //获取控件属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.personal_item);
        LayoutInflater.from(context).inflate(R.layout.personal_item,this);
        Boolean isShowBottomLine = ta.getBoolean(R.styleable.personal_item_show_bottom_line, true);//得到是否显示底部下划线属性
        Boolean isShowLeftIcon = ta.getBoolean(R.styleable.personal_item_show_left_icon, true);//得到是否显示左侧图标属性标识
        Boolean isShowRightArrow = ta.getBoolean(R.styleable.personal_item_show_right_arrow, true);//得到是否显示右侧图标属性标识

        View leftIcon=findViewById(R.id.item_img);
        TextView leftTitle=findViewById(R.id.item_text);
        View bottomLine=findViewById(R.id.buttom_line);
        View rightArrow=findViewById(R.id.right_icon);
        TextView rightText=findViewById(R.id.right_text);

        leftIcon.setBackground(ta.getDrawable(R.styleable.personal_item_left_icon));//设置左侧图标
        leftTitle.setText(ta.getString(R.styleable.personal_item_left_text));//设置左侧标题文字
        leftIcon.setVisibility(isShowLeftIcon ? View.VISIBLE : View.INVISIBLE);//设置左侧图标是否显示
        bottomLine.setVisibility(isShowBottomLine ? View.VISIBLE : View.INVISIBLE);//设置底部图标是否显示
        rightArrow.setVisibility(isShowRightArrow ? View.VISIBLE : View.INVISIBLE);//设置右侧箭头图标是否显示
        rightText.setText(ta.getString(R.styleable.personal_item_right_text));//设置右边文字

    }
    //设置左边的图标的大小
    public void setLeftIconSize(int size){
        ImageView imageView=findViewById(R.id.item_img);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.height= dip2px(this.getContext(), size);
        imageView.setLayoutParams(params);
    }
    //设置右边文字的内容
    public void setRightText(String rightText){
        TextView right_text=findViewById(R.id.right_text);
        right_text.setText(rightText);//设置右侧文字
    }

    //设置右边图标的的大小
    public void setRightIconSize(int size){
        ImageView imageView=findViewById(R.id.right_icon);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.height= dip2px(this.getContext(), size);
        imageView.setLayoutParams(params);
    }

    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    private int dip2px(Context context, float dipValue)
    {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
}
