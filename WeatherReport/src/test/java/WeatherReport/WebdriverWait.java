package WeatherReport;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WebdriverWait {
    WebDriver driver;
    Wait<WebDriver> wait;
    public WebdriverWait(final WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<WebDriver>(this.driver).withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

    public boolean WaitForElement(final By element) {
        final WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(final WebDriver driver) {
                return driver.findElement(element);
            }
        });
        return ele.isDisplayed();
    }

    public void ClickIfPresentWithWait(final By element) {
        final WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(final WebDriver driver) {
            return driver.findElement(element);
        }});
        ele.click();
    }
    
}