package cn.edu.cdut.jiemo.mine;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    int height;//图片尺寸
    int width;
    //相册
    Button b1;
    //拍照
    Button b2;
    //用户名
    String userName=getUser().getUserName();
    String fileName;
    private Uri imageUri;
    private String imageUriStr;
    File outputImage;//图片文件

    public static final int CHOOSE_PHOTO =2;
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


        userImage.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "viewHeight:" + userImage.getHeight());
                Log.e("TAG", "viewWidth:" + userImage.getWidth());
                height=userImage.getHeight();
                width=userImage.getWidth();
                //初始化图片
                imageUriStr=getUser().getUserImage();
                if (imageUriStr==null){
                    //文件名在用户没有上传过头像时，赋值为当前的用户名
                    fileName=userName+".jpg";
                }else{
                    imageUri=Uri.parse(imageUriStr);
                    //获取文件名
                    fileName= imageUriStr.substring(imageUriStr.lastIndexOf("/") + 1, imageUriStr.length());
//            displayImage(imageUriStr);
//            try{
//                Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
//                userImage.setBackground(d);
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            }
                    displayImage(imageUriStr);
                    //Log.d("aaa","aaa");

                }
                outputImage =new File(getExternalCacheDir(),fileName);
            }
        });

        //去掉系统自带的标题栏
        ActionBar actionbar=getSupportActionBar();
        if(actionbar !=null)
        {
            actionbar.hide();
        }

//        //初始化图片
//        imageUriStr=getUser().getUserImage();
//        if (imageUriStr==null){
//            //文件名在用户没有上传过头像时，赋值为当前的用户名
//            fileName=userName+".jpg";
//        }else{
//            imageUri=Uri.parse(imageUriStr);
//            //获取文件名
//            fileName= imageUriStr.substring(imageUriStr.lastIndexOf("/") + 1, imageUriStr.length());
////            displayImage(imageUriStr);
////            try{
////                Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
////                userImage.setBackground(d);
////            }catch (FileNotFoundException e){
////                e.printStackTrace();
////            }
//            displayImage(imageUriStr);
//            //Log.d("aaa","aaa");
//
//        }




        //相册
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if(ContextCompat.checkSelfPermission(changeUserImage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(changeUserImage.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
               } else{
                   openAlbum();

               }
               }
        });



        //照相
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //创建Flie对象，存储照片

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
    protected void onStart() {
        super.onStart();

    }

    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);//打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();

                    Toast.makeText(this, "你拒绝了请求", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
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

                    displayImage(imageUriStr);
                    //Log.d("aaa", "onActivityResult: "+imageUriStr);
                    //String转换为Uri
                    //imageUri=Uri.parse(imageUriStr);
//                    try{
//                        Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
//                        userImage.setBackground(d);
//                    }catch (FileNotFoundException e){
//                        e.printStackTrace();
//                    }
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
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT>=19){
                        Log.d("aaa",">19");
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else{
                        Log.d("aaa","<19");
                        //4.4一下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }

                break;
            default:
                    break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri，则通过document id处理
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];//解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath=getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath=uri.getPath();
        }
        Log.d("aaa","changeImage_imagepath+onkikat"+imagePath);
        displayImage(imagePath);
        //保存用户头像
        getUser().changeUserImage(imagePath);
    }
    private void handleImageBeforeKitKat(Intent data){
        Log.d("aaa","handlebrefore");
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        //保存用户头像
        getUser().changeUserImage(imagePath);
        displayImage(imagePath);
        Log.d("aaa","changeImage_imagepath+beforonkikat"+imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    //展示图片
    private void displayImage(String imagePath){
        Log.d("aadispla",imagePath);
        if(imagePath!=null){
            try {
                //图库选择
                Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
                //设置想要的大小
                int newWidth=width;
                int newHeight=height;
                Log.d("changen高，低",width+" "+height);

                int oldwidth = bitmap.getWidth();
                int oldheight = bitmap.getHeight();
                Log.d("changeo高，低",oldwidth+" "+oldheight);
                //计算压缩的比率
                float scaleWidth=((float)newWidth)/oldwidth;
                float scaleHeight=((float)newHeight)/oldheight;

                //获取想要缩放的matrix
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth,scaleHeight);

                //获取新的bitmap
                bitmap=Bitmap.createBitmap(bitmap,0,0,oldwidth,oldheight,matrix,true);
                userImage.setImageBitmap(bitmap);
            }catch (Exception e){
                try {
                    //拍照选择
                    Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    //设置想要的大小
                    int newWidth=width;
                    int newHeight=height;

                    int oldwidth = bitmap.getWidth();
                    int oldheight = bitmap.getHeight();
                    //计算压缩的比率
                    float scaleWidth=((float)newWidth)/oldwidth;
                    float scaleHeight=((float)newHeight)/oldheight;

                    //获取想要缩放的matrix
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth,scaleHeight);

                    //获取新的bitmap
                    bitmap=Bitmap.createBitmap(bitmap,0,0,oldwidth,oldheight,matrix,true);
                    userImage.setImageBitmap(bitmap);
                }catch (Exception e1){

                }
            }



//            Toast.makeText(this,"成功加载",Toast.LENGTH_SHORT).show();
//            imageUri=Uri.parse(imageUriStr);
//
//            //将图片复制给用户数据库中的地址
//            Log.d("imagePath",imagePath);
//            copyfile(imagePath,imageUriStr);


//            try{
//                Drawable d= Drawable.createFromStream(getContentResolver().openInputStream(imageUri),null);
//                userImage.setBackground(d);
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            }

        }else{
            Toast.makeText(this,"打开图片失败",Toast.LENGTH_SHORT).show();
        }
    }

    //把路径a下的文件拷贝到路径b下
    public void copyfile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (!oldfile.exists()) { //文件不存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }


}
