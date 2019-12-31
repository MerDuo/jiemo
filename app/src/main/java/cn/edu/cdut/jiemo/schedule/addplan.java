package cn.edu.cdut.jiemo.schedule;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;

public class addplan extends AppCompatActivity {
    private Context context;
    private ImageView back;//返回键
    private EditText title;//标题
    private Switch check;//是否全天
    private RelativeLayout swiday;//点击选择日期
    private RelativeLayout switime;//点击选择时间
    private TextView mday;//显示日期
    private TextView time;//显示时间
    private Button addbtn;
    private sqLite mySQLiteOpenHelper;
    private SQLiteDatabase myDatabase;
    private int switchday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.activity_addplan);
        init();
        setDay();
        addTime();
        addPlan();
    }
    public void init(){
        context = this;
        title = findViewById(R.id.title);
        back = findViewById(R.id.returnbtn);
        check = findViewById(R.id.switchday);
        swiday = findViewById(R.id.stwday);
        switime = findViewById(R.id.stwtime);
        mday = findViewById(R.id.text1);
        time = findViewById(R.id.text2);
        addbtn =findViewById(R.id.add);
        mySQLiteOpenHelper = new sqLite(this);
        myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                addplan.this.finish();
            }
        });
        //进入添加日程界面显示当前的日期
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mday.setText(sdf.format(today.getTime()));//月份的展示
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
        time.setText(sdf2.format(today.getTime()));
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Calendar today = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    mday.setText(sdf.format(today.getTime()));//月份的展示
                    time.setText(sdf.format(today.getTime()));
                } else {
                    mday.setText(" ");
                    time.setText(" ");
                    //非选中时 do some thing
                    //setTime();
                }

            }
        });
    }
    public void addPlan(){
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = title.getText().toString();
                String t = mday.getText().toString();
                String ti = time.getText().toString();
                String check = "false";


//                Log.i("标题：",text);
//                Log.i("shijian：",t);
                if(!TextUtils.isEmpty(text) && !TextUtils.isEmpty(t) && !TextUtils.isEmpty(ti)) {
                    scheduleBean scheduleBean = new scheduleBean();
                    scheduleBean.plan = text;
                    scheduleBean.check = "false";
                    scheduleBean.time = ti;
                    scheduleBean.day = t;
                    mySQLiteOpenHelper.insert(scheduleBean);
                    //-------------------在main中添加---------------
                    Intent intent = new Intent(addplan.this, MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("scheduleDetail", title.getText().toString());
//                    bundle.putString("day", t);
//                    bundle.putString("time",ti);
//                    bundle.putInt("switchday",switchday);
//
//                    intent.putExtras(bundle);
                    intent.putExtra("page",1);
                    startActivity(intent);
                    //-------------------------------------------------
                }
                else{
                    Toast.makeText(context, "还未添加内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //选择日期
    public void setDay() {
        final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

        swiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcalendar = Calendar.getInstance();
                int year = mcalendar.get(Calendar.YEAR);
                final int month = mcalendar.get(Calendar.MONTH);
                final int day = mcalendar.get(Calendar.DATE);
                mday.setText(year+"-"+month+"-"+day);
                //mday.setText("选择时间：" + year + "年" + month + "月" + day + "日");
                new DatePickerDialog(addplan.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    //实现监听方法

                    @Override

                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        //设置文本显示内容，i为年，i1为月，i2为日
                        switchday = i2;
                        mday.setText(i + "-" + (i1 + 1) + "-" + i2 );
                        //-----------------------------
                        //添加的设置时间
                        if(check.isChecked()){
                            time.setText(i + "-" + (i1 + 1) + "-" + i2);
                        }
                    }

                }, year, month, day).show();//记得使用show才能显示悬浮窗
            }
        });
    }

    //选择时间
    public void addTime(){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        //-------------------------------
        //--添加的
        if(!check.isChecked()) {
            switime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new TimePickerDialog(addplan.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new TimePickerDialog.OnTimeSetListener() {

                        //实现监听方法

                        @Override

                        public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            //设置文本显示内容

                            time.setText(i + ":" + i1);

                        }

                    }, hour, minute, true).show();//记得使用show才能显示！
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
}
