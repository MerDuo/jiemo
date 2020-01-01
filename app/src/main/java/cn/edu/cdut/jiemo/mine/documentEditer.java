package cn.edu.cdut.jiemo.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.diary.Top2View;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

/**
 * Created by aaa on 2019/12/3.
 */

public class documentEditer extends AppCompatActivity {
    private EditText editText;
//    private String data;
//    private SharedPreferences pref;
//    private SharedPreferences.Editor editor;
    String changeData;
    private String dataKey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
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

        //获取文本编辑控件
        Button submit=findViewById(R.id.submit);

        //获取用户数据
        dataKey=intent.getStringExtra("dataKey");
        editText=findViewById(R.id.edit);
        switch (dataKey){
            case "name":
                //设置内容
                editText.setText(getUser().getUserName());
                //更改用户名（账号）
                submit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        changeData=editText.getText().toString();
                        //是否为空判断
                        if(changeData.equals("")){
                            Toast.makeText(documentEditer.this, "请输入内容", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(getUser().changeAccount(changeData)){
                            //更改sharepreference中的name
                            SharedPreferences pref=getSharedPreferences("loginInfo",MODE_PRIVATE);
                            pref.edit().putString("loginUserName",changeData).apply();
                            Toast.makeText(documentEditer.this, "更改成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(documentEditer.this, "改用户名已经存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case "age":
                //更改年龄
                editText.setText(String.valueOf(getUser().getAge()));
                submit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        changeData=editText.getText().toString();
                        //是否为空判断
                        if(changeData.equals("")){
                            Toast.makeText(documentEditer.this, "请输入内容", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(isNumber(changeData)){
                            getUser().changeAge(Integer.parseInt(changeData));
                            Toast.makeText(documentEditer.this, "更改成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(documentEditer.this, "必须输入数字哦", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case "sex":
                //更改性别
                editText.setText(getUser().getSex());
                submit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        changeData=editText.getText().toString();
                        //是否为空判断
                        if(changeData.equals("")){
                            Toast.makeText(documentEditer.this, "请输入内容", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(getUser().changeSex(changeData)&&(changeData.equals("女")|changeData.equals("男"))){
                            Toast.makeText(documentEditer.this, "更改成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(documentEditer.this, "请输入男/女", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case "signature":
                //更改签名
                editText.setText(getUser().getSignature());
                submit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        changeData=editText.getText().toString();
                        //是否为空判断
                        if(changeData.equals("")){
                            Toast.makeText(documentEditer.this, "请输入内容", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(getUser().changeSignature(changeData)){
                            Toast.makeText(documentEditer.this, "更改成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(documentEditer.this, "更改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
