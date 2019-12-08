package neu.ctang.connecteddevices.project;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.logging.*;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.WebLink;

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

	// public methods
	public void discoverResources() {
		_Logger.info("Issuing discover...");
		initClient();
		Set<WebLink> wlSet = _clientConn.discover();
		if (wlSet != null) {
			for (WebLink wl : wlSet) {
				_Logger.info(" --> WebLink: "+wl.getURI());
			}
		}
	}

	public void sendGetRequest() {
	//	initClient();
		initClient("temp");
		if (useNON) {
			_clientConn.useNONs();
		}
		// NOTE: you must implement the rest of this yourself
		String getInfo = _clientConn.get().getResponseText();
		//String getInfo1 = new String(_clientConn.get().getPayload());
		_Logger.info("send GET request to server\n"+getInfo);
		_Logger.info("Code\n"+_clientConn.get().getCode());
		_Logger.info("Options\n"+_clientConn.get().getOptions());
		
	}

	private void initClient() {
		initClient(null);
	}

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
		//	_clientConn = new CoapClient("coap://localhost:5683/temp");
			_Logger.info("Created client connection to server / resource: " + _serverAddr);
		} catch (Exception e) {
			_Logger.log(Level.SEVERE,"Failed to connect to broker: "+ getCurrentUri(),e);
		}
	}

	private String getCurrentUri() {
		// TODO Auto-generated method stub
		return _clientConn.getURI();
	}

	public void runTests(String temp) {
		// TODO Auto-generated method stub
		sendGetRequest();
		//discoverResources();
	}
}