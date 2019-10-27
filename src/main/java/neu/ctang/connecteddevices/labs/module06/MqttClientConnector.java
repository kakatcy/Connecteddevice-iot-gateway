package neu.ctang.connecteddevices.labs.module06;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientConnector {

	private static int qos = 2;
	private static String broker = "tcp://localhost:1883";
	private static final Logger _Logger = Logger.getLogger(MqttPubClientTestApp.class.getName());

	MqttClient mqttClient;
	String clientId = "temp_java";

	// connect with the broker
	public void connect() throws MqttException {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttConnectOptions connOpts = new MqttConnectOptions();

		connOpts.setCleanSession(false);

		connOpts.setConnectionTimeout(70);
		connOpts.setKeepAliveInterval(70);

		mqttClient = new MqttClient(broker, clientId, persistence);
		mqttClient.setCallback(new PushCallback());
		mqttClient.connect(connOpts);
	}

	// publish message via broker
	public void publishMessage(String topicName, int qos, byte[] payload) {
		try {
			MqttMessage message = new MqttMessage(payload);
			message.setQos(qos);
			mqttClient.publish(topicName, message);
		} catch (MqttException me) {
			// TODO Auto-generated catch block
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	// disconnected with broker
	public void disconnect() {
		try {
			Thread.sleep(5000);
			_Logger.info("disconnecting");
			mqttClient.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}