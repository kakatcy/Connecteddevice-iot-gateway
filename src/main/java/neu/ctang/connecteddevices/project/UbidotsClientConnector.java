package neu.ctang.connecteddevices.project;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.labbenchstudios.edu.connecteddevices.common.ConfigConst;
import com.labbenchstudios.edu.connecteddevices.common.ConfigUtil;
import com.ubidots.*;
import com.ubidots.ApiClient;
import com.ubidots.DataSource;
import com.ubidots.Variable;
import com.google.gson.*;

public class UbidotsClientConnector {
	String _apiToken = null;
	ApiClient _apiClient = null;

	public void connect() {
		// new ApiClient to send or receive data by using ubidots API
		_apiClient = new ApiClient("BBFF-e790d7ff33fd7123071f6f5b7b217344fe2");
	}

	public void publishMessage(String label, String topicName, int qos, double payload) throws MqttException {
		// publish payload to temperature or humidity of ubidots
		// label is the id of sensordata
		Variable var = _apiClient.getVariable(label);
		var.saveValue(payload);
	}
	
	public double getMessage(String label) {
		//get messages from ubidots
		Variable var = _apiClient.getVariable(label);
		Value[] results = var.getValues();
		Value result = results[0];
		System.out.println(result.getValue());
		return result.getValue();
	}
}
