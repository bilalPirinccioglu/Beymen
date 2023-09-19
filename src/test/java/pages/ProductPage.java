package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.DriverManager;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage{
    public ProductPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy (xpath = "(//a[@class='o-productDetail__brandLink'])")
    public WebElement brand;
    @FindBy (xpath = "//span[@class='o-productDetail__description']")
    public WebElement productDetail;
    @FindBy (id = "priceNew")
    public WebElement price;
    @FindBy(xpath = "(//span[@class='m-variation__item'])")
    public List<WebElement> sizeList;
    @FindBy(xpath = "//*[@id=\"addBasket\"]")
    public WebElement addBasket;
    @FindBy(xpath = "//div[@class='m-price__lastPrice']")
    public List<WebElement> lastPrice;
    @FindBy(xpath = "(//span[@class='m-variation__item -criticalStock'])")
    public List<WebElement> criticalStock;

    public ProductPage getProductInfoToTxt(String path) throws IOException {

        FileWriter writer = new FileWriter(path,false);
        new FileOutputStream(path).close();
        if (lastPrice.isEmpty()){
            writer.write("Brand:" + brand.getText()+"\nName:"+productDetail.getText()+"\nPrice:"+price.getText());
            writer.close();
        }
        else {
            writer.write("Brand:" + brand.getText()+"\nName:"+productDetail.getText()+"\nPrice:"+lastPrice.get(0).getText());
            writer.close();
        }

        log.info("Successfully wrote to the file.");

        return this;
    }

    public ProductPage selectRandomSize(){
        Random rand = new Random();
        int i;
        if (sizeList.isEmpty()){
            i=rand.nextInt(criticalStock.size());
            hoverOver(criticalStock.get(i));
            click(criticalStock.get(i));
        }
        else {
            i = rand.nextInt(sizeList.size());
            hoverOver(sizeList.get(i));
            click(sizeList.get(i));
        }

        return this;
    }
    public ProductPage addBasket(){
        hoverOver(addBasket);
        click(addBasket);
        wait(8);
        return this;
    }

}
