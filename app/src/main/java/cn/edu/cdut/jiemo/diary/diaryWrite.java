package cn.edu.cdut.jiemo.diary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;

public class diaryWrite extends Activity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    EditText diary;
    EditText diarytitle;

    float fontsize;

    String[] fontcolors = {"#000000","#778899","#ffffff"};
    int fontcolornum;
    int temp = 0;
    String fontcolor;

    String[] categorylist = new String[]{"备忘", "随笔", "日记","其他"};
    String category = categorylist[0];

    //int[] backgrounds = {R.drawable.blank,R.drawable.diarybackgroud_fangge,R.drawable.diarybackground_orange};
    int backgroundpic = R.drawable.blank;
    ConstraintLayout constraintLayout;
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

        // 字体大小
        ImageView fontsizein = findViewById(R.id.increasefontsize);
        ImageView fonysizede = findViewById(R.id.decreasefontsize);
        fontsizein.setOnClickListener(this);
        fonysizede.setOnClickListener(this);
//        editText = findViewById(R.id.edit_diary);

        // 字体颜色
        ImageView fontcolor = findViewById(R.id.fontcolor);
        fontcolor.setOnClickListener(this);
        //diary.setTextColor(Color.parseColor("#ffffff"));

        // 背景图案
        ImageView bg = findViewById(R.id.bcg);
        bg.setOnClickListener(this);
//        constraintLayout = findViewById(R.id.diary_constraintlayout);

        // 保存
        ImageView savebtn = findViewById(R.id.save);
        savebtn.setOnClickListener(this);

        // 更多
        ImageView morebtn = findViewById(R.id.more);
        morebtn.setOnClickListener(this);

        // 分享
        ImageView sharebtn = findViewById(R.id.sharebtn);
        sharebtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.returnbtn:
                Intent intent = new Intent();
                intent.setClass(diaryWrite.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.del:
                diary.setText("");
                break;
            case R.id.increasefontsize:
                fontsize = diary.getTextSize()+5;
                diary.setTextSize(TypedValue.COMPLEX_UNIT_PX,fontsize);
//                diary.setTextSize(20);
                break;
            case R.id.decreasefontsize:
                fontsize = diary.getTextSize()-5;
                diary.setTextSize(TypedValue.COMPLEX_UNIT_PX,fontsize);
                break;
            case R.id.fontcolor:
//                Log.i("colortest","listcolornum:"+findindex("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff)));
//                Log.i("colortest", "color0:"+"#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff));
//                boolean i = ("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff)).equals(fontcolors[1]);
//                Log.i("colortest", String.valueOf(i));
                fontcolornum = (findindex("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff)) + 1) % 3;
//                int test = (findindex("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff))+1)%3;
//                Log.i("colortest","test+"+test);
//                Log.i("colortest","test+"+ findindex("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff))+1);
//                Log.i("colortest","listcolornum:"+findindex("#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff)));
                diary.setTextColor(Color.parseColor(fontcolors[fontcolornum]));
//                String colorset = "'"+Integer.toHexString(diary.getCurrentTextColor())+"'";
//                Log.i("colortest", "color1:"+"#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff));
               // Log.i("colortest","listcolor:"+fontcolors[fontcolornum]);
//                diary.setTextColor(Color.parseColor("#778899"));
                break;
            case R.id.bcg:
//                constraintLayout.getBackground();
//                constraintLayout.setBackgroundResource(backgrounds[0]);
                PopupMenu popupMenu = new PopupMenu(this,v);
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.inflate(R.menu.menu_diary);
                popupMenu.show();
                break;
            case R.id.save:
//                Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
                AlertDialog diaryalertDialog = new AlertDialog.Builder(this)
                        .setTitle("将日志分类为")
                        .setSingleChoiceItems(categorylist, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //index = i;
                                //Toast.makeText(diaryWrite.this,"singlechoice"+i,Toast.LENGTH_SHORT).show();
                                category = categorylist[i];
                            }
                        })
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fontcolor = "#"+Integer.toHexString(diary.getCurrentTextColor() & 0xffffff);
                                fontsize = diary.getTextSize();  // 连数据库时注意返回的px
                                Toast.makeText(diaryWrite.this, "这是保存按钮,分类为："+category+""+fontsize+
                                        ""+fontcolor+""+backgroundpic,Toast.LENGTH_SHORT).show();
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(diaryWrite.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                diaryalertDialog.show();
                break;
            case R.id.more:
                PopupMenu popupMenu1 = new PopupMenu(this,v);
                popupMenu1.setOnMenuItemClickListener(this);
                popupMenu1.inflate(R.menu.menu_diary_more);
                popupMenu1.show();
                break;
            case R.id.sharebtn:
                diarytitle = findViewById(R.id.diarytitle);
//                Toast.makeText(this,"testmail",Toast.LENGTH_SHORT).show();
//                Uri uri = Uri.parse("mailto:XXX@qq.com");
//                Intent intent1 = new Intent(Intent.ACTION_SENDTO,uri);
//                startActivity(intent1);
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(Uri.parse("mailto:xxx@qq.com"));
                intent1.putExtra(Intent.EXTRA_SUBJECT,diarytitle.getText().toString());
                intent1.putExtra(Intent.EXTRA_TEXT,diary.getText().toString());
                startActivity(intent1);
                break;
        }
    }

    public int findindex(String color) {
        if(color.equals("#0")){
            color = "#000000";
        }
        for (int i = 0; i < 3; i++) {
            if(color.equals(fontcolors[i]))
                temp = i;
        }
        return temp;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        constraintLayout = findViewById(R.id.diary_constraintlayout);
        switch (item.getItemId()){
            case R.id.popmemu_diary_defaultw:
                constraintLayout.setBackgroundResource(R.drawable.blank);
                backgroundpic = R.drawable.blank;
                break;
            case R.id.popmemu_diary_fangge:
                constraintLayout.setBackgroundResource(R.drawable.diarybackgroud_fangge0);
                backgroundpic = R.drawable.diarybackgroud_fangge0;
                break;
            case R.id.popmemu_diary_rabbit:
                constraintLayout.setBackgroundResource(R.drawable.diarybackground_fangge2);
                backgroundpic = R.drawable.diarybackground_fangge2;
                break;
            case R.id.popmemu_diary_line:
                constraintLayout.setBackgroundResource(R.drawable.diarybackgroud_line);
                backgroundpic = R.drawable.diarybackgroud_line;
                break;
            case R.id.popmemu_diary_flower:
                constraintLayout.setBackgroundResource(R.drawable.diarybackground_flower);
                backgroundpic = R.drawable.diarybackground_flower;
                break;
            case R.id.menu_more:
                Toast.makeText(this,"真的没东西了，快关掉",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


//    public int findindex_bcg(int bg, int[] bgs){
//        for(int i=0; i<bgs.length; i++){
//            if(bg==bgs[i]){
//                return i;
//            }
//        }
//    }

}
