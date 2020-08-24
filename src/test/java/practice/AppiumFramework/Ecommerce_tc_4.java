package practice.AppiumFramework;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;


public class Ecommerce_tc_4 extends base {
	
	@Test
	public void TotalValidation() throws IOException, InterruptedException {
		
		service = startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		
		FormPage fp = new FormPage(driver);
		CheckoutPage cp = new CheckoutPage(driver);
		Utilities u = new Utilities(driver);
		
		//fp.nameField.sendKeys("Hello");
		fp.getNameField().sendKeys("Hello");
		driver.hideKeyboard();
		fp.femaleOption.click();
		fp.dropDown.click();
		u.scrollToText("Argentina");
		fp.argentina.click();
		fp.letsShopButton.click();
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(4000);

		int count = cp.productList.size();
		double sum = 0;

		for(int i = 0;i < count; i++)
		{
			String amount1 = cp.productList.get(i).getText();
			double amount = getAmount(amount1);
			sum += amount;
		}
		
		double total = getAmount(cp.totalAmount.getText());
	
		System.out.println("Sum of products: " + sum);
		System.out.println("Total sum: " + total);
		
		Assert.assertEquals(sum, total);
		
		service.stop();

	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		// taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}

	public static double getAmount(String value) {
		
		value = value.substring(1);
		double amount = Double.parseDouble(value);
		return amount;
	}
}
