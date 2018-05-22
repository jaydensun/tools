package com.jayden.jdbchibernatemix;

import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Test2Dao extends JdbcDaoSupport {

    public void insert() {
        long id = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        getJdbcTemplate().execute("insert into test values (" + id + ",2,'3')");
    }
}
