package neu.ctang.connecteddevices.project;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.labbenchstudios.edu.connecteddevices.common.ConfigConst;
import com.labbenchstudios.edu.connecteddevices.common.ConfigUtil;
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

	public void publishMessage(String topicName, int qos, double payload) throws MqttException {
		// publish payload to tempsensor of ubidots
		// "5dc7337d1d847235ca5b5e3e" is the id of tempsensor
		Variable var = _apiClient.getVariable("5dc7337d1d847235ca5b5e3e");
		var.saveValue(payload);
	}
}
