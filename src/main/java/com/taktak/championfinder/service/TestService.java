package com.taktak.championfinder.service;

import com.taktak.championfinder.dto.TestDTO;
import com.taktak.championfinder.model.Test;
import com.taktak.championfinder.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestDTO> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream()
                .map(this::convertTestToDTO)
                .toList();
    }

    public TestDTO getTestById(int id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test not found"));
        return convertTestToDTO(test);
    }

    private TestDTO convertTestToDTO(Test test) {
        return new TestDTO(
                test.getId(),
                test.getName(),
                test.getDescription(),
                test.getThumbnailUrl()
        );
    }
}
