package com.jammi.akash.schoolbustracker.CustomClass;

public class notification_modelclass {
    private String noti_head,noti_sub_noti_,time;

    public String getNoti_head() {
        return noti_head;
    }

    public void setNoti_head(String noti_head) {
        this.noti_head = noti_head;
    }

    public String getNoti_sub_noti_() {
        return noti_sub_noti_;
    }

    public void setNoti_sub_noti_(String noti_sub_noti_) {
        this.noti_sub_noti_ = noti_sub_noti_;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public notification_modelclass(String noti_head, String noti_sub_noti_, String time) {
        this.noti_head = noti_head;
        this.noti_sub_noti_ = noti_sub_noti_;
        this.time = time;
    }
}
