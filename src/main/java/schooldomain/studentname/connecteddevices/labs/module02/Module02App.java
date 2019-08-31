/**
 * 
 */
package schooldomain.studentname.connecteddevices.labs.module02;

import java.util.logging.Logger;

import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

/**
 *
 */
public class Module02App extends BaseDeviceApp
{
	// static
	
	private static final Logger _Logger =
		Logger.getLogger(Module02App.class.getSimpleName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Module02App app = new Module02App(Module02App.class.getSimpleName(), args);
		app.startApp();
	}
	
	// private var's
	
	
	// constructors
	
	/**
	 * Default.
	 * 
	 */
	public Module02App()
	{
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 */
	public Module02App(String appName)
	{
		super(appName);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 * @param args
	 */
	public Module02App(String appName, String[] args)
	{
		super(appName, args);
	}
	
	// protected methods
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#start()
	 */
	@Override
	protected void start() throws DeviceApplicationException
	{
		_Logger.info("Hello - module02 here!");
		
	}
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#stop()
	 */
	@Override
	protected void stop() throws DeviceApplicationException
	{
		_Logger.info("Stopping module02 app...");
	}
	
}
