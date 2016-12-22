package test;

import javax.jws.WebService;

/**
 * Created by 089245 on 2016/12/9.
 */
@WebService
public class ServiceImpl implements IService {
    @Override
    public String hello(String msg) {
        return "hello || " + msg;
    }
}
