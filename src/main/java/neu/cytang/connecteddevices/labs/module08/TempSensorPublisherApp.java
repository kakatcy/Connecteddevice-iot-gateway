package neu.cytang.connecteddevices.labs.module08;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import neu.ctang.connecteddevices.common.DataUtil;
import neu.ctang.connecteddevices.common.SensorData;
import neu.ctang.connecteddevices.labs.module06.MqttClientConnector;

import com.ubidots.*;


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
		_mqttClient = new MqttClientConnector("ssl://industrial.api.ubidots.com:8883","BBFF-B7HsNg1Sv3UESmoikX8oyyNxyx4jts","/Users/cytang/program/connected devices/ubidots_cert.pem");
		_mqttClient.connect();
		// TODO: use a topic name you select (does not have to be ‘test’)
	//	String topicName = "test";
		String topicName = "/v1.6/devices/tcydevice/TempSensor";
		while(true) {
			float temperature = (float) (Math.random() * 50);
			System.out.println("temp" + temperature);
			_mqttClient.publishMessage(topicName, 0, Float.toString(temperature).getBytes());
			Thread.sleep(60000);
		}
	}

}
