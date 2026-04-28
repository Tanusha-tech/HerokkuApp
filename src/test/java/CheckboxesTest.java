import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {

    @Test
    public void checkCheckboxes() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        boolean isCheck = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertFalse(isCheck);
        boolean isCheck2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertTrue(isCheck2);

        driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
        isCheck = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertTrue(isCheck);
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        isCheck2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertFalse(isCheck2);

        softAssert.assertAll();
        driver.quit();
    }
}
