package com.jayden.tx;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface ITestDao {
    void save(Test test);

    void query(int id);

    void update(long id);

    void saveBatch();

    void saveBatchJdbc();

    void saveBatchSession();

    void saveMultiTimes(Test test);
}
