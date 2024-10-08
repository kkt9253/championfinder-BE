package com.taktak.championfinder.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionDTO {
    private int id;
    private String name;
    private String img;
    private String description;
}
