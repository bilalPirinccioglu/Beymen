package hooks;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import pages.HomePage;
import utilities.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks  {
    protected WebDriver driver;
    Log log = new Log();


    @Before
    public void openMainPage(){
        DriverManager.getDriver().get(ConfigReader.getProperty("main_page"));
        log.info("Main Page opened successfully");
    }
    @BeforeEach
    public void beforeMethod(TestInfo testInfo){
        DriverManager.getDriver().get(ConfigReader.getProperty("main_page"));
        log.info(testInfo.getDisplayName()  + " is started...");
    }


    public void takeScreenshot() throws IOException {
//        if (testInfo.getTags().){
//            String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
//            final byte[]screenshot=((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//            testInfo.attach(screenshot, "image/png",date);
//            logger.info( testInfo.getDisplayName()+ " is failed, Screenshot attached to report.");
//            DriverManager.getDriver().get(ConfigReader.getProperty("main_page"));
//            implicitlyWait(5);
//            logger.info(scenario.getName() + " is failed, redirected to " + ConfigReader.getProperty("main_page"));
//        }
//        else {
//            logger.info('"'+testInfo.getDisplayName()+'"'+ " successfully completed.");
//        }
    }
   // @After
    public void closeBrowser(){
        DriverManager.closeDriver();
    }

}
