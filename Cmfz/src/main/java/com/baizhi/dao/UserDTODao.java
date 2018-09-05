package com.baizhi.dao;

import com.baizhi.entity.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDTODao {
    /**
     * @param day 查询的周
     * @return 查询出最近这几周注册的人数
     */
    public int queryLastWeek(@Param("day")int day);

    /**
     * @return 查询所有的男性
     */
    public List<UserDTO> queryMan();

    /**
     * @return 查询所有的女性
     */
    public List<UserDTO> queryWoman();
}
