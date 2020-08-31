package WeatherReport;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import org.json.JSONObject;

public class GenerateReport extends POM {
    String ndtvUrl = "https://www.ndtv.com/";
    String weatherApi = "http://api.openweathermap.org/data/2.5/weather";    
    WebDriver driver;
    WebdriverWait Wait;
    WebDriverWait Expect;
    Actions action;
    String City;
    RestAssuredClient client = new RestAssuredClient(weatherApi,"7fe67bf08c80ded756e598d6f8fedaea");
    @BeforeTest
    public void setupDriver() {
        this.driver = new ChromeDriver();
        this.Wait = new WebdriverWait(this.driver);
        this.Expect = new WebDriverWait(this.driver, 20);
        this.action = new Actions(driver);
        this.City = "Rajkot";
        
    }

    @Test
    public void sampleTest()
    {
        driver.get(ndtvUrl);
        if(Wait.WaitForElement(Notification)){
            driver.findElement(NotificationNo).click();
        }
        Expect.until(ExpectedConditions.presenceOfElementLocated(Choose_India));
        driver.findElement(Choose_India).click();
        Expect.until(ExpectedConditions.presenceOfElementLocated(Sections));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        driver.findElement(Sections).click();
        Expect.until(ExpectedConditions.invisibilityOfElementLocated(TopNavHidden));
        action.moveToElement(driver.findElement(WeatherOption));
        Wait.ClickIfPresentWithWait(WeatherOption);
        Expect.until(ExpectedConditions.urlToBe("https://social.ndtv.com/static/Weather/report/"));
        Assert.assertEquals(driver.getTitle(), "NDTV Weather - Weather in your Indian City");
        Expect.until(ExpectedConditions.presenceOfElementLocated(SearchBox));
        String isCheked = driver.findElement(SelectCityCheckbox(City)).getAttribute("class");
        if (isCheked != "defaultChecked") {
            driver.findElement(SearchBox).sendKeys(City);
            driver.findElement(SelectCityCheckbox(City)).click();
        }
        Wait.WaitForElement(chooseCity(City));
        Expect.until(ExpectedConditions.presenceOfElementLocated(chooseCity(City)));
        driver.findElement(chooseCity(City)).click();
        Expect.until(ExpectedConditions.presenceOfElementLocated(TempratureInfo));
        List<WebElement> tempinfo = driver.findElements(TempratureInfo);
        String data = "";
        for (WebElement ele : tempinfo) {
            data += ele.getText();
        }
        JSONObject json = new JSONObject("{ "+data+" } ");
        System.out.println("From NDTV "+json.toString());
        String ApiData = client.getResponseBody(City);
        System.out.println("From Weather API"+ApiData);
    }



    public float kelvinToCelcius(float kelvin) {
        return (float) (kelvin - 273.15);
    }
    
    @AfterClass
    public void destroyDriver() {
        this.driver.quit();
    }
}