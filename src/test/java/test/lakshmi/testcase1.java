package test.lakshmi;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;



public class testcase1 extends baseClass{
	
	static WebDriver driver;
    private static ExtentReports extent;
    static URI report;
	
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
	
	public static void createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./test-output/ExtentReports/"  + fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Marriott Automation Execution");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Marriott Automation Execution");

 

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    
    
    
    @BeforeMethod
    public static void hello() throws Exception {
        createInstance("Reports.html");
        
        WebDriverManager.chromedriver().version("86").setup();
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com");
        driver.manage().window().maximize();
        //getElement("CreateNewAccountButton");
        //Desktop.getDesktop().browse(report);
    }
        
    @Test()
    public void launchExtent()
    {
        ExtentTest test = extent.createTest("sdfghjkl");
        System.out.println("launched...");
        
    }
    
    
    @AfterMethod
    public void quit() throws MalformedURLException, URISyntaxException
    {
        
    
        driver.quit();
        extent.flush();
        
        try {
            String reportURL = new File("C:/Users/Riyazulla/eclipse-workspace/reader/test-output/ExtentReports").getAbsolutePath();
            report = new URL("file:///" + reportURL + "/Reports.html").toURI();
            Desktop.getDesktop().browse(report);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
