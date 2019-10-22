import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeWork1 {

    WebDriver driver;

    @Before
    public void openBrowser()
    {
        driver = new ChromeDriver();
        driver.get("http://demo.litecart.net/admin/");
    }

    @After
    public void finish()
    {
        driver.quit();
    }

    @Test
    public void  firstHomeWork()
    {
        waitElCli(By.cssSelector("button[name =login]"));
        driver.findElement(By.cssSelector("button[name =login]")).click();
        this.waitEl(By.className("img-responsive"));

        List<WebElement> elements = driver.findElements(By.cssSelector("li.app"));
        List<String> headers= new ArrayList<String>();

        elements.forEach((d)-> headers.add(d.getText()));

        for (String element :headers) {
            String butName = getSideBarMenuName(element);
            driver.findElement(By.xpath(butName)).click();

            List<WebElement> subdirectory = driver.findElements(By.cssSelector("li.doc"));
            List<String> subdirectoryName= new ArrayList<String>();

            subdirectory.forEach((n) -> subdirectoryName.add(n.getText()));

            subdirectoryName.forEach((elem)->clickAndCheck(elem));
        }
    }

    private void waitEl(By elem)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
    }

    private void waitElCli(By elem)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }

    private void clickAndCheck(String elem)
    {
        String str = getSideBarMenuName(elem);
        driver.findElement(By.xpath(str)).click();
        assert(driver.findElements(By.xpath("//*[@class ='panel-heading']")).size()>0);
    }

    private String getSideBarMenuName(String elem)
    {
        return String.format("//*[@class ='name' and contains(text(),'%s')]",elem);
    }
}
