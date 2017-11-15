package com.qmms;

import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanProductTest {
    @Resource
    SerLoanService serLoanService;

    @Test
    public void productAdd(){
        SerLoanProduct product = new SerLoanProduct();
        product.setName("test");

        String loanTypes = "dd";
        String urls ="1$$www.hua.com,2$$hu";

        serLoanService.addLoanProduct(product,loanTypes.split(","),urls.split(","));
    }

    @Test
    public void productEdit() throws Exception{
        SerLoanProduct product = new SerLoanProduct();
        product.setId(1L);
        product.setName("test");

        SerLoanType loanType = new SerLoanType();
        loanType.setKey("dd");
        loanType.setTitle("test");

        String loanTypes = "dd";
        String urls ="1$$www.hua.com,2$$hu";

        serLoanService.editLoanProduct(product, loanTypes.split(","), urls.split(","));
    }

    @Test
    public void productDel(){
        serLoanService.delLoanProduct(13L);
    }


    @Test
    public void test(){
        System.out.println("1111$$2222".split("\\$\\$").length);
    }

}
