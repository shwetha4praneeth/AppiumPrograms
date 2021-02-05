package locators;

public class MSMobileBy5AndroidViewTagAndroidAppEspresso 
{
	public static void main(String[] args) 
	{
		/*
		driver.findElement(MobileSelector.ANDROID_VIEWTAG.toString(),"view-tag attribute value").click();
		driver.findElement(MobileBy.AndroidViewTag("view-tag attribute value")).click();
		driver.findElementByAndroidViewTag("view-tag attribute value").click();
		*/
		
		//view-tag attribute value can be given by Espresso driver only via getPageSoruce()
		//Cannot be provided by Appium desktop server inspector/UiAutomatorViewer
	}
}
