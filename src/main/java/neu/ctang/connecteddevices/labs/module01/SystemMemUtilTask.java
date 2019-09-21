package neu.ctang.connecteddevices.labs.module01;

import java.lang.management.ManagementFactory;
import java.lang.management.*;
//import com.sun.management.OperatingSystemMXBean;

public class SystemMemUtilTask implements Runnable{
	private float MemRate;
	
	public SystemMemUtilTask() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemMemUtilTask(String string, long _pollCycle) {
		// TODO Auto-generated constructor stub
		setMemRate(getDataFromSensor());
	}

	public float getMemRate() {
		return MemRate;
	}

	public void setMemRate(float memRate) {
		MemRate = memRate;
	}

	public float getDataFromSensor() {
		float memrate = 0;
		
		MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
		MemoryUsage memusage = mxb.getHeapMemoryUsage();
		
		double totalMem = memusage.getMax();
		double usedMem = memusage.getUsed();
		
		memrate =(float) (usedMem/totalMem*100);
		
		return memrate;
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
