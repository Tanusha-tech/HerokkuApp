import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {
    @Test
    public void checkInputs() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");

        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/inputs");

        driver.findElement(By.tagName("input")).sendKeys("Текст");
        String text = driver.findElement(By.tagName("input")).getText();
        Assert.assertEquals(text, "");

        driver.findElement(By.tagName("input")).sendKeys("42");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(
                Integer.parseInt(driver.findElement(By.tagName("input")).getAttribute("value")),
                43
        );

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(
                Integer.parseInt(driver.findElement(By.tagName("input")).getAttribute("value")),
                42
        );

        softAssert.assertAll();
        driver.quit();
    }
}
