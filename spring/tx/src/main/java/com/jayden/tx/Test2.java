package com.jayden.tx;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/13.
 */
@Entity
@Table(name = "test2")
public class Test2 {
    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private int value;

    @Column
    private String type;

    public Test2(long id, int value, String type) {
        this.id = id;
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

}
