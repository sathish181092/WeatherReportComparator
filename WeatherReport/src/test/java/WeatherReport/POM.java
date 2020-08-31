package WeatherReport;
import org.openqa.selenium.By;

public class POM {
    By Choose_India = By.xpath("//div[@class='topnav_cont']/a[text()='INDIA']");
    By Notification = By.xpath("//div[@class='noti_wrap']");
    By NotificationNo = By.xpath("//div[@class='noti_wrap']//a[@class='notnow']");
    By Sections = By.xpath("//div[@class='topmenu']/div[text()='Sections']");
    By TopNavMenu = By.xpath("//div[@class='topnav_expand']");
    By TopNavHidden = By.xpath("//div[@class='topnav_expand' and @style='display: none;']");
    By WeatherOption = By.xpath("//div[@class='topnav_expand']//li[@class='n_weather']/a");
    By SearchBox = By.id("searchBox");
    By TempratureInfo = By.xpath("//div[@class='leaflet-popup-content']/div/span");

    public By chooseCity(String city) {
        By ele = By.xpath("//div[@title='"+city+"']/parent::div");
        return ele;
    }

    public By SelectCityCheckbox(String city){
        By ele = By.xpath("//input[@id='"+city+"']");
        return ele;
    }


}