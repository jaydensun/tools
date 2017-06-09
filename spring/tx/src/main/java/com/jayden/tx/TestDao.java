package com.jayden.tx;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

/**
 * Created by Administrator on 2016/10/13.
 */
@Transactional
public class TestDao extends HibernateDaoSupport implements ITestDao {

//    public static final int DATA_COUNT = 10;
//    public static final int BATCH_COUNT = 10;
    public static final int DATA_COUNT = 1000;
    public static final int BATCH_COUNT = 20;

    @Override
    public void save(Test test) {
        getHibernateTemplate().save(test);
        test.setValue(100);
        getHibernateTemplate().save(test);
    }

    @Override
    public void query(int id) {
//        List<?> objects = getHibernateTemplate().find("from Test where id = " + id);
//        List<?> objects2 = getHibernateTemplate().find("from Test where id = " + id);
//        System.out.println(objects);
//        System.out.println(objects2);

        queryInternal(id);
        queryInternal(id);
    }

    @Override
    public void update(long id) {
        Test test = getHibernateTemplate().load(Test.class, id);
//        System.out.println(test);
//        getHibernateTemplate().save(test);
        Test newTest = new Test(id, 77);
//        getHibernateTemplate().merge(newTest);
        getHibernateTemplate().saveOrUpdate(newTest);
//        getHibernateTemplate().saveOrUpdate(test);
//        getHibernateTemplate().saveOrUpdate(newTest);
    }

    @Override
    public void saveBatch() {
        clear();
        long start = System.currentTimeMillis();
        for (int i = 0; i < DATA_COUNT; i++) {
            getHibernateTemplate().save(new Test(12345678, "" + System.nanoTime()));
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.printf("");
    }

    @Override
    public void saveBatchSession() {
        clear();
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                for (int k = 0; k < BATCH_COUNT; k++) {
                    session.save(new Test(12345678, "" + System.nanoTime()));
                }
                session.flush();
                session.clear();
            }
            return true;
        });
        System.out.println(System.currentTimeMillis() - start);
    }

    private void clear() {
        getHibernateTemplate().execute(session -> {
            session.createSQLQuery("truncate table test").executeUpdate();
            return true;
        });
    }

    @Override
    public void saveBatchJdbc() {
        clear();
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            session.doWork(connection -> {
//                PreparedStatement statement = connection.prepareStatement("insert into test (type, value) values (?, ?)");
//                for (int i = 0; i < 1000; i++) {
//                    statement.setString(1, "" + System.nanoTime());
//                    statement.setInt(2, 12345678);
//                    statement.addBatch();
//                }
//                statement.executeBatch();
                PreparedStatement statement = connection.prepareStatement("insert into test (type, value, id) values (?, ?, ?)");
                for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                    for (int k = 0; k < BATCH_COUNT; k++) {
                        statement.setString(1, "" + System.nanoTime());
                        statement.setInt(2, 12345678);
                        statement.setInt(3, i);
                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
            });
            return true;
        });
        System.out.println(System.currentTimeMillis() - start);
    }

    private void queryInternal(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Test where id = " + id);
        System.out.println(query.list());
    }
}
