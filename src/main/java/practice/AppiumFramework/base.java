package practice.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer()
	{
		boolean flag = checkIfServerIsRunnning(4723); 
		if (!flag)
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
	}
	
    public static boolean checkIfServerIsRunnning(int port) 
    {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
    
    public static void startEmulator() throws IOException, InterruptedException
    {
    	Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
    	Thread.sleep(6000);
    }
    
	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String project_path = System.getProperty("user.dir"); // project path
		FileInputStream fis = new FileInputStream(project_path + "\\src\\main\\java\\practice\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		File f = new File("src");
		File fs = new File(f, (String) prop.get(appName));
		
		DesiredCapabilities cap = new  DesiredCapabilities();
		
		//String device = (String) prop.get("device");
		String device = System.getProperty("deviceName");
		if (device.contains("emulator"))
		{
			startEmulator();
		}
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
		cap.setCapability("adbExecTimeout", 50000);
		//cap.setCapability("chromedriverExecutable", "C:\\Users\\krsto\\Downloads\\chromedriver_win32\\chromedriver.exe");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
		
	}

	public static void getScreenshot(String name) throws IOException
	{
		 File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\" + name + ".png"));
	}
	
}
