package androidapps;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PhoneCallInAVD 
{
	public static void main(String[] args) throws Exception
	{
		//Get phone number from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a phone number to dial");
		String phonenum=sc.nextLine();
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
		dc.setCapability("appPackage","com.android.dialer");
		dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.dialer:id/floating_action_button_container']")));
			driver.findElement(By.xpath("//*[@resource-id='com.android.dialer:id/floating_action_button_container']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='2']")));
			for(int i=0;i<phonenum.length();i++)
			{
				char c=phonenum.charAt(i);
				driver.findElement(By.xpath("//*[@content-desc='"+c+"']")).click();
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@content-desc='dial']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='00:15']")));
			driver.findElement(By.xpath("//*[@content-desc='End']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='dial pad']")));
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
