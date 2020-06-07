package alarm.breatheasy;

import org.springframework.data.mongodb.repository.MongoRepository;

import alarm.breatheasy.Parish;

public interface ParishRepository extends MongoRepository<Parish, String>, CustomParishRepository {

}