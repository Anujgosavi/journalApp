package net.ImissHer.demo.cache;

import jakarta.annotation.PostConstruct;
import net.ImissHer.demo.entity.ConfigJournalEntity;
import net.ImissHer.demo.repo.ConfigJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppCache {

    @Autowired
    ConfigJournalRepository configJournalRepository ;

    public Map<String , String> APP_CACHE ;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
       List<ConfigJournalEntity> lst = configJournalRepository.findAll() ;

       for(ConfigJournalEntity configJournalEntity : lst){
           APP_CACHE.put(configJournalEntity.getKey(), configJournalEntity.getValue());
       }
    }
}
