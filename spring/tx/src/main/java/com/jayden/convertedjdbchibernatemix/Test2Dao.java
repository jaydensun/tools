package com.jayden.convertedjdbchibernatemix;

import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Test2Dao extends HibernateDaoSupport {

    public void insert() {
        long id = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("insert into test values (" + id + ",2,'3')");
            sqlQuery.executeUpdate();
            return null;
        });
    }
}
