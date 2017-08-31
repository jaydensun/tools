package com.jayden.tx;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/13.
 */

@Transactional
public class TestDao extends HibernateDaoSupport implements ITestDao {

    //    public static final int DATA_COUNT = 10;
//    public static final int BATCH_COUNT = 10;
    public static final int DATA_COUNT = 1000;
    public static final int BATCH_COUNT = DATA_COUNT;

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

    @Transactional
    @Override
    public void saveBatch() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < DATA_COUNT; i++) {
            getHibernateTemplate().save(new Test(12345678, getType()));
        }
        System.out.println("saveBatch\t\t" + (System.currentTimeMillis() - start));
    }

    @Transactional
    @Override
    public void saveBatch2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < DATA_COUNT; i++) {
            getHibernateTemplate().save(new Test2(IdGenerator.getId(), 12345678, getType()));
        }
        System.out.println("saveBatch2\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchSession() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            for (int i = 0; i < DATA_COUNT; i++) {
                session.save(new Test(12345678, getType()));
            }
            return true;
        });
        System.out.println("saveBatchSession\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchSession2() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            for (int i = 0; i < DATA_COUNT; i++) {
                session.save(new Test2(IdGenerator.getId(), 12345678, getType()));
            }
            return true;
        });
        System.out.println("saveBatchSession2\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchSession3() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            for (int i = 0; i < DATA_COUNT; i++) {
                AibssSpaceCal asc = new AibssSpaceCal();
                asc.setId(IdGenerator.getId());
                asc.setSpaceId(333333L);
                asc.setStartValue(10);
                asc.setEndValue(99999);
                asc.setUnitPrice(7.65000);
                asc.setUnitPriceBasic(0.4568989);
                asc.setVersion(0);
                asc.setCreatedEmpCode("089245");
                asc.setCreatedTm(new Date());
                asc.setModifiedEmpCode("089245");
                asc.setModifiedTm(new Date());
                session.save(asc);
            }
            return true;
        });
        System.out.println("saveBatchSession3\t\t" + (System.currentTimeMillis() - start));
    }

    private String getType() {
        return "" + System.nanoTime();
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
    public void clear3() {
        getHibernateTemplate().execute(session -> {
            session.createSQLQuery("delete from TT_AIR_AIBSS_SPACE_CAL").executeUpdate();
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
            Object count = session.createSQLQuery("select count(1) from test2").list().get(0);
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
                for (int i = 0; i < DATA_COUNT; i++) {
                    statement.setString(1, getType());
                    statement.setInt(2, 12345678);
                    statement.addBatch();
                }
                statement.executeBatch();
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
                for (int i = 0; i < DATA_COUNT; i++) {
                    statement.setString(1, getType());
                    statement.setInt(2, 12345678);
                    statement.setLong(3, IdGenerator.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
            });
            return true;
        });
        System.out.println("saveBatchJdbc2\t\t" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveBatchJdbc3() {
        long start = System.currentTimeMillis();
        getHibernateTemplate().execute(session -> {
            session.doWork(connection -> {
                String sql = "insert into TT_AIR_AIBSS_SPACE_CAL (CREATED_EMP_CODE, CREATED_TM, END_VALUE, MODIFIED_EMP_CODE, MODIFIED_TM, SPACE_ID, START_VALUE, UNIT_PRICE, UNIT_PRICE_BASIC, VERSION, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int i = 0; i < DATA_COUNT / BATCH_COUNT; i++) {
                    for (int k = 0; k < BATCH_COUNT; k++) {
                        statement.setString(1, "089245");
                        statement.setTimestamp(2, new Timestamp(new Date().getTime()));
                        statement.setLong(3, 99999);
                        statement.setString(4, "089245");
                        statement.setTimestamp(5, new Timestamp(new Date().getTime()));
                        statement.setLong(6, 333333L);
                        statement.setLong(7, 10);
                        statement.setDouble(8, 7.65000);
                        statement.setDouble(9, 0.4568989);
                        statement.setLong(10, 0);
                        statement.setLong(11, IdGenerator.getId());
                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
            });
            return true;
        });
        System.out.println("saveBatchJdbc3\t\t" + (System.currentTimeMillis() - start));
    }

    private void queryInternal(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Test where id = " + id);
        System.out.println(query.list());
    }
}
