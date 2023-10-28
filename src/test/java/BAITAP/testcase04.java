/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/
package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class testcase04 {
    @Test
    public static void testCase04() {
        String dirPath = "screenshots";
        File dir = new File(dirPath);
        //check dir exists
        if (!dir.exists()) {
            dir.mkdir();
        }

        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        //casting the driver object to the TakesScreenshot interface
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
//        1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

//        2. Click on  MOBILE  menu
            driver.findElement(By.linkText("MOBILE")).click();


//      3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
            driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
            //debug purpose only
            Thread.sleep(2000);

//      4. Click on �COMPARE� button. A popup window opens
            driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //debug purpose only
            Thread.sleep(2000);

            String heading =  driver.findElement(By.xpath("//h1[normalize-space()='Compare Products']")).getText();
            System.out.println("Heading: "+ heading);
            if(!heading.isEmpty()){

                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
                File f = new File(dirPath + "/" + screenshot.getName());
                FileHandler.copy(screenshot, f);

//                5. Verify the error message
                AssertJUnit.assertEquals("COMPARE PRODUCTS", heading);
                driver.close();
            }

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Quit browser session
        driver.quit();
    }
}