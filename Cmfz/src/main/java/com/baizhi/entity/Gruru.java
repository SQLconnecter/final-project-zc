package com.baizhi.entity;

import java.io.Serializable;
import java.util.Objects;

public class Gruru implements Serializable{
    private String id;//上师的id
    private String name;//上师的名字
    private String dharmaName;//上师的法名
    private String phoneNum;//上师的联系方式
    private String photoImg;//上师的头像
    private boolean status;//上师的状态

    public Gruru() {
    }

    public Gruru(String id, String name, String dharmaName, String phoneNum, String photoImg, boolean status) {
        this.id = id;
        this.name = name;
        this.dharmaName = dharmaName;
        this.phoneNum = phoneNum;
        this.photoImg = photoImg;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gruru gruru = (Gruru) o;
        return status == gruru.status &&
                Objects.equals(id, gruru.id) &&
                Objects.equals(name, gruru.name) &&
                Objects.equals(dharmaName, gruru.dharmaName) &&
                Objects.equals(phoneNum, gruru.phoneNum) &&
                Objects.equals(photoImg, gruru.photoImg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, dharmaName, phoneNum, photoImg, status);
    }

    @Override
    public String toString() {
        return "Gruru{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", photoImg='" + photoImg + '\'' +
                ", status=" + status +
                '}';
    }
}

