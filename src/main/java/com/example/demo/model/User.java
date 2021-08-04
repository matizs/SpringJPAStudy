package com.example.demo.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =" user")
@Getter
@Setter
public class User {
    @Id
    private int id;

    @Column(name="name")
    private String name;
}
