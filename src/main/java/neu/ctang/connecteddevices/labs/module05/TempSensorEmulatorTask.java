package neu.ctang.connecteddevices.labs.module05;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

import com.google.gson.Gson;
import com.labbenchstudios.edu.connecteddevices.common.*;
import neu.ctang.connecteddevices.common.*;
import neu.ctang.connecteddevices.labs.module02.SmtpClientConnector;

public class TempSensorEmulatorTask implements Runnable {
	private static final Logger _Logger = Logger.getLogger(TempSensorEmulatorTask.class.getSimpleName());
	private static final int threshold = 10000;
	private static SensorData sensorData = new SensorData();
	Thread t;

	public TempSensorEmulatorTask() {
		// TODO Auto-generated constructor stub
		this.t = new Thread(this, "temp sensor thread");
	}

	// send email
	public void sendNotification(String jsonData) {
		try {
			// 4 parameter: name, description, topic, id
			ServiceResourceInfo resourceInfo = new ServiceResourceInfo(sensorData.getAppName(), "Temperature",
					sensorData.getAppName() + "Temperature record", String.valueOf(sensorData.getExitCode()));

			SmtpClientConnector _smtpConnector = new SmtpClientConnector();
			// my publishMessage() API takes byte[] as the payload for flexibility
			_smtpConnector.publishMessage(resourceInfo, 1,
					// sensorData.getBytes());
					jsonData);
		} catch (Exception e) {
			_Logger.log(Level.WARNING, "Failed to send SMTP message.", e);
		}
	}

	// write json data to the json file
	public void writeFile(String json) throws IOException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("tempData.json"));
			bw.write(json);
			bw.flush();
			bw.close();
		}

		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		float temperture = 0;
		try {
			DataUtil datautil = new DataUtil();
			while (true) {
				// generate temperature value
				temperture = (float) (Math.random() * 30);
				sensorData.addValue(temperture);
				_Logger.info("\n-------------\nNew sensor readings:\n" + "sampleCount=" + sensorData.getSampleCount()
						+ ",avgValue=" + sensorData.getAvgValue() + ",minValue=" + sensorData.getMinValue()
						+ ",maxValue=" + sensorData.getMaxValue() + ",curValue" + sensorData.getCurValue()
						+ ",timestamp=" + sensorData.getTimestamp() + ",totValue=" + sensorData.getTotValue());

				// convert to json file from sensordata object
				String gsonData = datautil.toJsonFromSensorData(sensorData);
				// send email
				sendNotification(gsonData);

				// write to the filesystem
				writeFile(gsonData);
				if (Math.abs(sensorData.getCurValue() - sensorData.getAvgValue()) > 2) {
					// System.out.println(gsonData);
					_Logger.info("Current temp exceeds average by >2. Converting data...\nJSON data:\n" + gsonData);
					break;
				}
			}

			// Read json file generated by python program
			String filename = "tempData.json";
			String path = "/Users/cytang/program/connected devices/iot-device/apps/labs/module05/";
			SensorData sensorDataFromPython = datautil.toSensorDataFromJsonFile(filename, path);

			// convert the sensordata object read from file system to json format again
			String gsonDataFromPython = datautil.toJsonFromSensorData(sensorDataFromPython);

			_Logger.info("Data from python\n" + gsonDataFromPython);

			Thread.sleep(threshold);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}