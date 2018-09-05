package com.baizhi.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * ，每个视频或者音频的信息
 */
public class Chapter implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 时长
     */
    private String duration;
    /**
     * 存储空间
     */
    private double size;
    /**
     * 存放的路径
     */
    private String audioPath;
    /**
     * 所属的专辑
     */
    private Album album;

    public Chapter() {
    }

    public Chapter(String id, String title, String duration, double size, String audioPath, Album album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.audioPath = audioPath;
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Double.compare(chapter.size, size) == 0 &&
                Objects.equals(id, chapter.id) &&
                Objects.equals(title, chapter.title) &&
                Objects.equals(duration, chapter.duration) &&
                Objects.equals(audioPath, chapter.audioPath) &&
                Objects.equals(album, chapter.album);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, duration, size, audioPath, album);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size=" + size +
                ", audioPath='" + audioPath + '\'' +
                ", album=" + album +
                '}';
    }
}
