package cn.edu.cdut.jiemo.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.R;

public class accountManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_account_manage);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
