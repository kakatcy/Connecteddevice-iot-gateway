/**
 * 
 */
package neu.ctang.connecteddevices.labs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import neu.ctang.connecteddevices.labs.module01.SystemCpuUtilTask;
import neu.ctang.connecteddevices.labs.module01.SystemMemUtilTask;

/**
 * Test class for all requisite Module01 functionality.
 * 
 * Instructions:
 * 1) Rename 'testSomething()' method such that 'Something' is specific to your needs; add others as needed, beginning each method with 'test...()'.
 * 2) Add the '@Test' annotation to each new 'test...()' method you add.
 * 3) Import the relevant modules and classes to support your tests.
 * 4) Run this class as unit test app
 * 5) Include a screen shot of the report when you submit your assignment
 */
public class Module01Test
{
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void testCpuRate()
	{
		float cpurate = new SystemCpuUtilTask().getCpuRate();
		assertTrue(cpurate >=0.0 && cpurate<=100.0);
	}
	
	@Test
	public void testMemRate() {
		float memrate = new SystemMemUtilTask().getMemRate();
		assertTrue(memrate>=0.0 && memrate<=100.0);
	}
	
	
	
}
