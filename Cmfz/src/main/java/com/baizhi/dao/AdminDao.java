package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    public Admin queryByUsernameAndPwd(@Param("username") String username, @Param("password") String password);
}
