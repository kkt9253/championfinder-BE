package com.taktak.championfinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "champion_name")
    private String name;

    @Column(name = "champion_img")
    private String img;

    @Column(name = "description")
    private String description;

    @Column(name = "bit_value")
    private int bitValue;

    @OneToMany(mappedBy = "champion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChampionClassification> championClassifications = new ArrayList<>();
}
