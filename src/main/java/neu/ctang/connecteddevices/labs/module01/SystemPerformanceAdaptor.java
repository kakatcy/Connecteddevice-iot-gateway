package neu.ctang.connecteddevices.labs.module01;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.lang.management.*;
import java.util.Date;
import java.text.*;

import com.sun.management.*;
import com.labbenchstudios.edu.connecteddevices.common.DevicePollingManager;

public class SystemPerformanceAdaptor extends Formatter implements Runnable{
	long _pollCycle;
	Logger _Logger = SystemPerformanceApp.getLogger();
	DevicePollingManager _pollManager = new DevicePollingManager(2);;
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");

	public SystemPerformanceAdaptor() {
		
	}
	
	public SystemPerformanceAdaptor(long pollCycle) {
		super();
		
		_Logger.setUseParentHandlers(false);
		SystemPerformanceAdaptor formatter = new SystemPerformanceAdaptor();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
 
        _Logger.addHandler(handler);
		if (pollCycle >= 1L) {
			_pollCycle = pollCycle;
		}
		_pollManager = new DevicePollingManager(2);
	}
	
	public void startPolling() {
        SystemCpuUtilTask cputask = new SystemCpuUtilTask("CPU Utilization", _pollCycle);
		_Logger.info("CPU Utilization="+Float.toString(cputask.getCpuRate()).format("%.1f", cputask.getCpuRate()));
		SystemMemUtilTask memtask = new SystemMemUtilTask("Memory Utilization", _pollCycle);
		_Logger.info("Memory Utilization="+Float.toString(memtask.getMemRate()).format("%.1f", memtask.getMemRate()));
	}

	public void run() {
		// TODO Auto-generated method stub
		int count = 10;
		while(count>0) {	
			try {
				startPolling();
				Thread.sleep(_pollCycle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count--;
		}
	}

	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis())));
        builder.append(":").append(record.getLevel()).append(":");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
	}
}
