package cn.edu.cdut.jiemo;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by aaa on 2019/10/28.
 */

public class titleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        View rootView=inflater.inflate(R.layout.title_fragment,container,false);

        //点击返回按钮
        ImageView backButton=rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Activity activity=getActivity();
                activity.finish();
            }
        });
        return rootView;
    }
    //设置导航栏的size
    public void setButtompadding(int size){
        ViewGroup titleBar= getView().findViewById(R.id.titleFragmentcl);
        int buttom=dip2px(this.getContext(), size);
        titleBar.setPadding(0,0,0,buttom);
    }
    //设置导航栏的标题
    public void setTitle(String title){
        TextView titleText= getView().findViewById(R.id.titleText);
        titleText.setText(title);
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



