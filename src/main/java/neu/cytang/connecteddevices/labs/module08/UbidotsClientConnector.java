package neu.cytang.connecteddevices.labs.module08;

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
//		ConfigUtil configUtil = ConfigUtil.getInstance();
//		String apiKey = configUtil.getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.API_KEY);
//		String baseUrl = configUtil.getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.BASE_URL_KEY);
//		_apiToken = apiKey;
//		_apiClient = new ApiClient(); // this is a Ubidots class
		// need to call 'fromToken()' on ApiClient before updating base URL,
		// as it will create the underlying ServerBridge if not already created
//		_apiClient.fromToken(apiKey);
//		if (baseUrl != null && baseUrl.length() > 0) {
//			_apiClient.setBaseUrl(baseUrl);
//		}
		// once you have a DataSource ref, you can retrieve / update variable data (your
		// topic)
		
		_apiClient = new ApiClient("BBFF-e790d7ff33fd7123071f6f5b7b217344fe2");
	//	DataSource[] dataSourceArr = _apiClient.fromToken(_apiToken).getDataSources();
		
		//var.getValues();
		
	}
	
	public void publishMessage(String topicName, int qos, double payload) throws MqttException {
		Variable var = _apiClient.getVariable("5dc7337d1d847235ca5b5e3e");
		var.saveValue(payload);
	}
}
