package com.baizhi.entity;

import java.util.Objects;

//这个类的作用就是存储最近一段时间 注册人员的数量
public class UserDTO {
    private String name;
    private int value;

    public UserDTO() {
    }

    public UserDTO(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return value == userDTO.value &&
                Objects.equals(name, userDTO.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
