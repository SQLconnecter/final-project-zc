package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Log implements Serializable {
    private String id;

    private String uid;

    private Date date;

    private String operate;

    private boolean result;

    public Log() {
    }

    public Log(String id, String uid, Date date, String operate, boolean result) {
        this.id = id;
        this.uid = uid;
        this.date = date;
        this.operate = operate;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return result == log.result &&
                Objects.equals(id, log.id) &&
                Objects.equals(uid, log.uid) &&
                Objects.equals(date, log.date) &&
                Objects.equals(operate, log.operate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, date, operate, result);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", date=" + date +
                ", operate='" + operate + '\'' +
                ", result=" + result +
                '}';
    }
}
