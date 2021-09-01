package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "HOUSE_INFO")
@NoArgsConstructor
public class HouseInfo {

    @GeneratedValue
    @Id
    private Integer id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "HOUSE_ID")
    private House house;

}
