package com.gmail.gian.springjpapostgres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by GianPH - 5/5/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    private List<Task> tasks;
}
