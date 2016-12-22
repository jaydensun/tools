package com.jayden.tx;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * Created by Administrator on 2016/10/13.
 */
@Entity
@Table(name = "test")
@GenericGenerator(name = "SEQ_Name", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "seq_test")})
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Name")
    @Column
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
}
