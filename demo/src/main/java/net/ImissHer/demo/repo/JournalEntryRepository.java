package net.ImissHer.demo.repo;

import net.ImissHer.demo.*;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<journalEntry, ObjectId> {



}


//  controller --> service -->> repository
