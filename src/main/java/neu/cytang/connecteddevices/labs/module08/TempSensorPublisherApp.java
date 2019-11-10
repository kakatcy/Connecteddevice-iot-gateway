package neu.cytang.connecteddevices.labs.module08;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import neu.ctang.connecteddevices.labs.module06.MqttClientConnector;

public class TempSensorPublisherApp {
	private static final Logger _Logger = Logger.getLogger(TempSensorPublisherApp.class.getName());
	private static TempSensorPublisherApp _App;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_App = new TempSensorPublisherApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// params
	private MqttClientConnector _mqttClient;

	// constructors
	public TempSensorPublisherApp() {
		super();
	}

	// public methods
	public void start() throws MqttPersistenceException, MqttException, InterruptedException {
		_mqttClient = new MqttClientConnector("ssl://industrial.api.ubidots.com:8883",
				"BBFF-B7HsNg1Sv3UESmoikX8oyyNxyx4jts", "/Users/cytang/program/connected devices/ubidots_cert.pem");
		_mqttClient.connect();

		// topic name of tempsensor in ubidots
		String topicName = "/v1.6/devices/tcydevice/TempSensor";
		while (true) {
			// generate temperature and publish to ubidots
			float temperature = (float) (Math.random() * 50);
			_Logger.info("current temperature:" + temperature);
			_mqttClient.publishMessage(topicName, 0, Float.toString(temperature).getBytes());
			_Logger.info("published successfully by Java publisher");
			Thread.sleep(6000);
		}
	}

}
