package neu.ctang.connecteddevices.project;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.logging.*;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.WebLink;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import com.labbenchstudios.edu.connecteddevices.common.*;

public class CoapClientConnector {
	String _protocol;
	int _port;
	String _host;
	String _serverAddr;
	CoapClient _clientConn;
	URI uri;
	boolean _isInitialized = false;
	boolean useNON = false;
	
	private static final Logger _Logger =
			Logger.getLogger(CoapClientConnector.class.getName());
	
	public CoapClientConnector(String host, boolean isSecure) {
		super();
		if (isSecure) {
			_protocol = ConfigConst.SECURE_COAP_PROTOCOL;
			_port = ConfigConst.SECURE_COAP_PORT;
		} else {
			_protocol = ConfigConst.DEFAULT_COAP_PROTOCOL;
			_port = ConfigConst.DEFAULT_COAP_PORT;
		}
		if (host != null && host.trim().length() > 0) {
			_host = host;
		} else {
			_host = ConfigConst.DEFAULT_COAP_SERVER;
		}
		// NOTE: URL does not have a protocol handler for "coap",
		// so we need to construct the URL manually
		_serverAddr = _protocol + "://" + _host + ":" + _port;
//		_serverAddr = "coap://localhost:5683";
		try {
			uri = new URI(_serverAddr);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_Logger.info("Using URL for server conn: " + _serverAddr);
		_Logger.info("URI:" + uri);
	}

	public void sendPutRequest(String payload) {
	//	initClient();
		initClient("temp1");
		if (useNON) {
			_clientConn.useNONs();
		}
		_Logger.info("current payload is " + payload);
		if(!payload.equals(null)) {
			//put actuators data to constrained devices 
			String getInfo = _clientConn.put(payload,MediaTypeRegistry.TEXT_PLAIN).getResponseText();
			_Logger.info(getInfo);
		}
	}

	private void initClient() {
		initClient(null);
	}

	//add the resourcename to the address
	private void initClient(String resourceName) {
		if (_isInitialized) {
			return;
		}
		if (_clientConn != null) {
			_clientConn.shutdown();
			_clientConn = null;
		}
		try {
			if (resourceName != null) {
				_serverAddr += "/" + resourceName;
			}
			_clientConn = new CoapClient(_serverAddr);
		//	uri = new URI(_serverAddr);
			_Logger.info("Created client connection to server / resource: " + _serverAddr);
		} catch (Exception e) {
			_Logger.log(Level.SEVERE,"Failed to connect to broker: "+ getCurrentUri(),e);
		}
	}

	private String getCurrentUri() {
		// TODO Auto-generated method stub
		return _clientConn.getURI();
	}

	public void runTests(String temp, String payload) {
		// TODO Auto-generated method stub
		//put payload to constrained devices
		sendPutRequest(payload);
	}
}