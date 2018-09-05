package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private String salt;
    private String photoImg;
    private String dharmaName;
    private boolean sex;
    private String province;
    private String city;
    private String sign;
    private String phoneNum;
    private boolean status;
    private Date registerDate;
    private Gruru gruru;

    public User() {
    }

    public User(String id, String name, String password, String salt, String photoImg, String dharmaName, boolean sex, String province, String city, String sign, String phoneNum, boolean status, Date registerDate, Gruru gruru) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.photoImg = photoImg;
        this.dharmaName = dharmaName;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phoneNum = phoneNum;
        this.status = status;
        this.registerDate = registerDate;
        this.gruru = gruru;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Gruru getGruru() {
        return gruru;
    }

    public void setGruru(Gruru gruru) {
        this.gruru = gruru;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return sex == user.sex &&
                status == user.status &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(photoImg, user.photoImg) &&
                Objects.equals(dharmaName, user.dharmaName) &&
                Objects.equals(province, user.province) &&
                Objects.equals(city, user.city) &&
                Objects.equals(sign, user.sign) &&
                Objects.equals(phoneNum, user.phoneNum) &&
                Objects.equals(registerDate, user.registerDate) &&
                Objects.equals(gruru, user.gruru);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, salt, photoImg, dharmaName, sex, province, city, sign, phoneNum, status, registerDate, gruru);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", photoImg='" + photoImg + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", gruru=" + gruru +
                '}';
    }
}
