package net.ImissHer.demo.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data  //  all methods
@NoArgsConstructor //
public class journalEntry {
    @Id
    private ObjectId id; // kinda like instance of db
    @NonNull
    private String title;
    private String content ;
    private LocalDateTime date ;



}
