package com.jayden.lock;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface ITestLockDao {
    void insert(String module);

    void mark(String module, String machine);

    int running(String module, String machine);

    void clean();
}
