package cn.edu.cdut.jiemo.mine;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.titleFragment;

/**
 * Created by aaa on 2019/12/17.
 */

public class aboutUs extends AppCompatActivity {
    //UI控件
    ImageView appImage;
    TextView appName;
    TextView appVersion;
    personalItemActivity QQ;
    personalItemActivity weixin;
    personalItemActivity weibo;
    personalItemActivity email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

        //UI控件初始化
        appImage=findViewById(R.id.appImage);
        appName=findViewById(R.id.appName);
        appVersion=findViewById(R.id.appVersion);
        QQ=findViewById(R.id.QQ_item);
        weixin=findViewById(R.id.weixin_item);
        weibo=findViewById(R.id.weibo_item);
        email=findViewById(R.id.email_item);

        //消除右边的箭头符号
        QQ.setRightIconSize(0);
        weixin.setRightIconSize(0);
        weibo.setRightIconSize(0);
        email.setRightIconSize(0);

        // return
//        ImageView return_btn = findViewById(R.id.returnbtn);
//        return_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


    }

}
