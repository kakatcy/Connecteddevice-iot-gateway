package neu.ctang.connecteddevices.labs.module06;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLSocketFactory;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.labbenchstudios.edu.connecteddevices.common.CertManagementUtil;

public class MqttClientConnector {

	private static int qos = 2;
	//private static String broker = "tcp://nannoiot.franxx.live:1883";
	//private static String broker = "tcp://140.238.32.242:1883";
	private static String broker = "tcp://localhost:1883";
	private static final Logger _Logger = Logger.getLogger(MqttPubClientTestApp.class.getName());
	
	MqttClient mqttClient;
	String clientId = "temp_java";
	MqttConnectOptions connOpts;

	private static boolean useTLS = false;
	
	public MqttClientConnector() {
		// TODO Auto-generated constructor stub
		connOpts = new MqttConnectOptions();
	}
	
	public MqttClientConnector(String hostname, String token, String certFilepath) {
		CertManagementUtil certManagemenUtil = CertManagementUtil.getInstance();
		SSLSocketFactory sslSocketFactory = certManagemenUtil.loadCertificate(certFilepath);
		broker = hostname;
		 
		connOpts = new MqttConnectOptions();
		connOpts.setSocketFactory(sslSocketFactory);
		
		String[] uris = new String[1];
		uris[0] = hostname;
		connOpts.setServerURIs(uris);
		connOpts.setUserName(token);
		connOpts.setPassword("".toCharArray());
	}
	
	// connect with the broker
	public void connect() throws MqttException {
		MemoryPersistence persistence = new MemoryPersistence();
	//	MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(false);
		connOpts.setConnectionTimeout(70);
		connOpts.setKeepAliveInterval(70);
	
		mqttClient = new MqttClient(broker, clientId, persistence);

		System.out.println("connect to broker:"+ broker);
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

	public void subscribeToTopic(String topicName, int qos) {
			try {
				mqttClient.subscribe(topicName,qos);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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