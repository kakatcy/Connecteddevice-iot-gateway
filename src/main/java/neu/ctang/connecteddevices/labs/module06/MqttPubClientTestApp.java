package neu.ctang.connecteddevices.labs.module06;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import neu.ctang.connecteddevices.common.DataUtil;
import neu.ctang.connecteddevices.common.SensorData;

public class MqttPubClientTestApp {

	// static
	private static final Logger _Logger = Logger.getLogger(MqttPubClientTestApp.class.getName());
	private static MqttPubClientTestApp _App;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_App = new MqttPubClientTestApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// params
	private MqttClientConnector _mqttClient;

	// constructors
	public MqttPubClientTestApp() {
		super();
	}

	// public methods
	public void start() throws MqttPersistenceException, MqttException {

		// generate a sample data and add to a SensorData object
		SensorData sensordata = new SensorData();
		float temperture = (float) (Math.random() * 30);
		sensordata.addValue(temperture);

		// convert sensordata to jsondata, and log a message of the first json
		DataUtil datautil = new DataUtil();
		String jsondata = datautil.toJsonFromSensorData(sensordata);
		_Logger.info(jsondata);
		

		_mqttClient = new MqttClientConnector();
		_mqttClient.connect();
		// TODO: use a topic name you select (does not have to be ‘test’)
		String topicName = "test";
		// only for subscribing...
		// _mqttClient.subscribeToTopic(topicName); // you must implement this method
		// yourself
		// only for publishing...
		_mqttClient.publishMessage(topicName, 2, jsondata.getBytes());
		_mqttClient.disconnect();
		System.exit(0);
	}

}
