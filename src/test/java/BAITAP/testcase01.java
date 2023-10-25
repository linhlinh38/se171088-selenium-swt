package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class testcase01 {
    public static void testcase01(){
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Step 2. Verify Title of the page
            By title = By.xpath("//h2[1]");
            WebElement webTitle = driver.findElement(title);

            AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", webTitle.getText());
            //debug purpose only
            Thread.sleep(2000);

            //Step 3. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();
            //debug purpose only
            Thread.sleep(2000);

            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
            //debug purpose only
            Thread.sleep(2000);

            //Step 5. Verify all products are sorted by name
            String dirPath = "screenshots";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);
        }catch (Exception e) {
            e.printStackTrace();
        }

        //Quit browser session
        driver.quit();
    }
}
