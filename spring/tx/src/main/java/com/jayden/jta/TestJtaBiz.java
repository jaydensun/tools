package com.jayden.jta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 089245 on 2017/10/18.
 */
public class TestJtaBiz implements ITestJtaBiz {
    @Autowired
    private ITestJtaDao testJtaDao;


    @Autowired
    private ITestJta2Dao testJta2Dao;

    @Transactional
    @Override
    public void insert(int id) {
        testJtaDao.insert(id);
        if (true)
            throw new RuntimeException();
        testJta2Dao.insert(id);
    }

    public void setTestJta2Dao(ITestJta2Dao testJta2Dao) {
        this.testJta2Dao = testJta2Dao;
    }

    public void setTestJtaDao(ITestJtaDao testJtaDao) {
        this.testJtaDao = testJtaDao;
    }
}
