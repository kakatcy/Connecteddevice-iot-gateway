package neu.ctang.connecteddevices.project;

public class CoapServerApp {

	public static void main(String[] args) {
		CoapServerApp _App = new CoapServerApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private var's
	private CoapServerConnector _coapServer;

	// constructors
	public CoapServerApp()
    {
		super(); 
	}

	// public methods
	public void start() {
		_coapServer = new CoapServerConnector();
		_coapServer.start();
	}

}
