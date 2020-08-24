package practice.AppiumFramework;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.PreferencePage;

public class ApiDemoTest extends base {

	@Test(dataProvider="InputData", dataProviderClass=TestData.class)
	public  void apiDemoTest(String input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("ApiDemo");
		
		//xpath syntax - tagName[@attribute='value']
		
		HomePage hp = new HomePage(driver);
		PreferencePage pp = new PreferencePage(driver);
		
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		hp.preferences.click();
		
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		pp.dependencies.click();
		
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		
		//driver.findElementsByClassName("android.widget.Button").get(1).click();
		pp.ds.get(1).click();
		
		service.stop();
				
	}

}
