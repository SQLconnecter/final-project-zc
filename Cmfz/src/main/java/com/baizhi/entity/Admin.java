package com.baizhi.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable{
    private int id;
    private String username;
    private String password;
    private boolean superRoot;

    public Admin() {
    }

    public Admin(int id, String username, String password, boolean superRoot) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.superRoot = superRoot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuperRoot() {
        return superRoot;
    }

    public void setSuperRoot(boolean superRoot) {
        this.superRoot = superRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                superRoot == admin.superRoot &&
                Objects.equals(username, admin.username) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, superRoot);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", superRoot=" + superRoot +
                '}';
    }
}

