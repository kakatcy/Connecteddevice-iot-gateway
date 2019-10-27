package neu.ctang.connecteddevices.labs.module06;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback{
    private static final Logger _Logger = Logger.getLogger(MqttPubClientTestApp.class.getName());

    //called when connection lost
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		_Logger.info("connectionlost");
		
	}
	
    //called when connection failure
	public void onFailure(Throwable cause) {
		_Logger.info("connection failure");
	}
	
    //called when messages arrived
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		_Logger.info("message arrived");
		_Logger.info(Integer.toString(message.getQos()));
		_Logger.info(new String(message.getPayload()));
		
	}

    //called when delivery completed
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		_Logger.info("delivery completion");
		
	}

}
