package neu.ctang.connecteddevices.project;

import java.util.logging.Logger;

//import java.util.logging.Logger;

public class CoapClient {
	// static
	private static final Logger _Logger = Logger.getLogger(CoapClient.class.getName());

	private static CoapClient _App;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_App = new CoapClient();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private var's
	private CoapClientConnector _coapClient;

	// constructors
	/**
	 * *
	 */
	public CoapClient() {
		super();
	}

	// public methods
	/**
	 * Connect to the CoAP server
	 *
	 */
	public void start() {
		//connect to the ubidots
		UbidotsClientConnector _httpClient = new UbidotsClientConnector();
		_httpClient.connect();
		_Logger.info("connect to Ubidots successfully");
		while(true) {
			try {
			//Using the Ubidots API to get airconditioner data
			double airconditioner = _httpClient.getMessage("5de70f221d84726986b65e77");
			
			//Using the Ubidots API to get watering data
			double watering = _httpClient.getMessage("5de70f071d84726ad59f68b5");
			
			String payload = Double.toString(airconditioner) +","+ Double.toString(watering);
			_Logger.info("current airconditioner temperature:" + airconditioner + "current watering" + watering);
			
			
			if(!payload.equals(null)) {
				//true means coaps; false means coap!
				_coapClient = new CoapClientConnector("10.0.0.175", false); 
				_coapClient.runTests("temp", payload);
			}
			
			Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
}
