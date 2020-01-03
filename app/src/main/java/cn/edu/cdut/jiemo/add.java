package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.diary.diaryWrite;
import cn.edu.cdut.jiemo.schedule.addplan;
public class add extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_add);
    }

    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.diaryImg1:
                Intent intent1 = new Intent();
                intent1.setClass(add.this, diaryWrite.class);
                startActivity(intent1);
            case R.id.diaryTxt:
                Intent intent2 = new Intent();
                intent2.setClass(add.this, diaryWrite.class);
                startActivity(intent2);
//            case R.id.calImg1:
//                Intent intent3 = new Intent();
//                intent3.setClass(add.this, addplan.class);
//                startActivity(intent3);
//            case R.id.calTxt:
//                Intent intent4 = new Intent();
//                intent4.setClass(add.this, addplan.class);
//                startActivity(intent4);
        }
    }
    public void returnimg(View v){
//        Intent intent = new Intent();
//        intent.setClass(add.this, MainActivity.class);
//        startActivity(intent);
        add.this.finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
