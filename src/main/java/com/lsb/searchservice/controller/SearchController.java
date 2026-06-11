package com.lsb.searchservice.controller;

import com.lsb.searchservice.client.FileSearchClient;
import com.lsb.searchservice.client.FolderSearchClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final FileSearchClient fileSearchClient;
    private final FolderSearchClient folderSearchClient;

    @GetMapping
    public Map<String, Object> search(@RequestParam String query) {

        List<Map<String, Object>> allFiles = fileSearchClient.getAllFiles();
        List<Map<String, Object>> folders = folderSearchClient.getAllFolders();

        List<Map<String, Object>> fileResult = allFiles.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        List<Map<String, Object>> folderResult = folders.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("files", fileResult);
        result.put("folders", folderResult);
        return result;
    }

    @GetMapping("/files")
    public List<Map<String, Object>> searchFiles(@RequestParam String query) {
        List<Map<String, Object>> allFiles = fileSearchClient.getAllFiles();
        return allFiles.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @GetMapping("/folders")
    public List<Map<String, Object>> searchFolder(@RequestParam String query) {
        List<Map<String, Object>> folders = folderSearchClient.getAllFolders();
        return folders.stream()
                .filter(f -> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

    }

}
