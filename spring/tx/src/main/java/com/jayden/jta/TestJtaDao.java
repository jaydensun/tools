package com.jayden.jta;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by 089245 on 2017/10/18.
 */
public class TestJtaDao extends JdbcDaoSupport implements ITestJtaDao {
    @Override
    public void insert(int id) {
        getJdbcTemplate().execute("insert into test_jta(id) values (" + id + ")");
    }
}
