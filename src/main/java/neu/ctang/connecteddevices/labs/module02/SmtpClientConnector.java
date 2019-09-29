package neu.ctang.connecteddevices.labs.module02;

import com.labbenchstudios.edu.connecteddevices.common.*;
import java.util.*;
import java.util.logging.Logger;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpClientConnector {
	private static final Logger _Logger = Logger.getLogger(SmtpClientConnector.class.getSimpleName());
	public boolean _isConnected = false;
	Session _smtpSession;
	Transport t;	
	
	public void connect() throws MessagingException {
		if (!_isConnected) {
			_Logger.info("Initializing SMTP gateway...");
			String configFile = "config/ConnectedDevicesConfig.props";
			Properties props = new Properties();
			ConfigUtil conUtil = ConfigUtil.getInstance();
			conUtil.loadConfig(configFile);
			String portStr = ConfigUtil.getInstance().getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.PORT_KEY);
			

			props.put(ConfigConst.SMTP_PROP_HOST_KEY, ConfigConst.HOST_KEY);
			
			props.put(ConfigConst.SMTP_PROP_PORT_KEY, portStr);
			 
			props.put(ConfigConst.SMTP_PROP_AUTH_KEY, ConfigConst.ENABLE_AUTH_KEY);
			
			props.put(ConfigConst.SMTP_PROP_ENABLE_TLS_KEY, ConfigConst.ENABLE_CRYPT_KEY);
			_Logger.info(props.toString());
			
			_smtpSession = Session.getDefaultInstance(props);
			try {
				t = _smtpSession.getTransport("smtps");
	//			t.connect("smtp.gmail.com", "neuchuanyangtang@gmail.com", "uyhupvbsxdgxriin");
				String host = ConfigUtil.getInstance().getProperty(
		                  ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.HOST_KEY);
				
				String fromAddrStr =
				           ConfigUtil.getInstance().getProperty(
				                  ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.FROM_ADDRESS_KEY);
				String authToken =
						ConfigUtil.getInstance().getProperty(
								ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.USER_AUTH_TOKEN_KEY);
	//			System.out.println(host+" "+fromAddrStr+" "+ authToken);
				t.connect(host,fromAddrStr,authToken);
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_isConnected = true;
		} else {
			_Logger.info("SMTP gateway connection already initialized.");
		}
	}

	public boolean publishMessage(ServiceResourceInfo resource, int qosLevel, String payload) throws MessagingException {
		if (!_isConnected) {
			connect();
		}
		boolean success = false;
		String fromAddrStr =
		           ConfigUtil.getInstance().getProperty(
		                  ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.FROM_ADDRESS_KEY); 
		try {
			Message smtpMsg = new MimeMessage(_smtpSession);
		//	InternetAddress fromAddr = new InternetAddress("neuchuanyangtang@gmail.com");
			InternetAddress fromAddr = new InternetAddress(fromAddrStr);
			InternetAddress[] toAddr = InternetAddress.parse(
                    ConfigUtil.getInstance().getProperty(
                            ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.TO_ADDRESS_KEY));

	//		String toAddr = "kakatcy@gmail.com";
			
			smtpMsg.setFrom(fromAddr);
		//	smtpMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
			smtpMsg.setRecipients(Message.RecipientType.TO, toAddr);
			smtpMsg.setSubject(resource.getTopic());
			smtpMsg.setText(new String(payload));
			t.sendMessage(smtpMsg, smtpMsg.getAllRecipients());
			System.out.println("send email successfully");
			t.close();
			success = true;
		} catch (Exception e) {
		}
		return success;
	}
}
