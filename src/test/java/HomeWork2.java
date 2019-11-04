import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.*;

public class HomeWork2 {

    WebDriver driver;

    @After
    public void finish() {
        driver.quit();
    }

    @Test
    public void chromeTest(){
        driver = new ChromeDriver();
        mainMethHomeWork();
    }

    @Test
    public void fireFoxTest(){
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        mainMethHomeWork();
    }

    @Test
    public void iETest(){
        driver = new InternetExplorerDriver();
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        mainMethHomeWork();
    }

    public void mainMethHomeWork() {
        driver.get("http://demo.litecart.net");
        waitEl(By.id("header"), 50);
        WebElement elem = driver.findElement(By.className("product-column"));
        WebElement elemInfo = elem.findElement(By.className("info"));
        String elemTitle = elemInfo.findElement(By.className("name")).getText();
        WebElement elemPriceReg = elemInfo.findElement(By.className("regular-price"));
        WebElement elemPriceCamp = elemInfo.findElement(By.className("campaign-price"));
        String elemPriceRegCost = elemPriceReg.getText();
        String elemPiceRegColor = elemPriceReg.getCssValue("color");
        String elemPiceRegStyle = elemPriceReg.getTagName();
        String elemPriceCampCost = elemPriceCamp.getText();
        String elemPiceCampColor = elemPriceCamp.getCssValue("color");
        String elemPiceCampStyle = elemPriceCamp.getTagName();

        Actions mouse = new Actions(driver);
        Action mouseMove = mouse
                .moveToElement(elem)
                .build();
        mouseMove.perform();
        elem.click();
        waitEl(By.className("title"), 50);
        String innerName = driver.findElement(By.cssSelector("#box-product .title")).getText();
        WebElement innerPriceReg = driver.findElement(By.className("regular-price"));
        WebElement innerPriceCamp = driver.findElement(By.className("campaign-price"));

        //assert that:
        // - Product Name is equal on Main page and on Item Page
        assert (elemTitle.equals(innerName));
        // - Prices (discount and regular) are equal on both pages
        assert (elemPriceRegCost.equals(innerPriceReg.getText()));
        assert (elemPriceCampCost.equals(innerPriceCamp.getText()));

        // - Regular price is gray and strike () on both pages
        assert (elemPiceRegColor.equals(innerPriceReg.getCssValue("color")));
        assert (elemPiceRegColor.contains("51, 51, 51"));
        assert (elemPiceRegStyle.equals(innerPriceReg.getTagName()));
        assert (elemPiceRegStyle.equals("del"));
        // - Campaigns price is red and bold on both pages
        assert (elemPiceCampColor.equals(innerPriceCamp.getCssValue("color")));
        assert (elemPiceCampColor.contains("204, 0, 0"));
        assert (elemPiceCampStyle.equals(innerPriceCamp.getTagName()));
        assert (elemPiceCampStyle.equals("strong"));
    }

    public void waitEl(By elem, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
    }

}