package breatheeasy.app.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepo extends CrudRepository<BreathData, Long>{
	List<BreathData> findAll();
}