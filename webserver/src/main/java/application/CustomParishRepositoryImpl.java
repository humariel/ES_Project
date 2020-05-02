package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import application.Parish;

@Repository
public class CustomParishRepositoryImpl implements CustomParishRepository {

    @Autowired
    private MongoTemplate mongotemplate;

    @Override
    public Parish findParishContainingEntity(double[] point) {

        GeoJsonPoint geoPoint = new GeoJsonPoint(new Point(point[1], point[0]));
        Criteria geoCriteria = Criteria.where("geometry").intersects(geoPoint);
        Query query = Query.query(geoCriteria);
        return mongotemplate.findOne(query, Parish.class);
    }

}