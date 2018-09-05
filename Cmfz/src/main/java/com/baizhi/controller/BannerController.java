package com.baizhi.controller;

import com.baizhi.entity.BannerImg;
import com.baizhi.service.BannerImgService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerImgService bannerImgService;
    private Logger logger = LoggerFactory.getLogger(BannerController.class);
    /**
     * @return 展示所有
     */
    @RequestMapping("/all.do")
    public List<BannerImg> showAll(){
        List<BannerImg> bannerImgs = bannerImgService.showAll();
        return bannerImgs;
    }
    @RequestMapping("/banner.do")
    public Map<String,Object> show(int page, int rows){
       Map<String,Object> map =new HashMap<>();
        Integer i = bannerImgService.countAll();
        List<BannerImg> bannerImgs = bannerImgService.queryByPage(page, rows);
        map.put("total",i);
        map.put("rows",bannerImgs);
        return map;
    }

    /**
     * @return 编辑图片的状态
     */
    @RequestMapping("/edit.do")
    public BannerImg edit(int id,boolean status){
        try {
            bannerImgService.change(id,status);
            BannerImg bannerImg = bannerImgService.giveBImg(id);
            return bannerImg;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @param id 图片的id
     * @return 删除图片
     */
    @RequestMapping("/drop.do")
    public BannerImg drop(int id){
        try {
            bannerImgService.drop(id);
            BannerImg bannerImg = new BannerImg();
            return bannerImg;
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping("/new.do")
    public BannerImg newImg(HttpServletRequest request,String title, String description, boolean status, MultipartFile img){
        /*做产品的上传*/
        //获取项目的绝对路径
        String servletPath = request.getServletContext().getRealPath("/");
        String uploadFilePath = servletPath + "goodimg";
        File uploadFile = new File(uploadFilePath);
        if(!uploadFile.exists()){
            uploadFile.mkdir();//如果文件夹不存在就创建文件夹
        }
        //获取原始文件的文件名
        String originalFilename = img.getOriginalFilename();
        //拿到一个随机的UUID作为上传文件的保存名字
        String uuid = UUID.randomUUID().toString();
        //从原始文件的文件名中拿到扩展名
        String extension = FilenameUtils.getExtension(originalFilename);
        //生成目标文件的名字
        String newFileName = uuid + "." + extension;
        //将文件传输到指定的路径
        try {
            img.transferTo(new File(uploadFilePath,newFileName));
            /*------------------------------------------------------------------=---*/
            logger.debug("新文件上传成功，文件名为："+newFileName+"----------目标地址为："+uploadFilePath);
            /*------------------------------------------------------------------=---*/

            BannerImg bannerImg = new BannerImg();
            bannerImg.setTitle(title);
            bannerImg.setDescription(description);
            bannerImg.setImgPath("/"+"goodimg/"+newFileName);
            bannerImg.setStatus(status);
            try {
                bannerImgService.newImg(bannerImg);
                logger.debug("新文件信息入库成功，文件名为："+newFileName+"----------目标地址为："+uploadFilePath);
                return bannerImg;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("文件的信息入库出现错误，错误文件名为："+newFileName+"----------目标地址为："+uploadFilePath);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
