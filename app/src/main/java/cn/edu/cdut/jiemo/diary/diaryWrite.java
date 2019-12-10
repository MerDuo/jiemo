package cn.edu.cdut.jiemo.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;

public class diaryWrite extends Activity implements View.OnClickListener {

    EditText diary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);

        // 添加返回事件
        ImageView returnBTN = findViewById(R.id.returnbtn);
        returnBTN.setOnClickListener(this);

        // 删除
        ImageView diarybtn_del = findViewById(R.id.del);
        diary = findViewById(R.id.edit_diary);
        diarybtn_del.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.returnbtn:
                Intent intent = new Intent();
                intent.setClass(diaryWrite.this, MainActivity.class);
                startActivity(intent);
            case R.id.del:
                diary.setText("");
        }


    }
}
