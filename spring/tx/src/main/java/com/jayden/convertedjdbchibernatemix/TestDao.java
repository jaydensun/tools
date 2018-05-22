package com.jayden.convertedjdbchibernatemix;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/13.
 */

public class TestDao extends HibernateDaoSupport {

    public void insert(String module) {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("insert into tt_export_info(id,module,status) values (:id,:module,0)");
            sqlQuery.setLong("id", new Random().nextLong());
            sqlQuery.setString("module", module);
            sqlQuery.executeUpdate();
            return null;
        });
    }

    public void mark(String module, String machine) {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("update tt_export_info set status = 1, deal_machine = :machine where status = 0 and module like '%"
                    + module + "%' order by create_tm limit 10");
            sqlQuery.setString("machine", machine);
            sqlQuery.executeUpdate();

            return null;
        });
    }

    public int running(String module, String machine) {
        return getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("update tt_export_info set status = 9 where status = 1 and deal_machine = :machine and module like '%"
                    + module + "%'");
            sqlQuery.setString("machine", machine);
            return sqlQuery.executeUpdate();
        });
    }

    public void clean() {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("delete from tt_export_info where status = 9");
            sqlQuery.executeUpdate();
            return null;
        });
    }


    public void insert() {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("insert into test2(id) values (:id)");
            sqlQuery.setLong("id", Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
            sqlQuery.executeUpdate();
            return null;
        });
    }

}
