package com.example.android.mypetline.Model.Tips;

public class TipsObj {
    public Integer tid;
    public String title;
    public String summary;
    public String date;
    public String img_src;
    public String type;

    public TipsObj(Integer tid, String title, String summary, String date, String img_src, String type) {
        this.tid = tid;
        this.title = title;
        this.summary = summary;
        this.date = date;
        this.img_src = img_src;
        this.type = type;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
