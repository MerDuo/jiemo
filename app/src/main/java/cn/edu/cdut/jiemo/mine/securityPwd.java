package cn.edu.cdut.jiemo.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;

public class securityPwd extends AppCompatActivity {

    EditText editText1;
    EditText editText2;

    String ed1;
    String ed2;
    SharedPreferences pwd;

    Boolean isOK;
    SharedPreferences securityState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pwd);

        pwd=getSharedPreferences("securityPwd",MODE_PRIVATE);
        editText1=findViewById(R.id.securitypwd);
        editText2=findViewById(R.id.securitypwd2);


    }
    public void submit(View view){
        ed1=editText1.getText().toString();
        ed2=editText2.getText().toString();

        if(ed1.equals(ed2)){
            //提交密码
            SharedPreferences.Editor editor=pwd.edit();
            editor.putString("securityPwd",ed1);
            editor.commit();

            Intent intent = new Intent(securityPwd.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
