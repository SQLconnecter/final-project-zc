package com.baizhi.serviceimple;

import com.baizhi.aspect.LogAnnotation;
import com.baizhi.dao.BannerImgDao;
import com.baizhi.entity.BannerImg;
import com.baizhi.service.BannerImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BannerImgImple implements BannerImgService {
    @Autowired
    private BannerImgDao bannerImgDao;
    private Logger logger = LoggerFactory.getLogger(BannerImgImple.class);
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<BannerImg> showAll() {
        List<BannerImg> bannerImgs = bannerImgDao.queryAll();
        return bannerImgs;
    }

    @Override
    @LogAnnotation(name = "修改数据状态")
    public void change(int id, boolean status) {
        bannerImgDao.update(id,status);
    }

    @Override
    @LogAnnotation(name = "删除数据")
    public void drop(int id) {
        bannerImgDao.delete(id);
    }

    @Override
    @LogAnnotation( name = "添加图片")
    public void newImg(BannerImg bannerImg) {
        bannerImgDao.add(bannerImg);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<BannerImg> queryByPage(int page, int rows) {
        int count =bannerImgDao.allCount();
        int start =(page-1)*rows;
        int end =rows;
        List<BannerImg> bannerImgs = bannerImgDao.queryByPage(start, end);
        logger.debug("拿到的分页结果是："+bannerImgs);
        return bannerImgs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public int countAll() {
        int i = bannerImgDao.allCount();
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public BannerImg giveBImg(int id) {
        BannerImg bannerImg = bannerImgDao.queryById(id);
        return bannerImg;
    }
}
