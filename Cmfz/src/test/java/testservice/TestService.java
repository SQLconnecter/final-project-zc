package testservice;

import com.baizhi.service.BannerImgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestService extends BeanTest {
    @Autowired
    private BannerImgService bannerImgService;
    @Test
    public void test(){
        try {
            bannerImgService.change(10,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
