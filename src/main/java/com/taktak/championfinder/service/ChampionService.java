package com.taktak.championfinder.service;

import com.taktak.championfinder.dto.ChampionDTO;
import com.taktak.championfinder.model.Champion;
import com.taktak.championfinder.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;

    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    // 질문 응답 기반의 챔피언 추천 로직
    public ChampionDTO recommendChampion(List<Boolean> userResponses) {
        // 현재 모든 챔피언 데이터를 조회
        List<Champion> DBChampions = championRepository.findAll();

        // 비트의 총합을 통한 필터링1
        int userBitSum = calculateBitSum(userResponses);
        List<Champion> matchingChampions = DBChampions.stream()
                .filter(champion -> champion.getBitValue() == userBitSum)
                .toList();
        if (!matchingChampions.isEmpty()) {
            return convertChampionToDTO(matchingChampions.get(0));
        }

        // 비트의 총합이 동일하지 않으면, 필터링2 수행
        Champion champion = findBestMatchingChampion(DBChampions, userResponses);
        return convertChampionToDTO(champion);
    }

    // 사용자 응답의 비트 합을 계산하는 메서드
    private int calculateBitSum(List<Boolean> userResponses) {
        int bitSum = 0;
        for (int i = 0; i < userResponses.size(); i++) {
            if (userResponses.get(i)) {
                bitSum |= (1 << i); // i번째 비트에 해당하는 값을 더함
            }
        }
        return bitSum;
    }

    private Champion findBestMatchingChampion(List<Champion> champions, List<Boolean> userResponses) {
        List<Champion> currentChampions = new ArrayList<>(champions);

        for (int i = 0; i < userResponses.size(); i++) {
            final int index = i; // 람다 표현식에서 사용하는 인덱스는 final 또는 effectively final이어야 함
            boolean userResponse = userResponses.get(index);
            List<Champion> prevChampions = new ArrayList<>(currentChampions); // 이전 상태 저장

            currentChampions = currentChampions.stream()
                    .filter(champion -> getChampionResponseByIndex(champion, index) == userResponse)
                    .toList();

            // 조건에 맞는 챔피언이 없을 경우 이전 상태에서 첫 번째 챔피언 반환
            if (currentChampions.isEmpty()) {
                return prevChampions.get(0);
            }
            // 1개의 챔피언만 남으면 해당 챔피언 반환
            if (currentChampions.size() == 1) {
                return currentChampions.get(0);
            }
        }
        return currentChampions.get(0);
    }

    // 특정 챔피언의 질문 응답을 인덱스에 맞춰 가져오는 메서드
    private boolean getChampionResponseByIndex(Champion champion, int index) {
        return champion.getChampionClassifications().stream()
                .filter(classification -> classification.getQuestionIndex() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("getChampionResponseByIndex method Invalid index: " + index))
                .getValue();
    }

    // Champion -> ChampionDTO
    private ChampionDTO convertChampionToDTO(Champion champion) {
        return new ChampionDTO(
                champion.getId(),
                champion.getName(),
                champion.getImg(),
                champion.getDescription()
        );
    }
}
