package neu.ctang.connecteddevices.common;

import java.io.Serializable;
import java.sql.Timestamp;
import com.labbenchstudios.edu.connecteddevices.common.*;

//public class SensorData extends BaseIotData implements Serializable {
public class SensorData extends BaseDeviceApp implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp timestamp = null;
	private float curValue =0.0f;
	private float avgValue =0.0f;
	private float minValue =0.0f;
	private float maxValue =0.0f;
	private float totValue =0.0f;
	private int sampleCount =0;

	public SensorData() {
		super();
	}

	public void addValue(float val) {
		updateTimeStamp();
		
		++this.sampleCount;
		
		this.curValue = val;
		this.totValue += val;
		
		if (this.sampleCount ==1 || this.curValue < this.minValue) {
			this.minValue = this.curValue;
		}
		if (this.curValue > this.maxValue) {
			this.maxValue = this.curValue;
		}
		if (this.totValue != 0 && this.sampleCount > 0) {
			this.avgValue = this.totValue / this.sampleCount;
		}
	}

	private void updateTimeStamp() {
		// TODO Auto-generated method stub
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

//	public SensorData getSensorData() {
//		SensorData sensordata = new SensorData();
//		sensordata.curValue = curValue;
//		sensordata.avgValue = avgValue;
//		sensordata.minValue = minValue;
//		sensordata.maxValue = maxValue;
//		sensordata.totValue = totValue;
//		sensordata.sampleCount = sampleCount;
//		return sensordata;
//		
//	}
	
	@Override
	protected void start() throws DeviceApplicationException {
		// TODO Auto-generated method stub
		
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public float getCurValue() {
		return curValue;
	}

	public void setCurValue(float curValue) {
		this.curValue = curValue;
	}

	public float getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(float avgValue) {
		this.avgValue = avgValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getTotValue() {
		return totValue;
	}

	public void setTotValue(float totValue) {
		this.totValue = totValue;
	}

	public int getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(int sampleCount) {
		this.sampleCount = sampleCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected void stop() throws DeviceApplicationException {
		// TODO Auto-generated method stub
		
	}

	public String getString() {
		// TODO Auto-generated method stub
		String result = "Temperature:\n"+
							"\tTime:"+this.timestamp + "\n"+
							"\tCurrent:"+this.curValue + "\n"+
							"\tAverage:"+this.avgValue + "\n"+
							"\tSample:"+this.sampleCount + "\n"+
							"\tMin:"+this.minValue + "\n"+
							"\tMax:"+this.maxValue;
		return result;
	}

}
