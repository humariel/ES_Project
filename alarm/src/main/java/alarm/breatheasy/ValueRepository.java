package alarm.breatheasy;

import org.springframework.data.mongodb.repository.MongoRepository;

import alarm.breatheasy.Value;

public interface ValueRepository extends MongoRepository<Value, String> {

}