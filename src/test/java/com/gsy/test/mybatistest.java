package com.gsy.test;

import gsy.dao.userdao;
import gsy.domain.user;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatistest {
    private InputStream in;
    private SqlSession session;
    private userdao ud;

    @Before
    public void init() throws Exception{
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
//        2.创建sqlsessionfactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
//        3.使用工厂生产SqlSession对象
        session=factory.openSession();
    }

    @After
    public void destory() throws Exception{
        session.close();
        in.close();
    }

    @Test
    public void testfind()  {
//        1.读取配置文件

//        4.使用SqlSession创建dao的代理对象
        ud=session.getMapper(userdao.class);
//        5.使用代理对象执行方法
        List<user> users= ud.findAll();
        for(user user:users){
            System.out.println(user);
        }
//        6.释放资源

    }

    @Test
    public void testadd(){
        user users=new user();
        users.setId("3");
        users.setName("c");
        users.setTel("111111");
        session.insert("gsy.dao.userdao.addNew",users);
        session.commit();
    }

    @Test
    public void testupdate(){
        user users=new user();
        users.setId("1");
        users.setName("d");
        users.setTel("111111");
        session.update("gsy.dao.userdao.update",users);
        session.commit();
    }

    @Test
    public void testdelete(){
        session.delete("gsy.dao.userdao.delete","1");
        session.commit();
    }
}
