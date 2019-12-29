package cn.edu.cdut.jiemo.myApplication;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

/**
 * Created by aaa on 2019/12/28.
 */

public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化skinmanager换肤操作
        SkinManager.getInstance().init(this);
    }
}
