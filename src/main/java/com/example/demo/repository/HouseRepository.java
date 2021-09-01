package com.example.demo.repository;

import com.example.demo.model.House;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Integer> {

    @EntityGraph(attributePaths = {"users", "houseInfo"}, type = EntityGraph.EntityGraphType.FETCH)
    List<House> findAllBy();
}
