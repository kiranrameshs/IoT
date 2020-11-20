/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.gda.connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;

import programmingtheiot.data.DataUtil;
import programmingtheiot.gda.connection.handlers.GenericCoapResponseHandler;

/**
 * Shell representation of class for student implementation.
 *
 */
public class CoapClientConnector implements IRequestResponseClient
{
	// static
	
	private static final Logger _Logger =
		Logger.getLogger(CoapClientConnector.class.getName());
	
	// params
	private String     protocol;
	private String     host;
	private int        port;
	private String     serverAddr;
	private CoapClient clientConn;
	private IDataMessageListener dataMsgListener;
	
	// constructors
	
	/**
	 * Default.
	 * 
	 * All config data will be loaded from the config file.
	 */
	public CoapClientConnector()
	{
		ConfigUtil config = ConfigUtil.getInstance();
		this.host = config.getProperty(ConfigConst.COAP_GATEWAY_SERVICE, ConfigConst.HOST_KEY, ConfigConst.DEFAULT_HOST);

		if (config.getBoolean(ConfigConst.COAP_GATEWAY_SERVICE, ConfigConst.ENABLE_CRYPT_KEY)) {
			this.protocol = ConfigConst.DEFAULT_COAP_SECURE_PROTOCOL;
			this.port     = config.getInteger(ConfigConst.COAP_GATEWAY_SERVICE, ConfigConst.SECURE_PORT_KEY, ConfigConst.DEFAULT_COAP_SECURE_PORT);
		} else {
			this.protocol = ConfigConst.DEFAULT_COAP_PROTOCOL;
			this.port     = config.getInteger(ConfigConst.COAP_GATEWAY_SERVICE, ConfigConst.PORT_KEY, ConfigConst.DEFAULT_COAP_PORT);
		}
		
		// NOTE: URL does not have a protocol handler for "coap",
		// so we need to construct the URL manually
		this.serverAddr = this.protocol + "://" + this.host + ":" + this.port;

		initClient();

		_Logger.info("Using URL for server conn: " + this.serverAddr);
			
	}
		
	/**
	 * Constructor.
	 * 
	 * @param host
	 * @param isSecure
	 * @param enableConfirmedMsgs
	 */
	public CoapClientConnector(String host, boolean isSecure, boolean enableConfirmedMsgs)
	{
	}
	
	
	// public methods
	
	@Override
	public boolean sendDiscoveryRequest(int timeout)
	{
		this.clientConn.setURI("/.well-known/core");

		// TODO: implement your own Discovery-specific response handler if you'd like, using the parsing logic from Option 2
		GenericCoapResponseHandler responseHandler = new GenericCoapResponseHandler(this.dataMsgListener);
		this.clientConn.get(responseHandler);
		return true;
	}

	@Override
	public boolean sendDeleteRequest(ResourceNameEnum resource, boolean enableCON, int timeout)
	{
		CoapResponse response = null;

		if (enableCON) {
			this.clientConn.useCONs();
		} else {
			this.clientConn.useNONs();
		}

		this.clientConn.setURI(this.serverAddr + "/" + resource.getResourceName());

		// TODO: This is NOT a performance-savvy way to use a response handler, as it will require
		// creating a new response handler with every call to this method. This is AN EXAMPLE ONLY.
		// A better solution would involve creation of a resource and / or request type-specific
		// response handler at construction time and storing it in a class-scoped variable for
		// re-use in this call.
		CoapHandler responseHandler = new GenericCoapResponseHandler(this.dataMsgListener);
		this.clientConn.delete(responseHandler);

		// TODO: you may want to implement a unique, DELETE and resource-specific CoapHandler modeled after GenericCoapResponseHandler.

		return true;
	}

	@Override
	public boolean sendGetRequest(ResourceNameEnum resource, boolean enableCON, int timeout)
	{
		
		CoapResponse response = null;

		if (enableCON) {
			this.clientConn.useCONs();
		} else {
			this.clientConn.useNONs();
		}

		this.clientConn.setURI(this.serverAddr + "/" + resource.getResourceName());

		// TODO: This is NOT a performance-savvy way to use a response handler, as it will require
		// creating a new response handler with every call to this method. This is AN EXAMPLE ONLY.
		// A better solution would involve creation of a resource and / or request type-specific
		// response handler at construction time and storing it in a class-scoped variable for
		// re-use in this call.
		CoapHandler responseHandler = new GenericCoapResponseHandler(this.dataMsgListener);
		this.clientConn.get(responseHandler);

		// TODO: you may want to implement a unique, GET and resource-specific CoapHandler modeled after GenericCoapResponseHandler.

		return true;
	}

	@Override
	public boolean sendPostRequest(ResourceNameEnum resource, boolean enableCON, String payload, int timeout)
	{
		CoapResponse response = null;

		if (enableCON) {
			this.clientConn.useCONs();
		} else {
			this.clientConn.useNONs();
		}

		this.clientConn.setURI(this.serverAddr + "/" + resource.getResourceName());

		// TODO: This is NOT a performance-savvy way to use a response handler, as it will require
		// creating a new response handler with every call to this method. This is AN EXAMPLE ONLY.
		// A better solution would involve creation of a resource and / or request type-specific
		// response handler at construction time and storing it in a class-scoped variable for
		// re-use in this call.
		CoapHandler responseHandler = new GenericCoapResponseHandler(this.dataMsgListener);
		this.clientConn.post(responseHandler, payload, MediaTypeRegistry.TEXT_PLAIN);

		// TODO: you may want to implement a unique, POST and resource-specific CoapHandler modeled after GenericCoapResponseHandler.

		return true;
	}

	@Override
	public boolean sendPutRequest(ResourceNameEnum resource, boolean enableCON, String payload, int timeout)
	{
		CoapResponse response = null;

		if (enableCON) {
			this.clientConn.useCONs();
		} else {
			this.clientConn.useNONs();
		}

		this.clientConn.setURI(this.serverAddr + "/" + resource.getResourceName());

		// TODO: This is NOT a performance-savvy way to use a response handler, as it will require
		// creating a new response handler with every call to this method. This is AN EXAMPLE ONLY.
		// A better solution would involve creation of a resource and / or request type-specific
		// response handler at construction time and storing it in a class-scoped variable for
		// re-use in this call.
		CoapHandler responseHandler = new GenericCoapResponseHandler(this.dataMsgListener);
		this.clientConn.put(responseHandler, payload, MediaTypeRegistry.TEXT_PLAIN);

		// TODO: you may want to implement a unique, PUT and resource-specific CoapHandler modeled after GenericCoapResponseHandler.

		return true;
	}

	@Override
	public boolean setDataMessageListener(IDataMessageListener listener)
	{
		this.dataMsgListener = listener;
		return true;
	}

	@Override
	public boolean startObserver(ResourceNameEnum resource, int ttl)
	{
		return false;
	}

	@Override
	public boolean stopObserver(int timeout)
	{
		return false;
	}

	
	// private methods
	private void initClient() {
		try {
			this.clientConn = new CoapClient(this.serverAddr);
			
			_Logger.info("Created client connection to server / resource: " + this.serverAddr);
		} catch (Exception e) {
			_Logger.log(Level.SEVERE, "Failed to connect to broker: " + (this.clientConn != null ? this.clientConn.getURI() : this.serverAddr), e);
		}
	}

	
}