package com.jammi.akash.schoolbustracker.CustomClass;

public class CalenderAdapter {
    private  String iDate;
    private  int status;

    public CalenderAdapter() {
    }

    public CalenderAdapter(String title) {
        this.iDate = title;

    }
    public CalenderAdapter(String title,int Status) {
        this.iDate = title;
        this.status= Status;

    }

    public  String getTitle() {
        return iDate;
    }
    public  int getStatus() {
        return status;
    }

    public void setTitle(String name) {
        this.iDate = name;
    }


}
