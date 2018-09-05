package com.baizhi.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 这个类是存储有关图片信息的类
 */
public class BannerImg {
    private int id;
    private String title;
    private String imgPath;
    private String description;
    private boolean status;
    private Date createDate;

    public BannerImg() {
    }

    public BannerImg(int id, String title, String imgPath, String description, boolean status, Date createDate) {

        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannerImg bannerImg = (BannerImg) o;
        return id == bannerImg.id &&
                status == bannerImg.status &&
                Objects.equals(title, bannerImg.title) &&
                Objects.equals(imgPath, bannerImg.imgPath) &&
                Objects.equals(description, bannerImg.description) &&
                Objects.equals(createDate, bannerImg.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, imgPath, description, status, createDate);
    }

    @Override
    public String toString() {
        return "BannerImg{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }

}
