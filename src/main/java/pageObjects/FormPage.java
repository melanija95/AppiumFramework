package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
		
		public FormPage(AppiumDriver driver) 
		{
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		
		@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
		private WebElement nameField;
		
		@AndroidFindBy(xpath="//*[@text='Female']")
		public WebElement femaleOption;
		
		@AndroidFindBy(id="android:id/text1")
		public WebElement dropDown;
		
		@AndroidFindBy(xpath="//*[@text='Argentina']")
		public WebElement argentina;
		
		@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
		public WebElement letsShopButton;
		
		public WebElement getNameField()
		{
			System.out.println("trying to find name field element");
			return nameField;
		}
		
}
