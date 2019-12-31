package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.edu.cdut.jiemo.schedule.sqLite;

public class register extends AppCompatActivity {
    private sqLite mySQLiteOpenHelper;//数据库
    private SQLiteDatabase myDatabase;

    //用户名，密码，再次输入密码
    private EditText username,password,password2;
    //注册按钮
    private Button regisbtn;
    //获取的值
    private String uname,pword,pword2;
    //返回键
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }
    private void init(){
        back = findViewById(R.id.btnPtev);
        regisbtn = findViewById(R.id.regisbtn);
        username = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        mySQLiteOpenHelper = new sqLite(this);
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                register.this.finish();
            }
        });
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditString();
                if(TextUtils.isEmpty(uname)){
                    Toast.makeText(register.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;}

//                }else if(TextUtils.isEmpty(pword)){
                    else if(pword.length()<6){
                    Toast.makeText(register.this,"密码长度不少于六位",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!pword.equals(pword2)){
                    Toast.makeText(register.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
//
//                else if(isExistUserName(uname)){
                else if(mySQLiteOpenHelper.hasUser(uname)){
                    Toast.makeText(register.this,"该用户名已存在",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                    String md5Psw= MD5Utiles.md5(pword);
                    userBean user = new userBean();
                    user.userName = uname;
                    user.password = md5Psw;
                    mySQLiteOpenHelper.addUser(user);
                    //Intent data = new Intent(register.this,login.class);
                    Intent data = new Intent();
                    data.putExtra("username",uname);
                    data.putExtra("psw",pword);
                    setResult(RESULT_OK, data);
                    //startActivityForResult(data,1);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    register.this.finish();
                }
            }
        });
    }
    //sqlite获取注册信息

    //获取用户名，密码，密码2
    private void getEditString(){
        uname = username.getText().toString().trim();
        pword = password.getText().toString().trim();
        pword2 = password2.getText().toString().trim();
    }
    //--------------sharePrefence方法查询是否用户名已注册---------------------
//    private boolean isExistUserName(String userName){
//        boolean has_userName=false;
//        //mode_private SharedPreferences sp = getSharedPreferences( );
//        // "loginInfo", MODE_PRIVATE
//        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
//        //获取密码
//        String spPsw=sp.getString(userName, "");//传入用户名获取密码
//        //如果密码不为空则确实保存过这个用户名
//        if(!TextUtils.isEmpty(spPsw)) {
//            has_userName=true;
//        }
//        return has_userName;
//    }
    //---------------------------------------------------------------------------
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */
    private void saveRegisterInfo(String userName,String psw){
        String md5Psw = MD5Utiles.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }
}
