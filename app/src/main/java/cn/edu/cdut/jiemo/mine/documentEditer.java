package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.fragment.Top2View;
import cn.edu.cdut.jiemo.fragment.titleFragment;

/**
 * Created by aaa on 2019/12/3.
 */

public class documentEditer extends AppCompatActivity {
    private EditText editText;
    private String data;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String dataKey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_editer);

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

        //设置标题栏内容
        Intent intent=getIntent();
        String title=intent.getStringExtra("titleText");
        Top2View titlefragment=(Top2View)findViewById(R.id.title_fragement);
        titlefragment.setText(title);

        //获取用户数据
        dataKey=intent.getStringExtra("dataKey");
        pref=getSharedPreferences("data",MODE_PRIVATE);
        data=pref.getString(dataKey,"");
        editText=findViewById(R.id.edit);
        editText.setText(data);

        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String changeData=editText.getText().toString();
                Log.d("aaaa", "onClick: "+changeData);
                Log.d("aaaa", "onClick: "+dataKey);
                editor=pref.edit();
                if (data.equals("age")){
                    int agechange=Integer.parseInt(changeData);
                    editor.putInt("age",agechange);
                    editor.apply();
                }else{
                    editor.putString(dataKey,changeData);
                    editor.apply();
                }
                finish();
            }
        });
    }
}
