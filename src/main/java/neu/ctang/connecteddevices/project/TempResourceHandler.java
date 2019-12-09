package neu.ctang.connecteddevices.project;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.logging.Logger;

public class TempResourceHandler extends CoapResource {

	private static final Logger _Logger = Logger.getLogger(CoapServerConnector.class.getName());

	// constructors ​
	public TempResourceHandler() {
		super("temp", true);
	}

	// public methods
	@Override
	public void handleGET(CoapExchange ce) {
		// respond "get worked" to client when client invoke GET
		ce.respond(ResponseCode.VALID, "GET worked");
		_Logger.info("Received GET request from client");

	}

	@Override
	public void handlePOST(CoapExchange ce) {
		// receive data from the client
		byte[] payload = ce.getRequestPayload();
		try {
			String sensorData = new String(payload, "UTF-8");
			// log the sensor data
			_Logger.info("the data(client post):\n" + sensorData);
			
			//extract temperature and humidity
			String temperature = sensorData.split(",")[0];
			String humidity = sensorData.split(",")[1];
			
			//connect to Ubidots
			UbidotsClientConnector _httpClient = new UbidotsClientConnector();
			_httpClient.connect();
			
			// the topic name of tempsensor in ubidots
			//publish temperature and humidity to ubidots respectively
			String topicName = "/v1.6/devices/finaldevice/temperature";
			double temperature1 = Double.parseDouble(temperature);
			_httpClient.publishMessage("5de42e6e1d84725dc4107c0f",topicName, 0, temperature1);
			
			String topicNameHumidity = "/v1.6/devices/finaldevice/humidity";
			double humidity1 = Double.parseDouble(humidity);
			_httpClient.publishMessage("5de42e7e1d84725d134ed82e",topicNameHumidity, 0, humidity1);
			_Logger.info("published successfully by using ubidots API");
			Thread.sleep(60000);
		} catch (Exception e) {
			e.printStackTrace();
			ce.respond(ResponseCode.BAD_REQUEST, "Invalid String");
		}
	}

	@Override
	public void handlePUT(CoapExchange ce) {
		// receive data from the client
		byte[] payload = ce.getRequestPayload();
		try {
			String value = new String(payload, "UTF-8");
			// respond value to client when client invoke PUT
			_Logger.info("Reveived PUT request from client");
			ce.respond(ResponseCode.CHANGED, value);
		} catch (Exception e) {
			e.printStackTrace();
			ce.respond(ResponseCode.BAD_REQUEST, "Invalid String");
		}
	}

	@Override
	public void handleDELETE(CoapExchange ce) {
		delete();
		// respond deleted code when client invoke deleted
		ce.respond(ResponseCode.DELETED);
		_Logger.info("Reveived DELETED request from client");
	}
}
