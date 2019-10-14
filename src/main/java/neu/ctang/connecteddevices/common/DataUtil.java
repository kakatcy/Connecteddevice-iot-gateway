package neu.ctang.connecteddevices.common;

import com.google.gson.Gson;

public class DataUtil {

	public String toJsonFromSensorData(SensorData sensorData) {
		String jsonData = null;
		if (sensorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sensorData);
		}
		return jsonData;
	}
	
	public SensorData toSensorDataFromJson(String jsonData) {
		SensorData sensordata = new SensorData();
		
		return sensordata;
	}
	
	public SensorData toSensorDataFromJsonFile(String filename, String path) {
		SensorData sensordata = new SensorData();
		
		return sensordata;
	}

	public String toJsonFromActuatorData(ActuatorData actuatorData) {
		String jsonData = null;
		if (actuatorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(actuatorData);
		}
		return jsonData;
	}
	
	public ActuatorData toActuatorDataFromJson(String jsonData) {
		ActuatorData actuatorData = null;
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			actuatorData = gson.fromJson(jsonData, ActuatorData.class);
		}
		return actuatorData;
	}
	
	public ActuatorData toActuatorDataFromJsonFile(String filename, String path) {
		ActuatorData actuatorData = null;
		
		return actuatorData;
	}
	
}
