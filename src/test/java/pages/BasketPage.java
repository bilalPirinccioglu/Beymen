package pages;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverManager;

import java.io.*;
import java.util.List;

public class BasketPage extends BasePage{
    public BasketPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy (xpath = "//span[@class='m-productPrice__extraPrice']")
    public List<WebElement> price;
    @FindBy (xpath = "//strong[@class='m-basket__productInfoCategory']")
    public WebElement brand;
    @FindBy (xpath = "//span[@class='m-basket__productInfoName']")
    public WebElement productDetail;
    @FindBy (xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement priceBeforeDiscount;
    @FindBy (xpath = "//*[@id=\"quantitySelect0-key-0\"]")
    public WebElement productAmount;
    @FindBy (xpath = "//select[@id='quantitySelect0-key-0']/option")
    public  List<WebElement> productAmountList;
    @FindBy(xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement deleteProduct;
    @FindBy(xpath = "//*[@id=\"emtyCart\"]")
    public List<WebElement> emptyCard;




    public BasketPage checkPrice(String path) throws IOException {

        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String text = null;
        while ((st = br.readLine()) != null){
            text += st;
        }
        if (price.isEmpty()){
            Assert.assertTrue("Product page price and basket page price are not equals!!!",text.contains(priceBeforeDiscount.getText().substring(0,priceBeforeDiscount.getText().length()-6)));
        }
        else
            Assert.assertTrue("Product page price and basket page price are not equals!!!",text.contains(price.get(0).getText().substring(0,price.get(0).getText().length()-6)));
        return this;
    }

    public BasketPage changeProductAmount(int i ){
        click(productAmount);
        waitUntilVisible(productAmountList);
        click(productAmountList.get(i-1));
        implicitlyWait(3);
        return this;

    }
    public BasketPage deleteProduct(){
        click(deleteProduct);
        return this;
    }

    public BasketPage basketEmptyCheck(){
        Assert.assertFalse("Basket not empty",!emptyCard.isEmpty());
        return this;
    }





}
