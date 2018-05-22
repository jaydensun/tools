package com.jayden.convertedjdbchibernatemix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2018/4/12.
 */
@Transactional
@Service
public class TestBiz implements ITestBiz {

    @Autowired
    TestDao testDao;

    @Autowired
    Test2Dao test2Dao;

    @Override
    public void test() {
        testDao.insert();
        test2Dao.insert();
        System.out.println(System.currentTimeMillis());
//        throw new RuntimeException();
    }
}
