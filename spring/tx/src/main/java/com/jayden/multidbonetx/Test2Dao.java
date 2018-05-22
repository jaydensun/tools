package com.jayden.multidbonetx;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Test2Dao extends HibernateDaoSupport {

    public void query() {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("select * from md_options");
            sqlQuery.list().forEach(x -> System.out.println(Arrays.toString((Object[]) x)));
            return null;
        });
    }
}
