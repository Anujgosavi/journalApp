package net.ImissHer.demo.repo;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

  User findByUserName(String username);

}


//  controller --> service -->> repository
