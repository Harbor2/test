package com.example.nine.mine.myUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SyncStats;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.R;
import com.example.nine.mine.activity.SignActivity;
import com.example.nine.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class AdapterDate extends BaseAdapter {
    //定义一个sp文件保存签到的日期数据
    public static SharedPreferences day_sp;
    private ArrayList<Integer> signdayList = new ArrayList<>();
    private ArrayList<Integer> readdayList = new ArrayList<>();
    private Context context;
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();
    //签到状态，实际应用中初始化签到状态可通过该字段传递
    private OnSignedSuccess onSignedSuccess;
    private int i;
    //获取当月天数
    private final int maxDay;
    //签到成功的回调方法，相应的可自行添加签到失败时的回调方法

    public AdapterDate(Context context) {
        //用于清空数据的方法
//        clear(context);
        this.context = context;
        //获取当月天数
        maxDay = DateUtil.getCurrentMonthLastDay();
        for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
            //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
            days.add(0);
            //0代表需要隐藏的item
            status.add(false);
            //false代表为签到状态
        }
        for (int i = 0; i < maxDay; i++) {
            days.add(i + 1);
            //初始化日历数据
            status.add(false);
            //初始化日历签到状态
        }
        day_sp = context.getSharedPreferences("signdata", context.MODE_PRIVATE);
        initListener();
    }

    /**
     * 保存已经签到的天数的数据
     *
     * @return
     */
    public void saveArray() {
        //将已经保存的数据清空
        SharedPreferences.Editor mEdit1 = day_sp.edit();
        mEdit1.putInt("Status_size", signdayList.size()); /*sKey is an array*/
        for (int i = 0; i < signdayList.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putInt("Status_" + i, signdayList.get(i));
        }
        mEdit1.commit();
    }

    /**
     * 获取保存的签到天数
     */
    public void loadArray() {
        day_sp = context.getSharedPreferences("signdata", Context.MODE_PRIVATE);
        int size = day_sp.getInt("Status_size", 0);
        readdayList.clear();
        System.out.println("读取的数字的长度为" + size);
        for (int i = 0; i < size; i++) {
            signdayList.add(day_sp.getInt("Status_" + i, 0));
            readdayList.add(day_sp.getInt("Status_" + i, 0));
        }
    }

    /**
     * 清空保存的天数
     */
    public void clear(Context context) {
        day_sp = context.getSharedPreferences("signdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = day_sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 对签到的监听
     */
    private void initListener() {
        //进入页面展示签到数据的回调
        SignActivity.setOnListLintener(new SignActivity.onListLintener() {
            @Override
            public void onListChange() {
                //读取数据
                loadArray();
                for (int i = 0; i < readdayList.size(); i++) {
                    Integer position = readdayList.get(i);
                    status.set(position, true);
                    notifyDataSetChanged();
                    System.out.println(readdayList.get(i) + "");
                }
                //判断今天是否已经完成了签到的任务
                String day = getNetDate.getInstance().getDay();
                //获取系统的日期
                int firstDayOfMonth = DateUtil.getFirstDayOfMonth();
                //获取网络的当前日期，然后经过处理得到手机系统日期的坐标
                if (day != null) {
                    i = Integer.parseInt(day) + firstDayOfMonth - 2;
                    if (readdayList.contains(i)) {
                        if (onSignShowLintener != null) {
                            onSignShowLintener.onSignShowChange();
                        }
                    }
                    System.out.println("网络的日期为   " + getNetDate.getInstance().getNetdate() + "   " + "系统的日期为   " + getSystemData(i));
                } else {
                    if (onGetDataLintener != null) {
                        onGetDataLintener.onDataChange();
                    }
                    //避免出现空指针异常
                    day = "";
                    Toast.makeText(context, "网络时间加载失败,请重新进入", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //点击签到的回调
        SignActivity.setOnSignDataLintener(new SignActivity.onSignDataLintener() {
            @Override
            public void onSignChange() {
                //获取网络日期
                String netdate = getNetDate.getInstance().getNetdate();
                //获取系统的日期
                String nowData = getSystemData(i);
                //将网络获取到的日期保存到文件中
                if (!signdayList.contains(i)) {
                    signdayList.add(i);
                }
                //将获得的信息保存到本地文件中
                System.out.println("保存的数据为==" + i);
                System.out.println("保存的数组的长度为" + signdayList.size());
                saveArray();
                System.out.println("网络的日期为   " + netdate + "   " + "系统的日期为   " + nowData);
                if (netdate != null) {
                    if (netdate.equals(nowData)) {
                        //签到成功
                        status.set(i, true);
                        notifyDataSetChanged();
                        if (onSignedSuccess != null) {
                            onSignedSuccess.OnSignedSuccess();
                        }
                        Toast.makeText(context, "签到成功", Toast.LENGTH_SHORT).show();
                        //每次签到成功之后将信息保存
                        System.out.println("签到成功，今天的日期是  " + netdate);

                        System.out.println("保存的数组的长度为" + signdayList.size());
                        //判断是否为全勤;
                        System.out.println("maxDay   " + maxDay);
                        System.out.println("getNetDate.getInstance().getDay()  " + getNetDate.getInstance().getDay());
                        if ((Integer.parseInt(getNetDate.getInstance().getDay()) == maxDay)) {
                            System.out.println("全勤 、、、、---------");
                            Toast.makeText(context, "全勤、、、、、、、---------", Toast.LENGTH_SHORT).show();
                            if (signdayList.size() == 1) {
                                System.out.println("全勤 、、、、");
                                Toast.makeText(context, "全勤、、、、、、、", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(context, "系统日历出错啦！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gv, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = view.findViewById(R.id.rlItem);
        viewHolder.ivStatus = view.findViewById(R.id.ivStatus);
        viewHolder.tv.setText(days.get(i) + "");
        if (days.get(i) == 0) {
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        if (status.get(i)) {
            viewHolder.tv.setTextColor(Color.parseColor("#FD0000"));
            viewHolder.ivStatus.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv.setTextColor(Color.parseColor("#666666"));
            viewHolder.ivStatus.setVisibility(View.GONE);
        }
        return view;

    }

    class ViewHolder {
        RelativeLayout rlItem;
        TextView tv;
        ImageView ivStatus;
    }

    /**
     * 得到系统的时间日期
     *
     * @param i
     * @return
     */
    public String getSystemData(int i) {
        String date = DateUtil.getCurrentYearAndMonth();
        int day = days.get(i);
        String dayStr = day < 10 ? "0" + day : day + "";
        String positionday = dayStr + "日";
        String lastday = date + positionday;
        return lastday;
    }

    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess) {
        this.onSignedSuccess = onSignedSuccess;
    }


    public static AdapterDate.onGetDataLintener onGetDataLintener;
    public static AdapterDate.onSignShowLintener onSignShowLintener;

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */
    public static interface onSignShowLintener {
        //接口中回调的方法
        void onSignShowChange();
    }

    public static interface onGetDataLintener {
        //接口中回调的方法
        void onDataChange();
    }

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnSignShowLintener(AdapterDate.onSignShowLintener l) {
        onSignShowLintener = l;
    }

    public static void setOnGetDataLintener(AdapterDate.onGetDataLintener l) {
        onGetDataLintener = l;
    }
}
