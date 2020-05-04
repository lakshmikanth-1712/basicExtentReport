package test.lakshmi;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class testcase1 extends baseClass{
	
	@Test
	public void testCase1() throws InterruptedException {
		
		test = extent.createTest("MyFirstTest", "Sample description");
		
		test.log(Status.INFO, "Test case Started ");
		
		System.out.println("entered test..");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.Button[@text='Skip sign in']").click();
		test.log(Status.PASS, "clicked skip sign in button ");
		
		test.log(Status.INFO, "Test case Completed ");

	}


}
