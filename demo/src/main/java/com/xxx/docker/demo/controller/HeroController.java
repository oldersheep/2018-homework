package com.xxx.docker.demo.controller;

import com.xxx.docker.demo.entity.HeroEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeroController {

    @GetMapping("/list")
    public List<HeroEntity> list() {
        List<HeroEntity> heroList = new ArrayList<>();
        HeroEntity hero = new HeroEntity();
        hero.setName("jugg");
        heroList.add(hero);
        hero = new HeroEntity();
        hero.setName("sven");
        heroList.add(hero);
        return heroList;
    }
}
