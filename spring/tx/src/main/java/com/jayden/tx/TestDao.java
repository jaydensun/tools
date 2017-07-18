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
    public static final int BATCH_COUNT = 100;

    @Override
    public void save(Test test) {
        getHibernateTemplate().save(test);
    }

    @Override
    public void saveMultiTimes(Test test) {
        for (int i = 0; i < 2; i++) {
            save(test.copy());
        }
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
        long start = System.currentTimeMillis();
        for (int i = 0; i < DATA_COUNT; i++) {
            getHibernateTemplate().save(new Test(12345678, "" + System.nanoTime()));
        }
        System.out.println("saveBatch\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatch2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < DATA_COUNT; i++) {
            getHibernateTemplate().save(new Test2(IdGenerator.getId(), 12345678, "" + System.nanoTime()));
        }
        System.out.println("saveBatch2\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchSession() {
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
        System.out.println("saveBatchSession\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchSession2() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                for (int k = 0; k < BATCH_COUNT; k++) {
                    session.save(new Test2(IdGenerator.getId(), 12345678, "" + System.nanoTime()));
                }
                session.flush();
                session.clear();
            }
            return true;
        });
        System.out.println("saveBatchSession2\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void clear() {
        getHibernateTemplate().execute(session -> {
            session.createSQLQuery("delete from test").executeUpdate();
            return true;
        });
    }

    @Override
    public void clear2() {
        getHibernateTemplate().execute(session -> {
            session.createSQLQuery("delete from test2").executeUpdate();
            return true;
        });
    }


    @Override
    public void count() {
        getHibernateTemplate().execute(session -> {
            Object count = session.createSQLQuery("select count(1) from test").list().get(0);
            System.out.println("count: " + count);
            return true;
        });
    }

    @Override
    public void count2() {
        getHibernateTemplate().execute(session -> {
            Object count = session.createSQLQuery("select count(1) from test").list().get(0);
            System.out.println("count: " + count);
            return true;
        });
    }

    @Override
    public void saveBatchJdbc() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            session.doWork(connection -> {
                PreparedStatement statement = connection.prepareStatement("insert into test (type, value) values (?, ?)");
                for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                    for (int k = 0; k < BATCH_COUNT; k++) {
                        statement.setString(1, "" + System.nanoTime());
                        statement.setInt(2, 12345678);
                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
            });
            return true;
        });
        System.out.println("saveBatchJdbc\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchJdbc2() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            session.doWork(connection -> {
                PreparedStatement statement = connection.prepareStatement("insert into test2 (type, value, id) values (?, ?, ?)");
                for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                    for (int k = 0; k < BATCH_COUNT; k++) {
                        statement.setString(1, "" + System.nanoTime());
                        statement.setInt(2, 12345678);
                        statement.setLong(3, IdGenerator.getId());
                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
            });
            return true;
        });
        System.out.println("saveBatchJdbc2\t\t" + (System.currentTimeMillis() - start));
    }

    private void queryInternal(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Test where id = " + id);
        System.out.println(query.list());
    }
}
