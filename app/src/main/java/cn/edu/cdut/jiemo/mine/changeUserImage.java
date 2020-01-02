package cn.edu.cdut.jiemo.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhy.changeskin.SkinManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.cdut.jiemo.R;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

/**
 * Created by aaa on 2020/1/2.
 */

public class changeUserImage extends AppCompatActivity {

    public static final int TAKE_PHOTO=1;
    ImageView userImage;
    //相册
    Button b1;
    //拍照
    Button b2;
    //用户名
    String userName=getUser().getUserName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //界面初始化
        setlayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }

    //界面初始化设置操作
    protected void setlayout(){

        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.change_userimg);

        //获取控件
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }
        //点击事件
        //
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });


    }

    //

}
