package test;

import javax.jws.WebService;

/**
 * Created by 089245 on 2016/12/9.
 */
@WebService
public interface IService {

    String hello(String msg);
}
