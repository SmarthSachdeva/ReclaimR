package com.Reclaimr.app.service;

import com.Reclaimr.app.models.LnFItems;
import com.Reclaimr.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(LnFItems item) {
        log.info("Attempting to save item: {}", item);

        // Validation checks
        if (item == null) {
            log.error("Failed to save item: Item is null");
            throw new IllegalArgumentException("Item cannot be null");
        }

        if (!StringUtils.hasText(item.getName())) {
            log.error("Failed to save item: Name is missing");
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        if (!StringUtils.hasText(item.getDescription())) {
            log.warn("Item description is empty: Proceeding with save");
        }

        if (item.getUser() == null) {
            log.error("Failed to save item: User is not associated with the item");
            throw new IllegalArgumentException("User cannot be null");
        }

        // Save item to the repository
        try {
            itemRepository.save(item);
            log.info("Item saved successfully: {}", item);
        } catch (Exception e) {
            log.error("Failed to save item: {}", item, e);
            throw new RuntimeException("Could not save item", e);
        }
    }
}
