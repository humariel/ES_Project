package application;

import org.springframework.data.mongodb.repository.MongoRepository;

interface ParishRepository extends MongoRepository<Parish, String>, CustomParishRepository {

}