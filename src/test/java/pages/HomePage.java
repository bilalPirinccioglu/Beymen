package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;



public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy (id = "onetrust-accept-btn-handler")
    public WebElement acceptAllCookies;
    @FindBy (id = "genderManButton")
    public WebElement genderManButton;
    @FindBy (id = "genderWomanButton")
    public WebElement genderWomanButton;
    @FindBy (xpath = "(//*[name()='svg'][@class='icon icon-close'])[2]")
    public WebElement genderCloseButton;

    public HomePage acceptAllCookies() {
        implicitlyWait(5);
        click(acceptAllCookies);
        return this;
    }
    public HomePage closeGenderChoice(){
        click(genderCloseButton);
        return this;
    }



}
