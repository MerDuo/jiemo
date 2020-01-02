package cn.edu.cdut.jiemo.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.schedule.addplan;
import cn.edu.cdut.jiemo.schedule.sqLite;

public class securitySetting extends AppCompatActivity{

    SharedPreferences pwd;
    SharedPreferences state;
    boolean securitystate;
    boolean isOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_security_setting);

        final Switch btn_switch = findViewById(R.id.btn_switch);
        pwd=getSharedPreferences("securityPwd",MODE_PRIVATE);
        state=getSharedPreferences("securityState",MODE_PRIVATE);

        //开关状态记忆
        securitystate=state.getBoolean("securityState",false);
        isOK=state.getBoolean("isOK",false);
        btn_switch.setChecked(securitystate);
        //开关点击事件
        btn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (securitystate==false && isChecked){
                    //Todo
                    Toast.makeText(getApplicationContext(), "app安全保护已开启", Toast.LENGTH_SHORT).show();

                    //提交状态
                    SharedPreferences.Editor editor1=state.edit();
                    editor1.putBoolean("securityState",true);
                    editor1.commit();


                    String content=pwd.getString("securityPwd","");//验证是否未设置锁屏密码
                    if(content.equals("")){
                        Intent intent = new Intent(securitySetting.this, securityPwd.class);
                        startActivity(intent);
                    }

                }else {
                    //Todo
                    Toast.makeText(getApplicationContext(), "app安全保护已关闭", Toast.LENGTH_SHORT).show();

                    //提交状态
                    SharedPreferences.Editor editor2=state.edit();
                    editor2.putBoolean("securityState",false);
                    editor2.commit();

                }
            }
        });

    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
