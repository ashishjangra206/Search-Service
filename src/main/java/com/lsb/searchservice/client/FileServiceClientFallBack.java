package com.lsb.searchservice.client;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FileServiceClientFallBack implements FileSearchClient {

    @Override
    public List<Map<String, Object>> getAllFiles() {
        System.out.println("File Service unavailable.....");
        return List.of();
    }

    @Override
    public List<Map<String, Object>> getAllFilesByFolderId(Long folderId) {
        System.out.println("File Service not working.....");
        return List.of();
    }
}
