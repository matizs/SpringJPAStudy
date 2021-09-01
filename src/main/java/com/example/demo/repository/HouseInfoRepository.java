package com.example.demo.repository;

import com.example.demo.model.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Integer> {
}
