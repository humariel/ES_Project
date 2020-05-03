package application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import application.Value;

@Repository
public class CustomValueRepositoryImpl implements CustomValueRepository {

    @Autowired
    private MongoTemplate mongotemplate;

    @Override
    public List<Value> getValuesFromParish(String parish, long timestamp){

        Criteria parishEquals = Criteria.where("parish").is(parish);
        Criteria timeInterval = Criteria.where("timestamp").gte(timestamp);
        Query query = Query.query(parishEquals).addCriteria(timeInterval);
        return mongotemplate.find(query, Value.class);
    }


}