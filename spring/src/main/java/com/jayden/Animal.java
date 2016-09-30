package com.jayden;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Animal implements InitializingBean {
    String name = "default name";
    String desc = "default desc";

    public Animal() {
        this.desc = Util.append(this.desc, "constructor");
        System.out.println(prefix() + "constructor : desc = [" + this.desc + "]");
    }

    private String prefix() {
        return getClass().getSimpleName() + "/";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(prefix() + "setName : name = [" + name + "]");
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = Util.append(desc, this.desc);
        System.out.println(prefix() + "setDesc : desc = [" + this.desc + "]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.desc = Util.append(this.desc, "afterPropertiesSet");
        System.out.println(prefix() + "afterPropertiesSet : desc = [" + this.desc + "]");
    }

    public void initMethod() {
        this.desc = Util.append(this.desc, "initMethod");
        System.out.println(prefix() + "initMethod : desc = [" + this.desc + "]");
    }

    @Override
    public String toString() {
        return prefix() + "{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
