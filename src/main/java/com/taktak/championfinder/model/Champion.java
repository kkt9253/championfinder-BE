package com.taktak.championfinder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int championId;

    @Column(name = "champion_name")
    private String championName;

    @Column(name = "champion_image")
    private String championImage;

    @Column(name = "description")
    private String description;

    @Column(name = "question_1")
    private Boolean question1;

    @Column(name = "question_2")
    private Boolean question2;

    @Column(name = "question_3")
    private Boolean question3;

    @Column(name = "question_4")
    private Boolean question4;

    @Column(name = "question_5")
    private Boolean question5;

    @Column(name = "question_6")
    private Boolean question6;

    @Column(name = "question_7")
    private Boolean question7;

    @Column(name = "question_8")
    private Boolean question8;

    @Column(name = "question_9")
    private Boolean question9;

    @Column(name = "question_10")
    private Boolean question10;

    @Column(name = "question_11")
    private Boolean question11;

    @Column(name = "question_12")
    private Boolean question12;

    @Column(name = "question_13")
    private Boolean question13;

    @Column(name = "question_14")
    private Boolean question14;

    @Column(name = "question_15")
    private Boolean question15;

    @Column(name = "bit_value")
    private int bitValue;
}
