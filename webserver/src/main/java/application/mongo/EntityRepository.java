package application.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.data.Value;

public interface EntityRepository extends MongoRepository<Value, String> {

}