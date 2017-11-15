package com.qmms;

import com.qmms.entity.SerCreditProduct;
import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerCreditService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditProductTest {
    @Resource
    SerCreditService serCreditService;

    @Test
    public void productAdd(){
        SerCreditProduct product = new SerCreditProduct();
        product.setCardName("dd");

        String loanTypes = "dd,dd1";
        String urls ="1$$www.hua.com,2$$hu";

        serCreditService.addCreditProduct(product,loanTypes.split(","),urls.split(","));
    }

    @Test
    public void productEdit() throws Exception{
        SerCreditProduct product = new SerCreditProduct();
        product.setCardId(1L);
        product.setCardName("ddaa");

        String loanTypes = "dd";
        String urls ="1$$www.hua.com,2$$hu,3$$hddd";

        serCreditService.editCreditProduct(product,loanTypes.split(","),urls.split(","));

//        serLoanService.editLoanProduct(product, loanTypes.split(","), urls.split(","));
    }

    @Test
    public void productDel(){
//        serLoanService.delLoanProduct(13L);
    }


    @Test
    public void test(){
        System.out.println("1111$$2222".split("\\$\\$").length);
    }

}
