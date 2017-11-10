package com.qmms;

import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Created by liutie on 17-11-9.
 */
public abstract class BasicJPATest {
    @Resource
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    //子类的每个测试方法之前都会调用
    @Before
    public void setup(){
//        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.entityManager.getTransaction().begin();
    }

    //子类的每个测试方法之后都会调用
    @After
    public void tearDown(){
        try{
            this.entityManager.getTransaction().commit();
        }catch(Exception ex){
            System.out.println("提交事务等阶段出现了错误哦");
        }finally{
            this.entityManager.close();
//            this.entityManagerFactory.close();
        }

    }
}
