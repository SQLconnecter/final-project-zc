package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    /**
     * @param page 页码
     * @param rows 每页显示的记录数量
     * @return 根据分页展示的用户的信息
     */
    public List<User> showByPage(int page,int rows);

    /**
     * @param id 用户的id
     * @param status 用户账号的状态
     * @return 更改用户的账号状态，并且返回更改之后用户的信息
     */
    public User change(String id,boolean status);

    /**
     * @param file 文件的信息
     *                 对上传的文件进行解析，然后将数据导入到数据库中
     */
    public void importDB(MultipartFile file);

    /**
     * @param page 页码
     * @param rows 每页展示的数据的多少
     * @return 将查询出的分页信息制作成file对象返回
     */
    public void  downloadPage(int page, int rows, HttpServletResponse response);

    /**
     * @param titles 用户选择的标题
     * @param params 用户选择的标题的参数
     * @param response 响应对象
     */
    public void downloadSelf(String titles,String params,HttpServletResponse response);

    /**
     * @return 查询数据库记录的总数
     */
    public Integer count();
}
