package com.example.nine.mine.mybean;

public class Dcteam_Recoed_Item {
    //舞队的名称
    private String dcteam_name;
    //申请的时间
    private String record_time;

    public Dcteam_Recoed_Item(String dcteam_name, String record_time) {
        this.dcteam_name = dcteam_name;
        this.record_time = record_time;
    }

    public String getDcteam_name() {
        return dcteam_name;
    }

    public void setDcteam_name(String dcteam_name) {
        this.dcteam_name = dcteam_name;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }
}
