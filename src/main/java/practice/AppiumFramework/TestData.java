package practice.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name="InputData")
	public Object[][] getDataForEditBox()
	{
		Object[][] obj = new Object[][]{{"hello"}, {"@:%^$"}};
		return obj;
	}
}
