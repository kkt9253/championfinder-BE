package com.taktak.championfinder.service;

import com.taktak.championfinder.model.Champion;
import com.taktak.championfinder.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;
    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    public Champion recommendChampion(List<Boolean> userResponses) {

        List<Champion> currentChampions = championRepository.findAll();
        int userBitSum = calculateBitSum(userResponses);

        // 유저 비트의 합과 챔피언 비트 비교
        List<Champion> sameBitChampions = currentChampions.stream()
                .filter(champion -> champion.getBitValue() == userBitSum)
                .toList();

        if (!sameBitChampions.isEmpty()) return sameBitChampions.get(0);

        // 유저 비트의 합과 챔피언 비트가 매치되지 않는다면 순차적 추천 로직 수행
        List<Champion> prevChampions = currentChampions;

        for (int i = 0; i < 15; i++) {
            boolean userResponse = userResponses.get(i);
            int idx = i; // 람다식에선 사용하는 변수가 final이여야 하기에 사용할 인덱스를 반복할 떄마다 새롭게 초기화

            prevChampions = currentChampions;

            currentChampions = currentChampions.stream()
                    .filter(champion -> getChampionResponseByIndex(champion, idx) == userResponse)
                    .toList();

            if (currentChampions.isEmpty()) return prevChampions.get(0);
            if (currentChampions.size() == 1) return currentChampions.get(0);
        }

        return currentChampions.get(0);
    }

    // 사용자의 응답 비트 합을 계산하는 메서드
    private int calculateBitSum(List<Boolean> userResponses) {
        int bitSum = 0;
        for (int i = 0; i < userResponses.size(); i++) {
            if (userResponses.get(i)) {
                bitSum += (1 << i);
            }
        }
        return bitSum;
    }

    private boolean getChampionResponseByIndex(Champion champion, int index) {
        return switch (index) {
            case 0 -> champion.getQuestion1();
            case 1 -> champion.getQuestion2();
            case 2 -> champion.getQuestion3();
            case 3 -> champion.getQuestion4();
            case 4 -> champion.getQuestion5();
            case 5 -> champion.getQuestion6();
            case 6 -> champion.getQuestion7();
            case 7 -> champion.getQuestion8();
            case 8 -> champion.getQuestion9();
            case 9 -> champion.getQuestion10();
            case 10 -> champion.getQuestion11();
            case 11 -> champion.getQuestion12();
            case 12 -> champion.getQuestion13();
            case 13 -> champion.getQuestion14();
            case 14 -> champion.getQuestion15();
            default -> throw new IllegalArgumentException("Invalid index: " + index);
        };
    }
}
