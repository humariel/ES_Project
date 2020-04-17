package breatheeasy.app.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BreathData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long timestamp;

	private static final long serialVersionUID = 1L;


	protected BreathData() {}

	public BreathData(Long timestamp) {
		this.timestamp=timestamp;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp=timestamp;
	}


	public BreathData(long id, long timestamp) {
		this.id = id;
		this.timestamp = timestamp;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BreathData id(long id) {
		this.id = id;
		return this;
	}

	public BreathData timestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", timestamp='" + getTimestamp() + "'" +
			"}";
	}

}
