package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 专辑类，保存有专辑相关的信息
 */
public class Album implements Serializable {
    /**
     * id
     */
    private int id;
    /**
     * 专辑名字
     */
    private String title;
    /**
     * 专辑封面的图片
     */
    private String coverImg;
    /**
     * 专辑的评分
     */
    private int score;
    /**
     * 专辑的作者
     */
    private String author;
    /**
     * 专辑的播音
     */
    private String broadCast;
    /**
     * 专辑的简介
     */
    private String brife;
    /**
     * 专辑的发布日期
     */
    private Date publishDate;
    /**
     * 专辑的上架时间
     */
    private Date createDate;
    /**
     * 专辑的状态：是否在线推荐
     */
    private boolean status;
    /**
     * 专辑的内容信息
     */
    private List<Chapter> children;

    public Album() {
    }

    public Album(int id, String title, String coverImg, int score, String author, String broadCast, String brife, Date publishDate, Date createDate, boolean status, List<Chapter> children) {
        this.id = id;
        this.title = title;
        this.coverImg = coverImg;
        this.score = score;
        this.author = author;
        this.broadCast = broadCast;
        this.brife = brife;
        this.publishDate = publishDate;
        this.createDate = createDate;
        this.status = status;
        this.children = children;
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

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public String getBrife() {
        return brife;
    }

    public void setBrife(String brife) {
        this.brife = brife;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                score == album.score &&
                status == album.status &&
                Objects.equals(title, album.title) &&
                Objects.equals(coverImg, album.coverImg) &&
                Objects.equals(author, album.author) &&
                Objects.equals(broadCast, album.broadCast) &&
                Objects.equals(brife, album.brife) &&
                Objects.equals(publishDate, album.publishDate) &&
                Objects.equals(createDate, album.createDate) &&
                Objects.equals(children, album.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, coverImg, score, author, broadCast, brife, publishDate, createDate, status, children);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", broadCast='" + broadCast + '\'' +
                ", brife='" + brife + '\'' +
                ", publishDate=" + publishDate +
                ", createDate=" + createDate +
                ", status=" + status +
                ", children=" + children +
                '}';
    }

}
