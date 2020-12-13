package programmingtheiot.part03.integration.connection;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.DataUtil;
import programmingtheiot.data.SensorData;
import programmingtheiot.gda.connection.CoapClientConnector;

public class CoapClientPerformanceTest {

	// NOTE: We'll use only 10,000 requests for CoAP
		public static final int DEFAULT_TIMEOUT = 5;
		public static final int MAX_TEST_RUNS = 10000;
		private static final Logger _Logger =
				Logger.getLogger(CoapServerGatewayTest.class.getName());
		
		private CoapClientConnector coapClient = null;

		@Before
		public void setUp() throws Exception
		{
			this.coapClient = new CoapClientConnector();
		}
		
		@Test
		public void testPostRequestCon()
		{
			execTestPost(MAX_TEST_RUNS, true);
		}
		
		@Test
		public void testPostRequestNon()
		{
			execTestPost(MAX_TEST_RUNS, false);
		}
		
		private void execTestPost(int maxTestRuns, boolean enableCON)
		{
			SensorData sd = new SensorData();
			String payload = DataUtil.getInstance().sensorDataToJson(sd);
			
			long startMillis = System.currentTimeMillis();
			
			for (int seqNo = 0; seqNo < maxTestRuns; seqNo++) {
				this.coapClient.sendPostRequest(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, enableCON, payload, DEFAULT_TIMEOUT);
			}
			
			long endMillis = System.currentTimeMillis();
			long elapsedMillis = endMillis - startMillis;
			
			_Logger.info("POST message - useCON " + enableCON + " [" + maxTestRuns + "]: " + elapsedMillis + " ms");
		}

}
