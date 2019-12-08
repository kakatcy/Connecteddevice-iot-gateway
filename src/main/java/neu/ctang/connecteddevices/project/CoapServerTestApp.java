package neu.ctang.connecteddevices.project;

public class CoapServerTestApp {

	public static void main(String[] args) {
		CoapServerTestApp _App = new CoapServerTestApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private var's
	private CoapServerConnector _coapServer;

	// constructors
	public CoapServerTestApp()
    {
		super(); 
	}

	// public methods
	public void start() {
		_coapServer = new CoapServerConnector();
		_coapServer.start();
	}

}
