package com.taktak.championfinder.controller;

import com.taktak.championfinder.model.Champion;
import com.taktak.championfinder.service.ChampionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class ChampionController {

    private final ChampionService championService;
    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @PostMapping("/filtering")
    public Champion recommendChampionByFiltering(@RequestBody List<Boolean> userResponses) {
        return championService.recommendChampion(userResponses);
    }
}
