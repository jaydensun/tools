package com.jayden.tx;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface ITestDao {
    void save(Test test);

    void query(int id);

    void update(long id);

    void saveBatch();
    void saveBatch2();

    void clear();
    void clear2();

    void saveBatchJdbc();
    void saveBatchJdbc2();

    void saveBatchSession();
    void saveBatchSession2();

    void saveMultiTimes(Test test);

    void count();
    void count2();
}
