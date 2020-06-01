package application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class TriggerRepositoryImpl implements CustomTriggerRepo {

    @Autowired
    private MongoTemplate mongotemplate;
    
    @Override
    public List<Trigger> findAllByAlarm(String alarm) {

        Criteria criteria = Criteria.where("alarm").is(alarm);
        Query query = Query.query(criteria);
        return mongotemplate.findAll(Trigger.class);
    }    
}