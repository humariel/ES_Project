package breatheasy.alerts.alerts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParishRepository extends MongoRepository<Parish, String>, CustomParishRepository {

}