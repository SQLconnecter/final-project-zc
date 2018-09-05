package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.List;

public interface AlbumService {
    /**
     * @return 展示所有的专辑的信息
     */
    public List<Album> showAlbums();

    /**
     * @param id 专辑的id
     *
     * @return  根据id查询到的专辑的信息
     */
    public Album showAlbumById(int id);

    /**
     * @param id 章节的id
     * @return 根据id查询到的章节的信息
     */
    public Chapter downloadChapter(String id);

    /**
     * @param album 需要添加的专辑的信息
     *              添加一张专辑
     */
    public void newAlbum(Album album);

    /**
     * @param id 某个专辑的id
     * @param chapter 章节的信息
     *                往某个章节里面添加专辑
     */
    public void newChapter(int id,Chapter chapter);

    /**
     * @param id 需要更改的专辑的id
     * @param status 更改为的状态
     *               更改某张专辑的上线推荐
     */
    public void changeStatus(int id,boolean status);

    /**
     * @return 统计记录的条数
     */
    public int countAll();

    /**
     * @param page 当前页面的页码
     * @param rows  总记录数
     * @return 根据分页信息查询的记录
     */
    public List<Album> queryByPage(int page,int rows);
}
