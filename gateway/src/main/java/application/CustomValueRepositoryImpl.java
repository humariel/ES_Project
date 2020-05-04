package application;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
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

        MatchOperation parishEquals = Aggregation.match(
            Criteria.where("parish").is(parish).and("timestamp").gte(timestamp)
        );

        GroupOperation groupByStateAndSumPop = Aggregation.group("parish")
            .avg("temperature").as("temperature")
            .avg("humidity").as("humidity")
            .avg("pressure").as("pressure")
            .avg("pm10").as("pm10");

        Aggregation aggr = Aggregation.newAggregation(
            parishEquals,
            groupByStateAndSumPop
        );

        return mongotemplate.aggregate(aggr, "value", Value.class).getMappedResults();
    }


}