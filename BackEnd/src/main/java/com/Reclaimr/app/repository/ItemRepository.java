package com.Reclaimr.app.repository;

import com.Reclaimr.app.models.LnFItems;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<AbstractReadWriteAccess.Item, String> {
    public void save(LnFItems item);
}
