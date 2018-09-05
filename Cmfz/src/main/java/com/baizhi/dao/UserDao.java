package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * @return 统计数据库中用户的数量
     */
    public int count();

    /**
     * @return 查询数据库中所有用户的信息
     */
    public List<User> queryAll();

    /**
     * @param start 开始的下标
     * @param rows 每页显示的记录的数量
     * @return 根据分页信息查询用户的信息
     */
    public List<User> queryByPage(@Param("start") int start, @Param("rows") int rows);

    /**
     * @param id 用户的id
     * @param status 用户的状态
     */
    public void updateStatus(@Param("id") String id,@Param("status") boolean status);

    /**
     * @param id 用户的id
     * @return 根据用户的id查询到的用户的信息
     */
    public User queryById(@Param("id") String id);

    /**
     * @param users 需要插入数据库的集合信息
     *              批量插入数据库
     */
    public void addList(List<User> users);
}
