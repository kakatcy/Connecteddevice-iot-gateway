package neu.ctang.connecteddevices.common;

import java.sql.Timestamp;

public class ActuatorData {
	public static final int COMMAND_OFF = 0;
	public static final int COMMAND_ON = 1;
	public static final int COMMAND_SET = 2;
	public static final int COMMAND_RESET = 3;

	public static final int STATUS_IDLE = 0;
	public static final int STATUS_ACTIVE = 1;

	public static final int ERROR_OK = 0;
	public static final int ERROR_COMMAND_FAILED = 1;
	public static final int ERROR_NON_RESPONSIBLE = -1;

	private Timestamp timeStamp = null;
	private String name = null;
	private boolean hasError = false;
	private int command = 0;
	private int errCode = 0;
	private int statusCode = 0;
	private int stateData = 0;
	private float val = 0.0f;

	public ActuatorData() {
		// TODO Auto-generated constructor stub
		updateTimeStamp();
	}

	public void updataData(ActuatorData data) {
		this.name = data.name;
		this.hasError = data.hasError;
		this.command = data.command;
		this.errCode = data.errCode;
		this.statusCode = data.statusCode;
		this.stateData = data.stateData;
		this.val = data.val;

	}

	private void updateTimeStamp() {
		// TODO Auto-generated method stub
		this.timeStamp = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStateData() {
		return stateData;
	}

	public void setStateData(int stateData) {
		this.stateData = stateData;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}

}
