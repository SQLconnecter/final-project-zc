package testdao;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.MenuItemDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.BannerImg;
import com.baizhi.entity.MenuItem;
import com.baizhi.entity.User;
import com.baizhi.service.BannerImgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TestDao extends BeanTest{
    @Autowired
    private MenuItemDao menuItemDao;
    @Autowired
    private BannerImgService bannerImgService;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private UserDao userDao;
    @Test
    public void test(){
        List<MenuItem> menuItems = menuItemDao.queryAll();
        menuItems.forEach(System.out::println);
    }

    /**
     * 测试轮播图的展示
     */
    @Test
    public void test2(){
        List<BannerImg> bannerImgs = bannerImgService.showAll();
        bannerImgs.forEach(System.out::println);

    }
    @Test
    public void test3(){
        List<BannerImg> bannerImgs = bannerImgService.queryByPage(2, 5);
        bannerImgs.forEach(System.out::println);
    }
    @Test
    public void test4(){
        List<Album> albums = albumDao.queryAll();
        albums.forEach(System.out::println);

    }@Test
    public void test5(){
        Album album = albumDao.queryByAlbumId(1);
        System.out.println(album);

    }
    @Test
    public void test6(){
        /*Chapter chapter = albumDao.queryByChapterId(2);
        System.out.println(chapter);*/

    }
    @Test
    public void test7(){
        /*int count = userDao.count();
        System.out.println(count);*/
        /*List<User> users = userDao.queryAll();
        users.forEach(System.out::print);*/
        /*List<User> users1 = userDao.queryByPage(0, 5);
        users1.forEach(System.out::print);*/
        User user = userDao.queryById("1");
        System.out.println(user);

    }

}
