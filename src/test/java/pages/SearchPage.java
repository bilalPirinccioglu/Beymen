package pages;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage{

    public SearchPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy ( className = "m-productCard__photo")
    public List<WebElement> productList;

    public ProductPage selectRondomProduct(){
        Random rand = new Random();
        click(productList.get(rand.nextInt(47)));
        implicitlyWait(5);
        return new ProductPage();
    }


}
