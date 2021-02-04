package androidapps;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class LocatingElementViaUIAutomatorViewer 
{
	public static void main(String[] args) throws Exception
	{
		//Enter values for mathematical operation
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter input 1");
		int i1=sc.nextInt();
		System.out.println("Enter input 2");
		int i2=sc.nextInt();
		sc.close();
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Create url of appium server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","5.1.1");
		dc.setCapability("appPackage","com.android.calculator2");
		dc.setCapability("appActivity","com.android.calculator2.Calculator");
		//Create driver object
		AndroidDriver driver;
		while(2>1)
		{
			try
			{
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
			}
		}
		
		//App Automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='5']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+i1+"']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='plus']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+i2+"']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='equals']"))).click();
			int result=Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.calculator2:id/formula']"))).getText());
			if(result==(i1+i2))
			{
				System.out.println("Addition test passed");
			}
			else
			{
				System.out.println("Addition test failed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		//Close app
		driver.closeApp();
		
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
