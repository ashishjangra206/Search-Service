package com.lsb.searchservice.client;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FolderServiceClientFallBack implements FolderSearchClient{

    @Override
    public List<Map<String, Object>> getAllFolders() {
        System.out.println("Folder Service unavailable.....");
        return List.of();
    }

    @Override
    public List<Map<String, Object>> getAllFilesByParent(Long parentId) {
        System.out.println("Folder Service not working.....");
        return List.of();
    }
}
