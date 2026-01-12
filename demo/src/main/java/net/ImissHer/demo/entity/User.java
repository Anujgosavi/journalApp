package net.ImissHer.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@Data  //  all methods
public class User {
    @Id
    private ObjectId id; // kinda like instance of db
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password ;

      @DBRef //  will contain the reference of journal entries kinda like foreign key
    private List<journalEntry> journalEntries  = new ArrayList<>();




}
