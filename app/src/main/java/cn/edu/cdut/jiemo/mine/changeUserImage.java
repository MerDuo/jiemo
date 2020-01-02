package cn.edu.cdut.jiemo.mine;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.zhy.changeskin.SkinManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import cn.edu.cdut.jiemo.MainActivity;
import cn.edu.cdut.jiemo.R;

import static cn.edu.cdut.jiemo.mine.mineUserDao.getUser;

/**
 * Created by aaa on 2020/1/2.
 */

public class changeUserImage extends AppCompatActivity {

    public static final int TAKE_PHOTO=1;
    ImageView userImage;
//    int height;
//    int width;
    //相册
    Button b1;
    //拍照
    Button b2;
    //用户名
    String userName=getUser().getUserName();
    private Uri imageUri;
    private String imageUriStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册换肤功能
        SkinManager.getInstance().register(this);
        setContentView(R.layout.change_userimg);

        //获取控件
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        userImage=findViewById(R.id.userImage);
//        userImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // TODO Auto-generated method stub
//                userImage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                height=userImage.getMeasuredHeight();
//                width=userImage.getMeasuredWidth();
//                Log.e("aaa高宽", userImage.getMeasuredHeight()+","+userImage.getMeasuredWidth());
//            }
//        });

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

        //初始化图片
        imageUriStr=getUser().getUserImage();
        if (imageUriStr==null){
            imageUriStr=userName;
        }else{
            imageUri=Uri.parse(imageUriStr);
            try{
                Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
                userImage.setBackground(d);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }


        //相册
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               // if(ContextCompat.checkSelfPermission(changeUserImage.this, Manifest.permission.WR))
            }
        });

        //照相
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //创建Flie对象，存储照片
                File outputImage =new File(getExternalCacheDir(),userName+".jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(changeUserImage.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                }else{
                    imageUri=Uri.fromFile(outputImage);
                }

                //启动相机程序
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //换肤功能注销
        SkinManager.getInstance().unregister(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    //Uri转换为String
                    imageUriStr=imageUri.toString();
                    //保存用户头像
                    getUser().changeUserImage(imageUriStr);
                    //Log.d("aaa", "onActivityResult: "+imageUriStr);
                    //String转换为Uri
                    //imageUri=Uri.parse(imageUriStr);
                    try{
                        Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
                        userImage.setBackground(d);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
//                    userImage.setImageURI(imageUri);
//                    try{
//                        //将拍摄的照片显示出来
//                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        bitmap =new;
//                        userImage.setImageURI(imageUri);
//                        userImage.setImageBitmap(bitmap);
//                    }catch (FileNotFoundException e){
//                        e.printStackTrace();
//                    }
                }
                break;
            default:
                    break;
        }
    }

}
