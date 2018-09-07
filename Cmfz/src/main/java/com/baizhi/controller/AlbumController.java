package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/album.do")
    public Album showAlbum(Integer albumid){
        Album album = albumService.showAlbumById(albumid);
        return album;
    }
    @RequestMapping("/newalb.do")
    public Album newAlbum(HttpServletRequest request,String title, MultipartFile img, boolean status, String brife, String author, String broadCast, String publishDate){
        Date date;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=format.parse(publishDate);
            try {
                img.transferTo(new File(uploadFilePath,newFileName));
                /*------------------------------------------------------------------=---*/
                /*------------------------------------------------------------------=---*/
                Album album = new Album();
                album.setTitle(title);
                album.setCoverImg("/goodimg/"+newFileName);
                album.setScore(10);
                album.setAuthor(author);
                album.setBroadCast(broadCast);
                album.setPublishDate(date);
                album.setBrife(brife);
                try {
                    albumService.newAlbum(album);

                    return album;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
    @RequestMapping("/newc.do")
    public Chapter newChapter(HttpServletRequest request,String title,MultipartFile file,Integer albumid){
        //获取项目的绝对路径
        long ls;
        int ss;//miao
        int s ;//时长：秒
        int h;//时长：分
        int min;//时长：小时
        String servletPath = request.getServletContext().getRealPath("/");
        String uploadFilePath = servletPath + "audio";
        File uploadFile = new File(uploadFilePath);
        if(!uploadFile.exists()){
            uploadFile.mkdir();//如果文件夹不存在就创建文件夹
        }
        //获取原始文件的文件名
        String originalFilename = file.getOriginalFilename();
        //拿到一个随机的UUID作为上传文件的保存名字
        String uuid = UUID.randomUUID().toString();
        //从原始文件的文件名中拿到扩展名
        String extension = FilenameUtils.getExtension(originalFilename);
        //生成目标文件的名字
        String newFileName = uuid + "." + extension;
        //将文件传输到指定的路径
        try {
            file.transferTo(new File(uploadFilePath,newFileName));
            long size=file.getSize()/1024/1024;//获取文件的空间大小单位为：MB
            //获取文件的播放时长
            Encoder encoder = new Encoder();

            try {
                MultimediaInfo m = encoder.getInfo(new File(uploadFilePath,newFileName));
                ls = m.getDuration();
                s = (int) (ls/1000);
                h=s/60/60;
                min=s%3600/60;
                ss=s%3600%60;
                /*------------------------------------------------------------------=---*/

                /*------------------------------------------------------------------=---*/
                Chapter chapter = new Chapter();
                chapter.setTitle(title);
                chapter.setSize(size);
                chapter.setAudioPath("/audio/"+newFileName);
                chapter.setId(uuid);

                chapter.setDuration(h+":"+min+":"+ss);
                try {
                    albumService.newChapter(albumid,chapter);
                    return chapter;
                } catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //下载
    @RequestMapping("/download.do")
    public void download(HttpSession session, HttpServletRequest request, HttpServletResponse response,String path){
        String realPath = session.getServletContext().getRealPath("/");
        String[] strs =path.split("/");
        String name=strs[2];
        String newpath=realPath+path;
        //设置相应的头部为下载模式
        //content
        response.setHeader("content-disposition","attachment;filename="+name);
        try {
            IOUtils.copy(new FileInputStream(newpath),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/allpage.do")
    public Map<String,Object> showPage(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        Integer total = albumService.countAll();
        List<Album> albums  = albumService.queryByPage(page, rows);
        map.put("total",total);
        map.put("rows",albums);
        return map;

    }
    @RequestMapping("/all.do")
    public Map<String,Object> showAll(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        Integer total = albumService.countAll();
        List<Album> albums  = albumService.queryAll();
        map.put("total",total);
        map.put("rows",albums);
        return map;

    }
}
