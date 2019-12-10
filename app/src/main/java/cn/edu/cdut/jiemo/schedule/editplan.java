package cn.edu.cdut.jiemo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.edu.cdut.jiemo.R;

public class editplan extends AppCompatActivity {
    private Context context;
    private ImageView back;//返回键
    private EditText title;//标题
    private Switch check;//是否全天
    private String checkStr;
    private RelativeLayout swiday;//点击选择日期
    private RelativeLayout switime;//点击选择时间
    private TextView mday;//显示日期
    private TextView time;//显示时间
    private Button addbtn;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplan);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                editplan.this.finish();
            }
        });
        Bundle receive = this.getIntent().getExtras();

        if(receive != null) {
            String sche = receive.getString("oplan");
            String t = receive.getString("oday");
            String time2 = receive.getString("otime");
            String ocheck = receive.getString("ocheck");
            id =  receive.getInt("dbid");
            time.setText(time2);
            mday.setText(t);//月份的展示
            title.setText(sche);
            checkStr = ocheck;
            if(ocheck == "true"){
                check.setChecked(true);
            }else{
                check.setChecked(false);
            }

        }


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Calendar today = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    mday.setText(sdf.format(today.getTime()));//月份的展示
                    time.setText(sdf.format(today.getTime()));
                    checkStr="true";
                } else {
                    mday.setText(" ");
                    time.setText(" ");
                    checkStr = "false";
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



//                Log.i("标题：",text);
//                Log.i("shijian：",t);
                if(!TextUtils.isEmpty(text) && !TextUtils.isEmpty(t) && !TextUtils.isEmpty(ti)) {
//                    scheduleBean scheduleBean = new scheduleBean();
////                    scheduleBean.plan = text;
////                    scheduleBean.check = "false";
////                    scheduleBean.time = ti;
////                    scheduleBean.day = t;
////                    mySQLiteOpenHelper.insert(scheduleBean);
                    Intent intent = new Intent(editplan.this, calender.class);
                    intent.putExtra("sche",title.getText().toString());
                    intent.putExtra("day",t);
                    intent.putExtra("time",ti);
                    intent.putExtra("check",checkStr);
                    intent.putExtra("id",id);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("scheduleDetail", title.getText().toString());
//                    bundle.putString("day", t);
//                    bundle.putString("time",ti);
//
//
//                    intent.putExtras(bundle);
                    startActivity(intent);
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
                final int month = mcalendar.get(Calendar.MONTH) + 1;
                final int day = mcalendar.get(Calendar.DATE);
                mday.setText(year+"-"+month+"-"+day);
                //mday.setText("选择时间：" + year + "年" + month + "月" + day + "日");
                new DatePickerDialog(editplan.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    //实现监听方法

                    @Override

                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        //设置文本显示内容，i为年，i1为月，i2为日

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
    public void addTime() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        //-------------------------------
        //--添加的
        if (!check.isChecked()) {
            switime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new TimePickerDialog(editplan.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new TimePickerDialog.OnTimeSetListener() {

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
}
