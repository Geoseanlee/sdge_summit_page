package com.sdgs.service;

import com.sdgs.entity.AboutMoreSection;
import java.util.List;

public interface AboutMoreSectionService {
    List<AboutMoreSection> getAllPublishedSections();
    List<AboutMoreSection> getAllSectionsForAdmin();
    AboutMoreSection createSection(AboutMoreSection section);
    AboutMoreSection updateSection(AboutMoreSection section);
    void deleteSection(Long id);
}