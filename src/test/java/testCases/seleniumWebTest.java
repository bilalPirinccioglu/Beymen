package testCases;

import hooks.Hooks;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.*;
import org.junit.Test;
import utilities.ConfigReader;

import java.io.IOException;

import static pages.BasePage.*;


public class seleniumWebTest extends Hooks {





    @Test
    public void seleniumWebCase() throws IOException, InvalidFormatException {
        new HomePage()
                .acceptAllCookies()
                .closeGenderChoice()
                .sendSearch(readDataFromExcel(ConfigReader.getProperty("excel_path"),0,0))
                .clearSearch()
                .sendSearch(readDataFromExcel(ConfigReader.getProperty("excel_path"),0,1))
                .sendEnterInSearch()
                .selectRondomProduct()
                .getProductInfoToTxt(ConfigReader.getProperty("txt_path"))
                .selectRandomSize()
                .addBasket()
                .goBasket()
                .checkPrice(ConfigReader.getProperty("txt_path"))
                .changeProductAmount(2)
                .deleteProduct();

    }
}
