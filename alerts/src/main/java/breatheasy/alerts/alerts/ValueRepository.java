package breatheasy.alerts.alerts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ValueRepository extends MongoRepository<Value, String> {

}