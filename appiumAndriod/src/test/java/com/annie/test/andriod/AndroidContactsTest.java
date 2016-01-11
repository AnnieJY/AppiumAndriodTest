package com.annie.test.andriod;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;

public class AndroidContactsTest {

	// private WebDriver driver;
	@SuppressWarnings("rawtypes")
	private AndroidDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps/ContactManager");
		File app = new File(appDir, "ContactManager.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 方式1：
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "emulator-5554"); //sdk-platformpath #adb devices
		//capabilities.setCapability("deviceName", "005544454f21b8bb");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("app", app.getAbsolutePath());

		// 方式2：

		/*
		 * capabilities.setCapability("device","Android");
		 * capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 * capabilities.setCapability(CapabilityType.VERSION, "4.4");
		 * capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		 */

		// app-package 通过uiautomator可以获取
		capabilities.setCapability("app-package",
				"com.example.android.contactmanager");
		// app-activity 通过反编译apk可能得到
		capabilities.setCapability("app-activity", "ContactAdder");
		// 固定URL端口 4723 AppiumDriver类
		// driver = new AppiumDriver (new URL("http://127.0.0.1:4723/wd/hub"),
		// capabilities);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void addContact() {
		WebElement el = driver.findElement(By.name("Add Contact"));
		el.click();
		// 方式一： List<WebElement> textFieldsList =
		// driver.findElements(By.className("android.widget.EditText"));
		// 方式二：id为UiAutomator的resource-id List<WebElement> textFieldsList =
		WebElement c_Name= driver.findElement(By.id("com.example.android.contactmanager:id/contactNameEditText"));
		WebElement c_Phone= driver.findElement(By.id("com.example.android.contactmanager:id/contactPhoneEditText"));
		WebElement c_Email= driver.findElement(By.id("com.example.android.contactmanager:id/contactEmailEditText"));
		 c_Name.sendKeys("Annie.Wang");
		 c_Phone.sendKeys("18666073095");
		 c_Email.sendKeys("annie.wang@easternphoenix.com");
		 
		// 方式三：XPath定位 选取所有拥有名为 android.widget.EditText节点下 text属性为Add note的所有节点
		// （contains是匹配功能）
		/*List<WebElement> textFieldsList = driver
				.findElements(By
						.xpath("//android.widget.EditText[contains(@text,'Add note')]"));

		textFieldsList.get(0).sendKeys("Some Name");
		textFieldsList.get(2).sendKeys("Some@example.com");*/
		 WebElement c_Save= driver.findElement(By.id("com.example.android.contactmanager:id/contactSaveButton"));
		 c_Save.click();
		 
	/*	 findElementByAccessibilityId()
		 findElementsByAccessibilityId()
		 findElementByIosUIAutomation()
		 findElementsByIosUIAutomation()
		 findElementByAndroidUIAutomator()
		 findElementsByAndroidUIAutomator()  */
		 
	}

}
