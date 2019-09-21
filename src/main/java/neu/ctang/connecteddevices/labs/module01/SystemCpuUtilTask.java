package neu.ctang.connecteddevices.labs.module01;

import java.lang.management.*;

public class SystemCpuUtilTask implements Runnable {
	private float CpuRate; 
	
	public SystemCpuUtilTask() {
		// TODO Auto-generated constructor stub
		
	}
	
	public SystemCpuUtilTask(String string, long _pollCycle) {
		// TODO Auto-generated constructor stub
		setCpuRate(getDataFromSensor());
		
	}
	
	public float getCpuRate() {
		return CpuRate;
	}

	public void setCpuRate(float cpuRate) {
		CpuRate = cpuRate;
	}

	public float getDataFromSensor() {
		float cpurate = 0;
		OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
		double cpu = osMxBean.getSystemLoadAverage();
		cpurate = (float) (cpu);
		
		return cpurate;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
