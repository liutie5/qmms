package com.qmms;

import com.qmms.dao.SerLoanBannerDao;
import com.qmms.entity.SerLoanBanner;
import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanBannerTest extends BasicJPATest{
    @Resource
    SerLoanService serLoanService;
    @Resource
    SerLoanBannerDao serLoanBannerDao;

    @Test
    public void add(){
        SerLoanBanner banner = new SerLoanBanner();
        banner.setImg("img");
        banner.setTitle("test");
        banner.setPid(1L);
        banner.setStatus(1);
        banner.setUrl("url");
        serLoanService.addLoanBanner(banner);
    }

    @Test
    public void edit() throws Exception{
        SerLoanBanner banner = new SerLoanBanner();
        banner.setId(2L);
        banner.setImg("imgdd");
        banner.setTitle("testddq");
        banner.setPid(2L);
        banner.setStatus(0);
        serLoanService.editLoanBanner(banner);
    }

    @Test
    public void edit1() throws Exception{
        SerLoanBanner banner = new SerLoanBanner();
        banner.setId(2L);
        banner.setImg("imgdddd");
//        banner.setTitle("testddq");
//        banner.setUrl("testddq");
        entityManager.merge(banner);
//        serLoanBannerDao.save(banner);
    }

    @Test
    public void del(){
        serLoanService.delLoanBanner(1L);
    }

}
