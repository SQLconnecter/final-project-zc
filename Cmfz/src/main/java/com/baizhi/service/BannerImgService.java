package com.baizhi.service;

import com.baizhi.entity.BannerImg;

import java.util.List;

public interface BannerImgService {
    /**
     * @return 展示当前所有的可以轮播的图片
     */
    public List<BannerImg> showAll();

    /**
     * @param id 当前图片的id
     * @param status 当前图片的状态
     *               更新当前图片的状态
     */
    public void change(int id,boolean status);

    /**
     * @param id 需要删除的图片得到id
     *           删除某张不需要的图片
     */
    public void drop(int id);

    /**
     * @param bannerImg 新的图片的信息
     *                  添加一张新的图片
     */
    public void newImg(BannerImg bannerImg);

    /**
     * @param page 需要展示的分页的页码
     * @param rows 每页所拥有的记录的数量
     * @return 按照要求展示的分页
     */
    public List<BannerImg> queryByPage(int page,int rows);

    /**
     * @return 返回记录总数
     */
    public int countAll();
    public BannerImg giveBImg(int id);
}

