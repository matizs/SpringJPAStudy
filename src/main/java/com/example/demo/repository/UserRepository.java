package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import javax.persistence.Version;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {



    List<User> findBy();

    User findByName(String name);
}
