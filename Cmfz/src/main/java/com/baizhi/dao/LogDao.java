package com.baizhi.dao;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    List<Log> queryAll();

    /**
     * @param start 开始的下表
     * @param limit 每页展示的数量
     * @return 根据分页信息查询的分页的数量
     */
    List<Log> queryByPage(@Param("start") int start, @Param("limit") int limit);
}