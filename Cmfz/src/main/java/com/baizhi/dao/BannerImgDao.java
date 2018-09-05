package com.baizhi.dao;

import com.baizhi.entity.BannerImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerImgDao {
    /**
     * @return 查询所有的图片
     */
    public List<BannerImg> queryAll();

    /**
     * @param start 记录的开始
     * @param end 记录的结束
     * @return 返回分页的结果
     */
    public List<BannerImg> queryByPage(@Param("start") int start,@Param("end") int end);

    /**
     * @param bannerImg 需要插入的图片
     *                  向数据库中插入的图片的信息
     */
    public void add(@Param("bannerImg") BannerImg bannerImg);


    /**
     * @param status 图片当前的使用状态
     * @param id 图片当前的id
     *           更新图片的当前状态
     */
    public void update(@Param("id") int id,@Param("status") boolean status);

    /**
     * @param id 需要删除的图片信息的id
     *           删除的图片的有关的信息
     */
    public void delete(int id);

    /**
     * @return 查询所有的记录的总数
     */
    public int allCount();
    public BannerImg queryById(int id);
}
