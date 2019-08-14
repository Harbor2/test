package com.example.nine.utils;

import android.content.Context;

import com.example.nine.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class getMsgDisplay {
    private Context context;
    public getMsgDisplay(Context context) {
        this.context = context;
    }
    public ArrayList getMsg() {
        //将账号密码从文件中读取出来
        File file = new File(context.getFilesDir(), "info.txt");
        String fo = null;
        ArrayList arrayList = new ArrayList();
        if (file.exists() && file.length() > 0) {
            try {
                BufferedReader rs = new BufferedReader(new FileReader(file));
                //如何得到行数并读取
                fo = rs.readLine();
                arrayList.add(fo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
