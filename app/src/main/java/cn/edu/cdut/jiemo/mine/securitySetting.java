package cn.edu.cdut.jiemo.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.R;

public class securitySetting extends AppCompatActivity implements View.OnClickListener {

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_security_setting);

        findViewById(R.id.btn_switch).setOnClickListener(this);
    }

    public void onClick(View view) {
        Toast toast = null;
        Switch btn_switch = findViewById(R.id.btn_switch);
        btn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Todo
                    Toast.makeText(getApplicationContext(), "app安全保护已开启", Toast.LENGTH_SHORT).show();
                }else {
                    //Todo
                    Toast.makeText(getApplicationContext(), "app安全保护已关闭", Toast.LENGTH_SHORT).show();
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
