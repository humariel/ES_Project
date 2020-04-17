package breatheeasy.app.Repository;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BreathData implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long timestamp;
	private double precipProbability;
	private double precipIntensity;
	private double tempHigh;
	private double tempLow;
	private double humidity;
	private double pressure;
	private double ozone;
	private int uvIndex;

	private static final long serialVersionUID = 1L;

	protected BreathData() {}

	public BreathData(Long timestamp) {
		this.timestamp = timestamp;
	}

	public BreathData(Long timestamp, double precipProbability, double precipIntensity, 
						double tempHigh, double tempLow, double humidity, double pressure, 
						double ozone, int uvIndex) {
		this.timestamp = timestamp;
		this.precipProbability = precipProbability;
		this.precipIntensity = precipIntensity;
		this.tempHigh = tempHigh;
		this.tempLow = tempLow;
		this.humidity = humidity;
		this.pressure = pressure;
		this.ozone = ozone;
		this.uvIndex = uvIndex;
	}


	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public double getPrecipProbability() {
		return this.precipProbability;
	}

	public void setPrecipProbability(double precipProbability) {
		this.precipProbability = precipProbability;
	}

	public double getPrecipIntensity() {
		return this.precipIntensity;
	}

	public void setPrecipIntensity(double precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public double getTempHigh() {
		return this.tempHigh;
	}

	public void setTempHigh(double tempHigh) {
		this.tempHigh = tempHigh;
	}

	public double getTempLow() {
		return this.tempLow;
	}

	public void setTempLow(double tempLow) {
		this.tempLow = tempLow;
	}

	public double getHumidity() {
		return this.humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return this.pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getOzone() {
		return this.ozone;
	}

	public void setOzone(double ozone) {
		this.ozone = ozone;
	}

	public int getUvIndex() {
		return this.uvIndex;
	}

	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + this.id + "'" +
			", timestamp='" + this.timestamp + "'" +
			", precipProbability='" + this.precipProbability + "'" +
			", precipIntensity='" + this.precipIntensity + "'" +
			", tempHigh='" + this.tempHigh + "'" +
			", tempLow='" + this.tempLow + "'" +
			", humidity='" + this.humidity + "'" +
			", pressure='" + this.pressure + "'" +
			", ozone='" + this.ozone + "'" +
			", uvIndex='" + this.uvIndex + "'" +
			"}";
	}
}
