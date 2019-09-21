package neu.ctang.connecteddevices.labs.module01;

import java.util.logging.Logger;
import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

public class SystemPerformanceApp extends BaseDeviceApp {
	

	private static final Logger _Logger = Logger.getLogger(SystemPerformanceApp.class.getSimpleName());

	public static Logger getLogger() {
		return _Logger;
	}
	
	private SystemPerformanceAdaptor _sysPerfAdaptor;

	public SystemPerformanceApp() {
		super();
	}

	public SystemPerformanceApp(String appName) {
		super(appName);
	}

	public SystemPerformanceApp(String simpleName, String[] args) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void start() throws DeviceApplicationException {
		// TODO Auto-generated method stub
		SystemPerformanceAdaptor adaptor = new SystemPerformanceAdaptor(5000);
		adaptor.run();
	}

	@Override
	protected void stop() throws DeviceApplicationException {
		// TODO Auto-generated method stub
		
	}
}
