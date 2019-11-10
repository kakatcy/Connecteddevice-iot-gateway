package neu.cytang.connecteddevices.labs.module08;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.labbenchstudios.edu.connecteddevices.common.ConfigConst;
import com.labbenchstudios.edu.connecteddevices.common.ConfigUtil;
import com.ubidots.ApiClient;
import com.ubidots.DataSource;
import com.ubidots.Variable;


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

		//_mqttClient = new MqttClientConnector();
		_httpClient = new UbidotsClientConnector();
		_httpClient.connect();
		// TODO: use a topic name you select (does not have to be ‘test’)
		String topicName = "/v1.6/devices/tcydevice/TempSensor";
		
		while(true) {
			// generate a sample data and add to a SensorData object
		//	SensorData sensordata = new SensorData();
			double temperature = Math.random() * 50;
			System.out.println(temperature);
			_httpClient.publishMessage(topicName, 0, temperature);
			Thread.sleep(60000);
		}
		
		
//		ApiClient api = new ApiClient("BBFF-hFd4dD9nsT66ZtuZUDjkFbDkiedAQ9");
//		DataSource dataSource = api.createDataSource("mydata");
//		Variable variable = dataSource.createVariable("TempSensor");
		
		
//		_httpClient.disconnect();
		//System.exit(0);
	}
	

}
