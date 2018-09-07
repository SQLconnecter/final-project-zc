package testservice;

import com.baizhi.entity.Album;
import com.baizhi.entity.UserDTO;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerImgService;
import com.baizhi.service.UserDTOService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestService extends BeanTest {
    @Autowired
    private BannerImgService bannerImgService;
    @Autowired
    private UserDTOService userDTOService;
    @Autowired
    private AlbumService albumService;
    @Test
    public void test(){
        try {
            bannerImgService.change(10,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){
        List<UserDTO> userDTOS = userDTOService.showDto(3);
        userDTOS.forEach(System.out::println);
    }
    @Test
    public void test2(){
        List<Album> albums = albumService.queryByPage(1, 5);

    }
}
