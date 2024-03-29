package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = " user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "HOUSE_ID")
    private House house;

    @Version
    private Long version;
    public User(String name) {
        this.name = name;
    }
}
