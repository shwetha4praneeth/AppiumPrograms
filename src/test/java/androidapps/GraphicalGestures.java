package androidapps;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class GraphicalGestures 
{
	public static void main(String[] args) throws Exception
	{
		//Install "apisdemo" app into device
		//Pathofsdk\system-images\android-25\google_apis\x86\data\app\ApiDemos\apidemos.apk
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4724\"");
		URL u=new URL("http://127.0.0.1:4724/wd/hub");
		//Define desired capabilities related to device & app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","fd51965a");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.touchboarder.android.api.demos");
		dc.setCapability("appActivity","com.example.android.apis.graphics.FingerPaint");
		//Launch app in device through appium server
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
		//App automation
		try
		{
			Thread.sleep(5000);
			int[] xs = {200};
			int[] ys = {300};
			for(int i=0;i<1;i++)
			{
				Point head=new Point(xs[i],ys[i]);
				Point leftEye=head.moveBy(-50,-50);
				Point rightEye=head.moveBy(50,-50);
				Point mouth=head.moveBy(0,50);
				drawCircle(driver,head,150,30);
				//drawCircle(driver,leftEye,20,20);
				//drawCircle(driver,rightEye,20,20);
				//drawCircle(driver,mouth,40,20);				
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//Close App
		driver.closeApp();
		//Stop appium server
		//Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		//Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
	public static void drawCircle(AndroidDriver driver, Point origin, double radius, int steps)
	{
		Point firstPoint=new Point((int) (origin.x+radius),origin.y);
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence circle=new Sequence(finger,1);
		circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),firstPoint.x,firstPoint.y));
		circle.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		for(int i=1; i<=steps; i++)
		{
			double theta=2*Math.PI*((double)i/steps);
			int x=(int)Math.floor(Math.cos(theta)*radius);
			int y=(int)Math.floor(Math.sin(theta)*radius);
			Point point=new Point(origin.x + x, origin.y + y);
			circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),point.x,point.y));		
		}
		circle.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(circle));
	}
}
