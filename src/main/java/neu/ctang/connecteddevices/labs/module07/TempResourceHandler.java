package neu.ctang.connecteddevices.labs.module07;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import neu.ctang.connecteddevices.common.DataUtil;
import neu.ctang.connecteddevices.common.SensorData;

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
			String jsondata2 = new String(payload, "UTF-8");
			// log the second json data
			_Logger.info("the second jsondata(client post):\n" + jsondata2);

			// convert the json data to sensordata
			DataUtil datautil = new DataUtil();
			SensorData sensordata = datautil.toSensorDataFromJson(jsondata2);

			// convert the sensordata to json data again
			String jsondata3 = datautil.toJsonFromSensorData(sensordata);
			_Logger.info("the third jsondata(server converted):\n" + jsondata3);
			ce.respond(ResponseCode.CHANGED, jsondata2);
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
