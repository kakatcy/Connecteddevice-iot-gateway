package neu.ctang.connecteddevices.labs.module05;

import java.util.logging.Logger;
import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

public class TempSimulatorApp extends BaseDeviceApp {
	private static final Logger _Logger = Logger.getLogger(TempSimulatorApp.class.getSimpleName());

	public static Logger getLogger() {
		return _Logger;
	}

	public TempSimulatorApp() {
		super();
	}

	public TempSimulatorApp(String appName) {
		super(appName);
	}

	public TempSimulatorApp(String simpleName, String[] args) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void start() throws DeviceApplicationException {
		// TODO Auto-generated method stub
		TempSensorEmulatorTask tempsensor = new TempSensorEmulatorTask();
		tempsensor.t.start();
	}

	@Override
	protected void stop() throws DeviceApplicationException {
		// TODO Auto-generated method stub

	}
}
