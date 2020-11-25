package test.lakshmi;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import ExtentReport.extentReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class baseClass extends extentReport {

	public static AppiumDriver<AndroidElement> driver;
	public static DesiredCapabilities caps = new DesiredCapabilities();

	@BeforeTest
	public static void initailize() throws MalformedURLException {

		caps.setCapability("deviceName", "vivo18");
		caps.setCapability("platformName", "Android");
		caps.setCapability(CapabilityType.APPLICATION_NAME, "amazon");
		caps.setCapability(CapabilityType.VERSION, "9");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		caps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		caps.setCapability("appActivity", "com.amazon.mShop.android.home.HomeActivity");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	}

	@AfterMethod
	public void checkResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.fail(result.getThrowable());
			// test.fail("Screenshot below: " +
			// test.addScreenCaptureFromPath(captureScreen(result.getMethod().getMethodName())));
			test.fail("Failed Screnshot",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen(result.getName())).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case :" + result.getThrowable() + " is SKIPPED");
			test.skip("Screenshot below: "
					+ test.addScreenCaptureFromPath(captureScreen(result.getMethod().getMethodName())));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case :" + result.getName() + " is PASSED ");
			// test.pass("Screenshot below: " +
			// test.addScreenCaptureFromPath(captureScreen(result.getMethod().getMethodName())));
			test.pass("Passed Screnshot",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen(result.getName())).build());
		}
	}

	public String captureScreen(String methodName) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		// String
		// dest="/Users/luckyshiney/git/TestExtentReport/test/Screenshots"+getcurrentdateandtime()+".png";
		// specify the screenshot path correctly else u will not get SS in report(only
		// absolute path work not relative path)
		String dest = System.getProperty("user.dir") + "/Screenshots/"
				+ d.toString().replace(":", "_").replace(" ", "_") + ":" + methodName + ".png";
		File target = new File(dest);
		FileHandler.copy(src, target);
		return dest;
	}

	@AfterTest
	public void end() {
		extent.flush();
	}

}
