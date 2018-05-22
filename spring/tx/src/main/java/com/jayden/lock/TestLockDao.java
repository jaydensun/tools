package com.jayden.lock;

import com.jayden.tx.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/13.
 */

@Transactional
public class TestLockDao extends HibernateDaoSupport implements ITestLockDao {

    @Override
    public void insert(String module) {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("insert into tt_export_info(id,module,status) values (:id,:module,0)");
            sqlQuery.setLong("id", new Random().nextLong());
            sqlQuery.setString("module", module);
            sqlQuery.executeUpdate();
            return null;
        });
    }

    @Override
    public void mark(String module, String machine) {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("update tt_export_info set status = 1, deal_machine = :machine where status = 0 and module like '%"
                    + module + "%' order by create_tm limit 10");
            sqlQuery.setString("machine", machine);
            sqlQuery.executeUpdate();

            return null;
        });
    }

    @Override
    public int running(String module, String machine) {
        return getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("update tt_export_info set status = 9 where status = 1 and deal_machine = :machine and module like '%"
                    + module + "%'");
            sqlQuery.setString("machine", machine);
            return sqlQuery.executeUpdate();
        });
    }

    @Override
    public void clean() {
        getHibernateTemplate().execute(session -> {
            SQLQuery sqlQuery = session.createSQLQuery("delete from tt_export_info where status = 9");
            sqlQuery.executeUpdate();
            return null;
        });
    }


}
