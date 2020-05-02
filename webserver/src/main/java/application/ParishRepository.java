package application;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.Parish;

public interface ParishRepository extends MongoRepository<Parish, String>, CustomParishRepository {

}