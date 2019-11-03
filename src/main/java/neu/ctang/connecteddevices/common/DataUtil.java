package neu.ctang.connecteddevices.common;

import java.io.*;

import org.json.JSONObject;

import com.google.gson.Gson;

public class DataUtil {
	// convert sensordata object to json format data
	public String toJsonFromSensorData(SensorData sensorData) {
		String jsonData = null;
		if (sensorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sensorData);
			JSONObject jsonobj = new JSONObject(jsonData);
			jsonobj.remove("_appName");
			jsonobj.remove("_exitCode");
			return jsonobj.toString();

		}

		// System.out.println("jsondata:"+jsonData);
		return jsonData;
	}

	// convert json format data to sensordata object
	public SensorData toSensorDataFromJson(String jsonData) {
		SensorData sensordata = null;
		//System.out.println(jsonData);
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			sensordata = gson.fromJson(jsonData, SensorData.class);
		}
		return sensordata;
	}

	// read a json file and convert it to sensordata object
	public SensorData toSensorDataFromJsonFile(String filename, String path) {
		// Gson gson = new Gson();
		// System.out.println(path+filename);
		SensorData sensordata = new SensorData();
		StringBuilder str = new StringBuilder();
		try {
			String row = null;
			BufferedReader br = new BufferedReader(new FileReader(path + filename));
			while ((row = br.readLine()) != null) {
				// System.out.println(row.toString());
				str.append(row);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("read file:"+ str.toString());
		sensordata = toSensorDataFromJson(str.toString());

		return sensordata;
	}

	//// convert actuatordata object to json format data
	public String toJsonFromActuatorData(ActuatorData actuatorData) {
		String jsonData = null;
		if (actuatorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(actuatorData);
		}
		return jsonData;
	}

	// convert json format data to actuatordata object
	public ActuatorData toActuatorDataFromJson(String jsonData) {
		ActuatorData actuatorData = null;
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			actuatorData = gson.fromJson(jsonData, ActuatorData.class);
		}
		return actuatorData;
	}

	// read a json file and convert it to actuatordata object
	public ActuatorData toActuatorDataFromJsonFile(String filename, String path) {
		ActuatorData actuatorData = new ActuatorData();
		StringBuilder str = new StringBuilder();
		try {
			String row = null;
			BufferedReader br = new BufferedReader(new FileReader(path + filename));
			while ((row = br.readLine()) != null) {
				str.append(row);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		actuatorData = toActuatorDataFromJson(str.toString());
		return actuatorData;
	}

}
