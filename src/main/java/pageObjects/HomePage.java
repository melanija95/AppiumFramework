package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// all the objects belonging to one page will be defined in one java class

public class HomePage {
	
	public HomePage(AppiumDriver driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//@iOSFindBy
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement preferences;
	
}
