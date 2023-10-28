package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
@Test
public class testcase03 {
    public static void testcase03(){
    //Init web-driver session
    WebDriver driver = driverFactory.getChromeDriver();

    try{
        //1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        //2. Click on �MOBILE� menu
        driver.findElement(By.linkText("MOBILE")).click();
        //debug purpose only
        Thread.sleep(2000);

        //3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
        WebElement addToCart = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]"));
        addToCart.click();

        //4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
        WebElement element = driver.findElement(By.xpath("//input[@title='Qty']"));
        element.click();
        element.sendKeys("1000");
        WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]"));
        updateButton.click();
        //Expected that an error is displayed
        WebElement errorMess =  driver.findElement(By.xpath("//p[@class='item-msg error']"));

        String dirPath = "screenshots";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File f = new File(dirPath + "/" + screenshot.getName());
        FileHandler.copy(screenshot, f);

        //5. Verify the error message
        AssertJUnit.assertEquals("The requested quantity for \"Sony Xperia\" is not available.", errorMess.getText());
        //debug purpose only
        Thread.sleep(2000);



        //6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
        WebElement emptyCartButton = driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]"));
        emptyCartButton.click();

        //A message "SHOPPING CART IS EMPTY" is shown.
        WebElement emptyCartMess = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart is Empty']"));

        //7. Verify cart is empty
        AssertJUnit.assertEquals("SHOPPING CART IS EMPTY", errorMess.getText());
        //debug purpose only
        Thread.sleep(2000);

        TakesScreenshot takesScreenshot2 = (TakesScreenshot) driver;
        File screenshot2 = takesScreenshot2.getScreenshotAs(OutputType.FILE);
        File f2 = new File(dirPath + "/" + screenshot2.getName());
        FileHandler.copy(screenshot2, f2);

    }catch (Exception e) {
        e.printStackTrace();
    }
    //Quit browser session
        driver.quit();
}


}
