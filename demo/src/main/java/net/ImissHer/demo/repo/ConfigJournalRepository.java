package net.ImissHer.demo.repo;

import net.ImissHer.demo.entity.ConfigJournalEntity;
import net.ImissHer.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournalEntity, ObjectId> {


}




//  controller --> service -->> repository
