package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Objects;

/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
@Test
public class testcase02 {
    public static void testcase02(){
        int scc = 0;
        StringBuffer stringBuffer = new StringBuffer();
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on �MOBILE� menu
            driver.findElement(By.linkText("MOBILE")).click();
            //debug purpose only
            Thread.sleep(2000);

            //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            String XPerialPrice = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();
            System.out.println(XPerialPrice);

            //4. Click on Sony Xperia mobile
            driver.findElement(By.id("product-collection-image-1")).click();

            //5. Read the Sony Xperia mobile from detail page.
            String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
            //debug purpose only
            Thread.sleep(2000);

            //6. Compare Product value in list and details page should be equal ($100).
            AssertJUnit.assertEquals(XPerialPrice, detailPrice);
            if(Objects.equals(detailPrice, XPerialPrice)) {
                System.out.println("The price is " + detailPrice);
            }
            //debug purpose only
            Thread.sleep(2000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        //Quit browser session
        driver.quit();
    }


}
