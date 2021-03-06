package cn.edu.cdut.jiemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import cn.edu.cdut.jiemo.schedule.sqLite;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

public class login extends AppCompatActivity{
    private sqLite mySQLiteOpenHelper;//数据库
    private SQLiteDatabase myDatabase;
    private TextView tv_register,tv_find_psw;//显示的注册，找回密码
    private Button btn_login;//登录按钮
    private String userName,psw,spPsw;//获取的用户名，密码，加密密码
    private EditText et_user_name,et_psw;//编辑框

    //返回键
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);

        setContentView(R.layout.activity_login);
        //设置此界面为竖屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

    }
    private void init() {
//        back = findViewById(R.id.btnPtev);
        tv_register = findViewById(R.id.top3_text_r);
        //tv_find_psw=findViewById(R.id.tv_find_psw);
        btn_login = findViewById(R.id.loginbtn);
        et_user_name = findViewById(R.id.phone);
        et_psw = findViewById(R.id.password);
        mySQLiteOpenHelper = new sqLite(this);
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //返回键
//                login.this.finish();
//            }
//        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent=new Intent(login.this,register.class);
                startActivityForResult(intent, 1);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始登录，获取用户名和密码 getText().toString().trim();
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                //对当前用户输入的密码进行MD5加密再进行比对判断, MD5Utils.md5( ); psw 进行加密判断是否一致
                String md5Psw= MD5Utiles.md5(psw);
                // md5Psw ; spPsw 为 根据从SharedPreferences中用户名读取密码
                //-------------sharePrefence方法------------------//
//                // 定义方法 readPsw为了读取用户名，得到密码
//                spPsw=readPsw(userName);
                //------------------------------------------------//
                // TextUtils.isEmpty
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)) {
                    Toast.makeText(login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                    // md5Psw.equals(); 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
//                }else if(md5Psw.equals(spPsw)){
                }else if(!mySQLiteOpenHelper.hasUser(userName)) {
                    Toast.makeText(login.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                }else if(mySQLiteOpenHelper.login(userName,md5Psw)){
                    //一致登录成功qqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss66ssssssssssssssssssssssssss
                    Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //初始化用户数据Name
                    getUser().initUser(getApplicationContext(),userName);
                    //设置用户主题
                    if(getUser().getTheme()!=null){
                        SkinManager.getInstance().changeSkin(getUser().getTheme());
                        Log.d("aaa","theme"+getUser().getTheme());
                    }
                    int uid=mySQLiteOpenHelper.getUid(userName);
                    //保存登录状态，在界面保存登录的用户名 定义个方法 saveLoginStatus boolean 状态 , userName 用户名;
                    saveLoginStatus(true, userName,uid);
                    //登录成功后关闭此页面进入主页
                    Intent data=new Intent();
                    //datad.putExtra( ); name , value ;
                    data.putExtra("isLogin",true);
                    //RESULT_OK为Activity系统常量，状态码为-1
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    setResult(RESULT_OK,data);
                    //销毁登录界面
                    login.this.finish();
                    //跳转到主界面，登录成功的状态传递到 MainActivity 中
                    startActivity(new Intent(login.this, MainActivity.class));
                    return;
                }else{
                    Toast.makeText(login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    falseLogin(false);
                    return;
                }
            }
        });

    }

    /**
     *从SharedPreferences中根据用户名读取密码
     */

    private String readPsw(String userName){
        //getSharedPreferences("loginInfo",MODE_PRIVATE);
        //"loginInfo",mode_private; MODE_PRIVATE表示可以继续写入
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //sp.getString() userName, "";
        return sp.getString(userName , "");
    }
    /**
     *保存登录状态和登录用户名到SharedPreferences中
     */
    private void saveLoginStatus(boolean status,String userName,int id){
        //saveLoginStatus(true, userName);
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        editor.putInt("userId",id);
        //提交修改
        editor.commit();
    }
    private void falseLogin(boolean status){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        // 删除用户名
        editor.putString("loginUserName", "");
        //提交修改
        editor.commit();
    }
    /**
     * 注册成功的数据返回至此
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    @Override
    //显示数据， onActivityResult
    //startActivityForResult(intent, 1); 从注册界面中获取数据
    //int requestCode , int resultCode , Intent data
    // LoginActivity -> startActivityForResult -> onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            String userName=data.getStringExtra("username");
            String psw = data.getStringExtra("psw");
            if(!TextUtils.isEmpty(userName)){
                //设置用户名到 et_user_name 控件
                et_user_name.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                et_user_name.setSelection(userName.length());
            }
            if(!TextUtils.isEmpty(psw)){
                //设置用户名到 et_user_name 控件
                et_psw.setText(psw);
                //et_user_name控件的setSelection()方法来设置光标位置
                //et_psw.setSelection(psw.length());
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }
}
