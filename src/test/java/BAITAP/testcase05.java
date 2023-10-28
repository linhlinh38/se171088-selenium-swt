package BAITAP;
/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

*/

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import pom.RegisterPage;

import java.io.File;
import java.io.IOException;

@Test
public class testcase05 {
    public static void testcase05(){
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        String dirPath = "screenshots";
        File dir = new File(dirPath);
        //check dir exists
        if (!dir.exists()) {
            dir.mkdir();
        }
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        try{
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            driver.findElement(By.cssSelector(".skip-link.skip-account")).click();
            driver.findElement(By.cssSelector("div[id='header-account'] a[title='My Account']")).click();
            //debug purpose only
            Thread.sleep(2000);

            //3. Click Create an Account link and fill New User information excluding the registered Email ID.
            driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.inputFirstname("Nguyen");
            registerPage.inputMiddlename("Gia");
            registerPage.inputLastname("Lin");
            registerPage.inputEmail("05linhnguyen@gmail.com");
            registerPage.inputPassword("123456");
            registerPage.inputConfirmPassword("123456");
            registerPage.clickOnRegisterBtn();
            //debug purpose only
            Thread.sleep(2000);

            //5. Verify Registration is done. Expected account registration done.
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);
            //debug purpose only
            Thread.sleep(2000);

            //6. Go to TV menu
            driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
            //debug purpose only
            Thread.sleep(2000);

            //7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
            //debug purpose only
            Thread.sleep(2000);

            //8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            //debug purpose only
            Thread.sleep(2000);

            //9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement email = driver.findElement(By.xpath("//textarea[@id='email_address']"));
            email.click();
            email.sendKeys("lili123@gmail.com");

            WebElement msg =  driver.findElement(By.xpath("//textarea[@id='message']"));
            msg.click();
            msg.sendKeys("so good!!");

            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();

            //debug purpose only
            Thread.sleep(2000);

            //10.Check wishlist is shared. Expected wishlist shared successfully.
            TakesScreenshot takesScreenshot2 = (TakesScreenshot) driver;

            File screenshot2 = takesScreenshot2.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f2 = new File(dirPath + "/" + screenshot2.getName());
            FileHandler.copy(screenshot2, f2);

            //debug purpose only
            Thread.sleep(2000);


        }catch (Exception e) {
            e.printStackTrace();
        }
        //Quit browser session
        driver.quit();
    }
}
