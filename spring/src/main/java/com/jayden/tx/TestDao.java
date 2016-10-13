package com.jayden.tx;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
@Transactional
public class TestDao extends HibernateDaoSupport implements ITestDao {

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
        Test newTest = new Test(3L, 55);
        getHibernateTemplate().saveOrUpdate(newTest);
        newTest = new Test(3L, 66);
        getHibernateTemplate().merge(newTest);
//        getHibernateTemplate().saveOrUpdate(newTest);
    }

    private void queryInternal(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Test where id = " + id);
        System.out.println(query.list());
    }
}
