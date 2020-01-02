package cn.edu.cdut.jiemo.myApplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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
    //这是一个重新方法
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
