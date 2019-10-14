package neu.ctang.connecteddevices.labs.module05;

import java.util.*;
import java.util.logging.*;

import com.labbenchstudios.edu.connecteddevices.common.*;
import neu.ctang.connecteddevices.common.*;
import neu.ctang.connecteddevices.labs.module02.SmtpClientConnector;

public class TempSensorEmulatorTask implements Runnable {
	private static final Logger _Logger = Logger.getLogger(TempSensorEmulatorTask.class.getSimpleName());
	private static final int threshold = 5;
	private static SensorData sensorData = new SensorData();

	// send email
	public void sendNotification() {
		try {
			// SensorData sensorData = super.getSensorData();
			// SensorData sensorData = new SensorData();

			// 4 parameter: name, description, topic, id
			ServiceResourceInfo resourceInfo = new ServiceResourceInfo(sensorData.getAppName(),
					"Temperature above threshold", sensorData.getAppName() + "Threshold Crossing",
					String.valueOf(sensorData.getExitCode()));

			SmtpClientConnector _smtpConnector = new SmtpClientConnector();
			// my publishMessage() API takes byte[] as the payload for flexibility
			_smtpConnector.publishMessage(resourceInfo, 1,
			//		 sensorData.getBytes());
					sensorData.getString());
		} catch (Exception e) {
			_Logger.log(Level.WARNING, "Failed to send SMTP message.", e);
		}
	}

	public void checkData(SensorData sensorData) {
		float cur = sensorData.getCurValue();
		float ave = sensorData.getAvgValue();
		if (cur >= ave + threshold || cur <= ave - threshold) {
			System.out.println("send notification");
			sendNotification();
		}
	}

	public void sentEmail(String json) {
		
	}
	
	public void writeFile(String json) {
		
	}
	
	public void readData(String dir) {
		
	}
	
	// generate temperature value
	public void run() {
		// TODO Auto-generated method stub
		float temperture = 0;
		for (int i = 0; i < 10; i++) {
			try {
				temperture =(float) (Math.random() * 30);
				sensorData.addValue(temperture);
	//			System.out.println(sensorData.getCurValue() + " " + sensorData.getAvgValue() + " "
	//					+ sensorData.getMinValue() + " " + sensorData.getMaxValue() + " " + sensorData.getTotValue()
	//					+ " " + sensorData.getSampleCount());

				checkData(sensorData);
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
