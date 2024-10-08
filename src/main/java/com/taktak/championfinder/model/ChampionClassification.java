package com.taktak.championfinder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question_index", nullable = false)
    private int questionIndex;

    @Column(name = "value", nullable = false)
    private Boolean value;

    @ManyToOne
    @JoinColumn(name = "champion_id")
    private Champion champion;
}
