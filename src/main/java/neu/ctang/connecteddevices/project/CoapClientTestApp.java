package neu.ctang.connecteddevices.project;

//import java.util.logging.Logger;

public class CoapClientTestApp {
	// static
//	private static final Logger _Logger = Logger.getLogger(CoapClientTestApp.class.getName());

	private static CoapClientTestApp _App;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_App = new CoapClientTestApp();
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
	public CoapClientTestApp() {
		super();
	}

	// public methods
	/**
	 * Connect to the CoAP server
	 *
	 */
	public void start() {
		//true means coaps; false means coap!
		_coapClient = new CoapClientConnector("127.0.0.1", false); 
		_coapClient.runTests("temp");
	}
}
