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


    @Column
    private int value;

    @Column
    private String type;

    public Test() {
    }

    public Test(int value) {
        this.value = value;
    }

    public Test(Long id, int value) {
        this.id = id;
        this.value = value;
    }

    public Test(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Test copy() {
        return new Test(value, type);
    }
}
