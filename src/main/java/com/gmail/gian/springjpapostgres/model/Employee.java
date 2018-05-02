package com.gmail.gian.springjpapostgres.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by GianPH - 1/5/2018
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    public Employee(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
