package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class House {
    @GeneratedValue
    @Id
    private Integer id;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<HouseInfo> houseInfo;
}
