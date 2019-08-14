package com.example.nine.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.nine.mine.activity.LoginActivity;
import com.example.nine.mine.mybean.MyInformation;

import java.io.File;
import java.io.FileOutputStream;

public class UpdateMsg {
    public Context context;
    byte[] encodeuserphone = null;
    byte[] encodepassword = null;
    public UpdateMsg(Context context){
        this.context = context;
    }
    public void writeMsg(String userPhone,String userPass){
        //直接将填写的账号密码写入到文件info.txt中

        AES mAes = new AES();
        try {
            encodeuserphone = userPhone.getBytes("UTF8");
            encodepassword = userPass.getBytes("UTF8");
        } catch (Exception e) {
            Log.e("error: ","加密出错");
        }
        String enUserphone = mAes.encrypt(encodeuserphone);
        String enUserpass = mAes.encrypt(encodepassword);
        String info = enUserphone + "##" + enUserpass + "\r\n";
        CacheUtils.putString(context,"info.txt",info);

    }

    public void writeStatus(Boolean status){
        CacheUtils.putBoolean(context,"loginstatus",status);
    }
    public void writeAutoStatus(Boolean status){
        CacheUtils.putBoolean(context,"autologin",status);
    }
}
