import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationMessageTest {

    @Test
    public void notificationMessage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        WebElement clickHereLink = driver.findElement(By.linkText("Click here"));
        clickHereLink.click();

        WebElement notification = driver.findElement(By.id("flash"));
        String notificationText = notification.getText();

        boolean isSuccessful = notificationText.contains("Action successful");
        boolean isUnsuccessful = notificationText.contains("Action unsuccessful, please try again");

        Assert.assertTrue(isSuccessful || isUnsuccessful,
                "Уведомление не содержит ни 'Action successful', ни 'Action unsuccessful, please try again'. Текст: " + notificationText);

        driver.quit();
    }
}
