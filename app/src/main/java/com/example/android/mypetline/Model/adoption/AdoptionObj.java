package com.example.android.mypetline.Model.adoption;

public class AdoptionObj {
    private Integer type;
    private String name;
    private String description;
    private String img_url;
    private String phone;
    private String pubdate;
    private String location;

    public AdoptionObj(Integer type, String name, String description, String img_url, String phone, String pubdate, String location) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.phone = phone;
        this.pubdate = pubdate;
        this.location = location;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
