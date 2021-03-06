package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait                wait;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        System.out.println("Setting up test");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "rp9");
        caps.setCapability("udid", "bc3d16b3");
        // caps.setCapability("deviceName", "Pixel_3a_API_30_x86");
        // caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "net.amp.era");
        caps.setCapability("appActivity", "my.com.astro.radiox.presentation.screens.launch.LaunchActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 15);
    }

    @AfterMethod
    public void teardown(ITestResult res) {
        System.out.println("Tearing down test");
        if (ITestResult.FAILURE == res.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = driver;
                File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
                System.out.println("\t - Failure detected, capturing screenshot");
                String path = ".\\failures\\";
                FileUtils.copyFile(src, new File(path + res.getName() + ".jpg"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        driver.quit();
    }
}