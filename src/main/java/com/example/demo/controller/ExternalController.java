package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.HouseInfo;
import com.example.demo.model.User;
import com.example.demo.repository.HouseInfoRepository;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@RestController
public class ExternalController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final HouseInfoRepository houseInfoRepository;

    public ExternalController(UserService userService, UserRepository userRepository, HouseRepository houseRepository, HouseInfoRepository houseInfoRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.houseInfoRepository = houseInfoRepository;
    }

    @GetMapping("/hi")
    @Cacheable(value = "testCache", key = "")
    public String test(){
        return "hello";
    }


    @GetMapping("/create")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String create(@RequestParam String name) {
        User user = new User(name);
        House house = new House();
        houseRepository.save(house);
        user.setHouse(house);

        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setHouse(house);


        houseInfoRepository.save(houseInfo);
        userService.insertUser(user);

        return userRepository.findById(user.getId()).get().getName();
    }

    @GetMapping("/findHouse")
    @Transactional
    public String findH() throws JsonProcessingException {
        System.out.println("================FIND ALL=============");
        houseRepository.findAll();
        System.out.println("=====================================");
        System.out.println("============FIND ALL BY ENTITY GRAPH==");
        houseRepository.findAllBy();
        System.out.println("========================================");
        return "ok";
    }
    @GetMapping("/findHouse/{id}")
    @Transactional
    public String findHouse(@PathVariable Integer id) {
        House house = houseRepository.findById(id).get();
        for (User user : house.getUsers()) {
            System.out.println(user.getName());
        }
        return house.toString();
    }
    @GetMapping("/find/{id}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String find(@PathVariable Integer id) {
        System.out.println("FIND 실행");
        User user1 = userRepository.findById(id).get();
        //1차 캐시 테스트
        User user2 = userRepository.findById(id).get();
        System.out.println("=============User1 == user2=======");
        System.out.println(user1 == user2);
        System.out.println(user1.hashCode());
        User user = userRepository.findById(id).get();
        user.setName("Saved");
        userRepository.save(user);
        System.out.println("END!!!!!!!!!");

        System.out.println("=========UPDATE=======");
        User u = new User("TESTING");
        u.setHouse(user1.getHouse());
        userRepository.save(u);
        System.out.println("==========END===========");
        System.out.println("================FIND BY NAME");
        System.out.println("==================END========");
        return userRepository.findById(id).get().getName();
    }
}
