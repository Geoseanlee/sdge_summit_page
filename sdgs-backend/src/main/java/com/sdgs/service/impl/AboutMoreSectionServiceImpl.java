package com.sdgs.service.impl;

import com.sdgs.entity.AboutMoreSection;
import com.sdgs.mapper.AboutMoreSectionMapper;
import com.sdgs.service.AboutMoreSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutMoreSectionServiceImpl implements AboutMoreSectionService {

    private final AboutMoreSectionMapper sectionMapper;

    @Override
    public List<AboutMoreSection> getAllPublishedSections() {
        return sectionMapper.selectAllPublished();
    }
    
    @Override
    public List<AboutMoreSection> getAllSectionsForAdmin() {
        return sectionMapper.selectAllForAdmin();
    }

    @Override
    public AboutMoreSection createSection(AboutMoreSection section) {
        sectionMapper.insert(section);
        return section;
    }

    @Override
    public AboutMoreSection updateSection(AboutMoreSection section) {
        sectionMapper.update(section);
        return section;
    }

    @Override
    public void deleteSection(Long id) {
        sectionMapper.deleteById(id);
    }
}