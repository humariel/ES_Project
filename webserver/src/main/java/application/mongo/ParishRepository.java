package application.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.data.Parish;

public interface ParishRepository extends MongoRepository<Parish, String>, CustomParishRepository {

}