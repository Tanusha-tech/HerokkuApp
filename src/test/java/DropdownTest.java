import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    @Test
    public void checkDropdown() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> actualElements = select.getOptions();

        Assert.assertTrue(actualElements.size() >= 3, "Дропдаун пуст или не хватает значений");

        Assert.assertEquals(select.getOptions().get(0).getText(), "Please select an option", "Некорректное дефолтное значение");
        Assert.assertEquals(select.getOptions().get(1).getText(), "Option 1", "Некорректное значение для первого элемента");
        Assert.assertEquals(select.getOptions().get(2).getText(), "Option 2", "Некорректное значение для второго элемента");

        select.selectByIndex(1);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1", "Ошибка выбора первого элемента");

        select.selectByIndex(2);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2", "Ошибка выбора второго элемента");

        driver.quit();
    }
}
