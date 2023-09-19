package pages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver driver;
    Log log = new Log();


    @FindBy(xpath = "//input[@placeholder='Ürün, Marka Arayın']")
    public WebElement searchBar;
    @FindBy(xpath = "//a[@title='Sepetim']")
    public WebElement basket;

    public BasePage sendSearch(String text){
        sendKeys(searchBar,text);
        return this;
    }
    public BasePage clearSearch(){
        doubleClick(searchBar);
        Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(Keys.DELETE).build().perform();
        return this;
    }
    public void sendEnter(){
        Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(Keys.ENTER).build().perform();
        log.info("Send ENTER");
    }

    public SearchPage sendEnterInSearch(){
        sendEnter();
        wait(2);
        return new SearchPage();
    }
    public BasketPage goBasket(){
        click(basket);
        implicitlyWait(5);
        return new BasketPage();
    }







    //baseFunctionalities
    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void click(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        log.info("Clicked element is " + webElement);
    }
    public static void doubleClick(WebElement element){
        Actions actions =new Actions(DriverManager.getDriver());
        actions.doubleClick(element).perform();
    }

    public void waitUntilVisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public static void wait(int secs) {

        try {
            Thread.sleep(1000 * secs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver.Timeouts implicitlyWait(int waitTime){
        return  DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
    }
    public  void sendKeys(WebElement webElement, String string){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.sendKeys(string);
        implicitlyWait(5);
        log.info("Sent text is " + string +  " to " + webElement);
    }

    public static String readDataFromExcel(String path, int rowNumber, int columnNumber) throws IOException, InvalidFormatException {

        FileInputStream fs = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fs);

        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
        Row row=sheet.getRow(rowNumber); //returns the logical row
        Cell cell=row.getCell(columnNumber); //getting the cell representing the given column
        String value=cell.getStringCellValue();    //getting cell value
        return value;

    }

    public void hoverOver(WebElement element){
        Actions act = new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(element));
        act.moveToElement(element).build().perform();
        log.info("element is "+ element);

    }

    public static void waitUntilVisible(List<WebElement> webElement){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }


}
