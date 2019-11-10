package neu.cytang.connecteddevices.labs.module08;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class TempSensorCloudPublisherApp {
	private static final Logger _Logger = Logger.getLogger(TempSensorCloudPublisherApp.class.getName());
	private static TempSensorCloudPublisherApp _App;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_App = new TempSensorCloudPublisherApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// params
	private UbidotsClientConnector _httpClient;

	// constructors
	public TempSensorCloudPublisherApp() {
		super();
	}

	// public methods
	public void start() throws MqttPersistenceException, MqttException, InterruptedException {

		_httpClient = new UbidotsClientConnector();
		_httpClient.connect();

		// the topic name of tempsensor in ubidots
		String topicName = "/v1.6/devices/tcydevice/TempSensor";

		while (true) {
			// generate a sample data and publish to ubidots
			double temperature = Math.random() * 50;
			_Logger.info("current temperature:" + temperature);
			_httpClient.publishMessage(topicName, 0, temperature);
			_Logger.info("published successfully by using ubidots API in Java publisher");
			Thread.sleep(60000);
		}

	}

}
