/**
 * 
 */
package schooldomain.studentname.connecteddevices.labs.module01;

import java.util.logging.Logger;

import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

/**
 *
 */
public class Module01App extends BaseDeviceApp
{
	// static
	
	private static final Logger _Logger =
		Logger.getLogger(Module01App.class.getSimpleName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Module01App app = new Module01App(Module01App.class.getSimpleName(), args);
		app.startApp();
	}
	
	// private var's
	
	
	// constructors
	
	/**
	 * Default.
	 * 
	 */
	public Module01App()
	{
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 */
	public Module01App(String appName)
	{
		super(appName);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 * @param args
	 */
	public Module01App(String appName, String[] args)
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
		_Logger.info("Hello - module01 here!");
		
	}
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#stop()
	 */
	@Override
	protected void stop() throws DeviceApplicationException
	{
		_Logger.info("Stopping module01 app...");
	}
	
}
