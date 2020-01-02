package cn.edu.cdut.jiemo.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;
import cn.edu.cdut.jiemo.welcome;
public class inputSecuritypwd extends AppCompatActivity{
    SharedPreferences securityPwd;
    EditText inputSecurityPwd;
    Button securityPwdSubmit;
    SharedPreferences securityState;
    Boolean state;
    Boolean isOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_securitypwd);
        securityState=getSharedPreferences("securityState",MODE_PRIVATE);
        state=securityState.getBoolean("securityState",false);
        isOK=securityState.getBoolean("isOK",false);//是否验证成功

        if(state=false){
            setTheme(R.style.AppTheme_NoActionBar);
        }
    }

    public void submitOnclick(View view){
        securityPwd=getSharedPreferences("securityPwd",MODE_PRIVATE);
        inputSecurityPwd=findViewById(R.id.inputsecuritypwd);
        securityPwdSubmit=findViewById(R.id.submitbtn2);

        String content=securityPwd.getString("securityPwd","");
        String etString=inputSecurityPwd.getText().toString();
        if(content.equals(etString)){

            Intent intent = new Intent(inputSecuritypwd.this, welcome.class);
            startActivity(intent);

            SharedPreferences.Editor editor=securityState.edit();
            editor.putBoolean("isOK",true);
            editor.commit();
        }else{
            Toast.makeText(getApplicationContext(),"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
        }
    }



}
