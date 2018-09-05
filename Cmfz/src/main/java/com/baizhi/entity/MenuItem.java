package com.baizhi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuItem implements Serializable{
    private int id;
    private String title;
    private String href;
    private String iconCls;
    private List<MenuItem> menuItems = new ArrayList<>();

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id &&
                Objects.equals(title, menuItem.title) &&
                Objects.equals(href, menuItem.href) &&
                Objects.equals(iconCls, menuItem.iconCls) &&
                Objects.equals(menuItems, menuItem.menuItems);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, href, iconCls, menuItems);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }

    public MenuItem(int id, String title, String href, String iconCls, List<MenuItem> menuItems) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.iconCls = iconCls;
        this.menuItems = menuItems;
    }

    public MenuItem() {
    }
}
