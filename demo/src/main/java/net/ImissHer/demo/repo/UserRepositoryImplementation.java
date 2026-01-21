package net.ImissHer.demo.repo;

import net.ImissHer.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImplementation {

    @Autowired
    MongoTemplate mongoTemplate ;

    public List<User> getUserBySA(){
        Query q = new Query();
        q.addCriteria(Criteria.where("email").exists(true));
        q.addCriteria(Criteria.where("sentimentAnalysis").is(true)) ;

        return mongoTemplate.find(q , User.class) ;
    }
}
