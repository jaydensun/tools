package com.jayden.tx;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/13.
 */
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private int value;

    public Test() {
    }

    public Test(int value) {
        this.value = value;
    }

    public Test(Long id, int value) {
        this.id = id;
        this.value = value;
    }

    public Test(Test testInDb) {
        id = testInDb.id;
        value = testInDb.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
